package com.example.fp_batik;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.fp_batik.api.ApiEndpoints;
import com.example.fp_batik.api.VolleySingleton;
import com.example.fp_batik.api.VolleyMultipartRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class ScanActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int CAMERA_PERMISSION_CODE = 102;

    ActivityResultLauncher<Intent> activityResultLauncher =  registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    processAndRedirect(result.getData());
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });
        // Langsung buka kamera tanpa layout
        openCamera();
    }

    private void openCamera() {
        // Check camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            // Permission already granted, open camera
            launchCamera();
        }
    }

    private void launchCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            activityResultLauncher.launch(cameraIntent);
        } else {
            Toast.makeText(this, "Kamera tidak tersedia di perangkat ini", Toast.LENGTH_SHORT).show();
            finish(); // Kembali ke activity sebelumnya
        }
    }

    private void processAndRedirect(Intent intentData) {
        // Show processing message

        Bitmap bitmap = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bitmap = intentData.getParcelableExtra("data", Bitmap.class); // API 33+
        } else {
            bitmap = (Bitmap) Objects.requireNonNull(intentData.getExtras()).get("data"); // Pre-API 33
        }

        if (bitmap != null) {
            Toast.makeText(this, "Mengidentifikasi motif batik...", Toast.LENGTH_SHORT).show();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imageData = stream.toByteArray();


            VolleyMultipartRequest volleyRequest = new VolleyMultipartRequest(
                    Request.Method.POST,
                    ApiEndpoints.PREDICT,
                    response -> {
                        try {
                            String batikClass = new JSONObject(new String(response.data))
                                    .getJSONObject("data")
                                    .getJSONObject("prediction")
                                    .getString("class");

                            Log.e("BATIK KELAS", batikClass);


                            JsonObjectRequest getBatikRequest = new JsonObjectRequest(
                                    String.format(ApiEndpoints.GET_BATIK, batikClass),
                                    response1 -> {
                                        try {
                                            JSONObject kainData = response1.getJSONObject("data").getJSONObject("kainData");
                                            JSONArray pictures = response1.getJSONObject("data").getJSONArray("pictures");

                                            Intent resultIntent = new Intent(ScanActivity.this, BatikDetailActivity.class);

                                            resultIntent.putExtra("batik_name", kainData.getString("name"));
                                            resultIntent.putExtra("batik_origin", kainData.getString("origin"));
                                            resultIntent.putExtra("batik_era", kainData.getInt("century"));
                                            resultIntent.putExtra("batik_type", kainData.getString("type"));
                                            resultIntent.putExtra("batik_meaning", kainData.getString("meaning"));
                                            resultIntent.putExtra("batik_image_url", String.format(ApiEndpoints.IMAGE, pictures.getString(0)));
                                            resultIntent.putExtra("from_scan", true);

                                            Toast.makeText(this, "Berhasil Deteksi", Toast.LENGTH_LONG).show();

                                            startActivity(resultIntent);
                                            finish();
                                        } catch (Exception error) {
                                            Log.e("GET KAIN DATA", error.toString());
                                            Toast.makeText(this, "Terjadi Kesalahan dalam Mengambil Data Batik", Toast.LENGTH_LONG).show();
                                        }
                                    },
                                    error -> {
                                        Log.e("GET KAIN", error.toString());
                                        Toast.makeText(this, "Gagal: " + error.toString(), Toast.LENGTH_LONG).show();
                                    }
                            );

                            VolleySingleton.getInstance(this).addToRequestQueue(getBatikRequest);

                        } catch (Exception error) {
                            Log.e("DETECTION DATA", error.toString());
                            Toast.makeText(this, "Terjadi Kesalahan dalam Deteksi", Toast.LENGTH_LONG).show();
                        }
                    },
                    error -> {
                        Log.e("DETECTION", error.toString());
                        Toast.makeText(this, "Gagal: " + error.toString(), Toast.LENGTH_LONG).show();
                    });

            volleyRequest.addFile("image", new VolleyMultipartRequest.DataPart(
                    "photo.jpg", imageData, "image/jpeg"));

            VolleySingleton.getInstance(this).addToRequestQueue(volleyRequest);

        } else {
            Toast.makeText(this, "Gagal memuat gambar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, open camera
                launchCamera();
            } else {
                Toast.makeText(this, "Izin kamera diperlukan untuk scan batik",
                        Toast.LENGTH_LONG).show();
                finish(); // Kembali ke activity sebelumnya
            }
        }
    }
}

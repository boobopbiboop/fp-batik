package com.example.fp_batik;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import com.example.fp_batik.api.ApiEndpoints;
import com.example.fp_batik.api.VolleyMultipartRequest;
import com.example.fp_batik.api.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class ScanActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 102;
    private static final double CONFIDENCE_THRESHOLD = 0.5;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    processAndRedirect(result.getData());
                } else {
                    // Jika pengguna membatalkan kamera, tutup ScanActivity
                    finish();
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            launchCamera();
        }
    }

    private void launchCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            activityResultLauncher.launch(cameraIntent);
        } else {
            Toast.makeText(this, "Kamera tidak tersedia di perangkat ini", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void processAndRedirect(Intent intentData) {
        Bitmap bitmap = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bitmap = intentData.getParcelableExtra("data", Bitmap.class);
        } else {
            bitmap = (Bitmap) Objects.requireNonNull(intentData.getExtras()).get("data");
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
                            JSONObject responseObject = new JSONObject(new String(response.data));
                            JSONObject predictionObject = responseObject.getJSONObject("data").getJSONObject("prediction");

                            // --- PERUBAHAN: Membaca confidence dan class dari JSON ---
                            double confidence = predictionObject.getDouble("confidence");
                            String batikClass = predictionObject.getString("class");

                            Log.i("PREDICTION_RESULT", "Class: " + batikClass + ", Confidence: " + confidence);

                            // --- PERUBAHAN: Kondisi baru dengan pengecekan confidence ---
                            if (confidence > CONFIDENCE_THRESHOLD && batikClass != null && !batikClass.isEmpty()) {
                                // Jika confidence tinggi & class valid, lanjutkan
                                fetchBatikDetails(batikClass);
                            } else {
                                // Jika confidence rendah, tampilkan pesan error
                                Toast.makeText(this, "Kain tidak terdeteksi", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        } catch (Exception error) {
                            // Error saat parsing JSON juga dianggap kain tidak terdeteksi
                            Log.e("DETECTION_DATA_ERROR", "Error parsing JSON: " + error.toString());
                            Toast.makeText(this, "Kain tidak terdeteksi", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    },
                    error -> {
                        // Cek detail error dari respons jaringan
                        if (error.networkResponse != null) {
                            // Jika ada respons dari server (bukan timeout), tapi statusnya error
                            // (misal: 400 atau 500) ini kemungkinan besar karena deteksi gagal.
                            Log.e("DETECTION_SERVER_ERROR", "Status Code: " + error.networkResponse.statusCode);
                            Toast.makeText(this, "Kain tidak terdeteksi", Toast.LENGTH_LONG).show();
                        } else {
                            // Jika tidak ada respons sama sekali, ini baru benar-benar masalah koneksi.
                            Log.e("DETECTION_CONNECTION_ERROR", "Error: " + error.toString());
                            Toast.makeText(this, "Gagal terhubung ke server deteksi", Toast.LENGTH_LONG).show();
                        }
                        finish();
                    });

            volleyRequest.addFile("image", new VolleyMultipartRequest.DataPart(
                    "photo.jpg", imageData, "image/jpeg"));

            VolleySingleton.getInstance(this).addToRequestQueue(volleyRequest);

        } else {
            Toast.makeText(this, "Gagal memuat gambar dari kamera", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void fetchBatikDetails(String batikClass) {
        JsonObjectRequest getBatikRequest = new JsonObjectRequest(
                String.format(ApiEndpoints.GET_BATIK, batikClass),
                response -> {
                    try {
                        JSONObject kainData = response.getJSONObject("data").getJSONObject("kainData");
                        JSONArray pictures = response.getJSONObject("data").getJSONArray("pictures");
                        String[] variationImages = new String[pictures.length()];
                        for(int i = 0; i < pictures.length(); i++)
                            variationImages[i] = String.format(ApiEndpoints.IMAGE, pictures.getString(i));

                        Intent resultIntent = new Intent(ScanActivity.this, BatikDetailActivity.class);

                        resultIntent.putExtra("batik_name", kainData.getString("name"));
                        resultIntent.putExtra("batik_origin", kainData.getString("origin"));
                        resultIntent.putExtra("batik_era", kainData.getInt("century"));
                        resultIntent.putExtra("batik_type", kainData.getString("type"));
                        resultIntent.putExtra("batik_meaning", kainData.getString("meaning"));
                        resultIntent.putExtra("batik_image_url", String.format(ApiEndpoints.IMAGE, pictures.getString(0)));
                        resultIntent.putExtra("batik_philosophy", kainData.getString("pilosophy"));
                        resultIntent.putExtra("batik_history", kainData.getString("history"));
                        resultIntent.putExtra("batik_variations", variationImages);
                        resultIntent.putExtra("from_scan", true);

                        Toast.makeText(this, "Berhasil Deteksi: " + kainData.getString("name"), Toast.LENGTH_LONG).show();

                        startActivity(resultIntent);
//                        finish();
                    } catch (Exception error) {
                        Log.e("GET_KAIN_DATA_ERROR", error.toString());
                        Toast.makeText(this, "Terjadi Kesalahan dalam Mengambil Data Batik", Toast.LENGTH_LONG).show();
                        finish();
                    }
                },
                error -> {
                    Log.e("GET_KAIN_SERVER_ERROR", error.toString());
                    Toast.makeText(this, "Gagal mengambil data untuk batik: " + batikClass, Toast.LENGTH_LONG).show();
                    finish();
                }
        );
        VolleySingleton.getInstance(this).addToRequestQueue(getBatikRequest);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchCamera();
            } else {
                Toast.makeText(this, "Izin kamera diperlukan untuk scan batik",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.example.fp_batik.api.VolleySingleton;
import com.example.fp_batik.api.VolleyMultipartRequest;

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

            String url = "http://192.168.54.169:3002/api/predict"; // ganti ke URL backend kamu

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imageData = stream.toByteArray();


            VolleyMultipartRequest volleyRequest = new VolleyMultipartRequest(
                    Request.Method.POST,
                    url,
                    response -> {
                        String resultResponse = new String(response.data);
                        Log.e("response", resultResponse);
                        Toast.makeText(this, "Berhasil: " + resultResponse, Toast.LENGTH_LONG).show();
                    },
                    error -> {
                        Toast.makeText(this, "Gagal: " + error.toString(), Toast.LENGTH_LONG).show();

                    });

            volleyRequest.addFile("image", new VolleyMultipartRequest.DataPart(
                    "photo.jpg", imageData, "image/jpeg"));

            VolleySingleton.getInstance(this).addToRequestQueue(volleyRequest);

        } else {
            Toast.makeText(this, "Gagal memuat gambar", Toast.LENGTH_SHORT).show();
        }
        // Simulate AI processing time (replace with your actual ML/API logic)
//        new android.os.Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Simulate different batik results
//                String[] batikNames = {"Batik Parang", "Batik Kawung", "Batik Mega Mendung", "Batik Truntum"};
//                String[] origins = {"Yogyakarta", "Solo", "Cirebon", "Yogyakarta"};
//                String[] meanings = {
//                        "Melambangkan kekuatan dan keteguhan hati",
//                        "Melambangkan kesucian dan keadilan",
//                        "Melambangkan kesabaran dan ketenangan",
//                        "Melambangkan cinta kasih yang tumbuh"
//                };
//
//                // Random selection for demo (replace with actual AI result)
//                int randomIndex = (int) (Math.random() * batikNames.length);
//
//                // Redirect to BatikDetailActivity
//                Intent resultIntent = new Intent(ScanActivity.this, BatikDetailActivity.class);
//                resultIntent.putExtra("batik_name", batikNames[randomIndex]);
//                resultIntent.putExtra("batik_origin", origins[randomIndex]);
//                resultIntent.putExtra("batik_era", "Klasik");
//                resultIntent.putExtra("batik_type", "Tradisional");
//                resultIntent.putExtra("batik_meaning", meanings[randomIndex]);
//                resultIntent.putExtra("batik_image_url", "https://example.com/batik.jpg");
//                resultIntent.putExtra("from_scan", true);
//
//                startActivity(resultIntent);
//                finish(); // Close scan activity
//            }
//        }, 1500); // 1.5 second processing
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

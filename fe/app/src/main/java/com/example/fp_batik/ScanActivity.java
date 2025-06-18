package com.example.fp_batik;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ScanActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int CAMERA_PERMISSION_CODE = 102;

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
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(this, "Kamera tidak tersedia di perangkat ini", Toast.LENGTH_SHORT).show();
            finish(); // Kembali ke activity sebelumnya
        }
    }

    private void processAndRedirect(Bitmap capturedImage) {
        // Show processing message
        Toast.makeText(this, "Mengidentifikasi motif batik...", Toast.LENGTH_SHORT).show();

        // Simulate AI processing time (replace with your actual ML/API logic)
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Simulate different batik results
                String[] batikNames = {"Batik Parang", "Batik Kawung", "Batik Mega Mendung", "Batik Truntum"};
                String[] origins = {"Yogyakarta", "Solo", "Cirebon", "Yogyakarta"};
                String[] meanings = {
                        "Melambangkan kekuatan dan keteguhan hati",
                        "Melambangkan kesucian dan keadilan",
                        "Melambangkan kesabaran dan ketenangan",
                        "Melambangkan cinta kasih yang tumbuh"
                };

                // Random selection for demo (replace with actual AI result)
                int randomIndex = (int) (Math.random() * batikNames.length);

                // Redirect to BatikDetailActivity
                Intent resultIntent = new Intent(ScanActivity.this, BatikDetailActivity.class);
                resultIntent.putExtra("batik_name", batikNames[randomIndex]);
                resultIntent.putExtra("batik_origin", origins[randomIndex]);
                resultIntent.putExtra("batik_era", "Klasik");
                resultIntent.putExtra("batik_type", "Tradisional");
                resultIntent.putExtra("batik_meaning", meanings[randomIndex]);
                resultIntent.putExtra("batik_image_url", "https://example.com/batik.jpg");
                resultIntent.putExtra("from_scan", true);

                startActivity(resultIntent);
                finish(); // Close scan activity
            }
        }, 1500); // 1.5 second processing
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                // Handle camera result
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap capturedImage = (Bitmap) extras.get("data");
                    // Langsung proses dan redirect
                    processAndRedirect(capturedImage);
                }
            } else {
                // User cancelled
                Toast.makeText(this, "Scan dibatalkan", Toast.LENGTH_SHORT).show();
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

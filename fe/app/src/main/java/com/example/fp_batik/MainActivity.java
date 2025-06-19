package com.example.fp_batik;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.fp_batik.api.ApiEndpoints;
import com.example.fp_batik.api.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Links to your XML layout

        // Initialize views
        ImageButton scanButton = findViewById(R.id.scan);
        gridLayout = findViewById(R.id.gridLayout);

        // Set OnClickListener for the scan button
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScanClicked(v);
            }
        });

        // Example: Add some dummy items to the GridLayout
        // In a real app, you would dynamically load data here (e.g., from a database or API)
        JsonObjectRequest getAllBatikRequest = new JsonObjectRequest(
                ApiEndpoints.GET_ALL_BATIK,
                response -> {
                    try {
                        JSONArray batikArray = response.getJSONArray("data");
                        // Assuming the response is an array of batik objects
                        for (int i = 0; i < batikArray.length(); i++) {
                            JSONObject batikData = batikArray.getJSONObject(i);
                            addGridItem(
                                    batikData.getString("name"),
                                    String.format(ApiEndpoints.IMAGE, batikData.getString("dirUrl")),
                                    batikData.getString("class")
                                    );
                        }
                    } catch (Exception error) {
                        Log.e("GET ALL BATIK", error.toString());
                        Toast.makeText(MainActivity.this, "Terjadi kesalahan dalam memuat data batik", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e("GET ALL BATIK", error.toString());
                    Toast.makeText(MainActivity.this, "Gagal memuat data batik: " + error.toString(), Toast.LENGTH_SHORT).show();
                }
            );

        VolleySingleton.getInstance(MainActivity.this).addToRequestQueue(getAllBatikRequest);
    }

    /**
     * Handles the click event for the scan button.
     * In a real application, you might start a camera activity for scanning.
     */
    public void onScanClicked(View view) {
        Toast.makeText(this, "Scan button clicked!", Toast.LENGTH_SHORT).show();
        // Example: Start a new activity for scanning
         Intent scanIntent = new Intent(MainActivity.this, ScanActivity.class);
         startActivity(scanIntent);
    }

    private void addGridItem(String batikName, String imageUrl, String batikClass) {
        // Inflate a new item layout
        // You might want to create a separate XML layout for your grid items (e.g., grid_item.xml)
        LayoutInflater inflater = LayoutInflater.from(this);
        View gridItemView = inflater.inflate(R.layout.grid_item, gridLayout, false);

        ImageView itemImage = gridItemView.findViewById(R.id.item_image);
        TextView itemName = gridItemView.findViewById(R.id.item_name);

        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(itemImage);

        itemName.setText(batikName);

        // Optional: Set a click listener for each grid item
        gridItemView.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Clicked: " + batikName, Toast.LENGTH_SHORT).show();
            JsonObjectRequest getBatikRequest = new JsonObjectRequest(
                    String.format(ApiEndpoints.GET_BATIK, batikClass),
                    response -> {
                        try {
                            JSONObject kainData = response.getJSONObject("data").getJSONObject("kainData");
                            JSONArray pictures = response.getJSONObject("data").getJSONArray("pictures");
                            String[] variationImages = new String[pictures.length()];
                            for(int i = 0; i < pictures.length(); i++)
                                variationImages[i] = String.format(ApiEndpoints.IMAGE, pictures.getString(i));

                            Intent resultIntent = new Intent(MainActivity.this, BatikDetailActivity.class);

                            resultIntent.putExtra("batik_name", kainData.getString("name"));
                            resultIntent.putExtra("batik_origin", kainData.getString("origin"));
                            resultIntent.putExtra("batik_era", kainData.getInt("century"));
                            resultIntent.putExtra("batik_type", kainData.getString("type"));
                            resultIntent.putExtra("batik_meaning", kainData.getString("meaning"));
                            resultIntent.putExtra("batik_image_url", String.format(ApiEndpoints.IMAGE, pictures.getString(0)));
                            resultIntent.putExtra("batik_philosophy", kainData.getString("pilosophy"));
                            resultIntent.putExtra("batik_history", kainData.getString("history"));
                            resultIntent.putExtra("batik_variations", variationImages);
                            resultIntent.putExtra("from_scan", false);

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

            VolleySingleton.getInstance(MainActivity.this).addToRequestQueue(getBatikRequest);
        });

        gridLayout.addView(gridItemView);
    }
}
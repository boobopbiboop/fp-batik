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
                            String batikName = batikArray.getJSONObject(i).getString("name");
                            String imageUrl = batikArray.getJSONObject(i).getString("dirUrl");
                            addGridItem(batikName, String.format(ApiEndpoints.IMAGE, imageUrl));
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

    /**
     * Adds an item to the GridLayout.
     * Each item consists of an ImageView and a TextView.
     * @param name The name of the batik.
     * @param batikImageUrl The url for the batik image.
     */
    private void addGridItem(String name, String batikImageUrl) {
        // Inflate a new item layout
        // You might want to create a separate XML layout for your grid items (e.g., grid_item.xml)
        LayoutInflater inflater = LayoutInflater.from(this);
        View gridItemView = inflater.inflate(R.layout.grid_item, gridLayout, false);

        ImageView itemImage = gridItemView.findViewById(R.id.item_image);
        TextView itemName = gridItemView.findViewById(R.id.item_name);

        if (!batikImageUrl.isEmpty()) {
            Glide.with(this)
                    .load(batikImageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .into(itemImage);
        }

        itemName.setText(name);

        // Optional: Set a click listener for each grid item
        gridItemView.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Clicked: " + name, Toast.LENGTH_SHORT).show();
            // Example: Start a detail activity for the clicked item
            // Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
            // detailIntent.putExtra("batik_name", name);
            // detailIntent.putExtra("batik_image", imageResId);
            // startActivity(detailIntent);
        });

        gridLayout.addView(gridItemView);
    }
}
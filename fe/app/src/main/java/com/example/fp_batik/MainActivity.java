package com.example.fp_batik;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

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
        addGridItem("Batik Parang", R.drawable.batik_parang);
        addGridItem("Batik Kawung", R.drawable.batik_kawung);
        //addGridItem("Batik Megamendung", R.drawable.batik_megamendung);
        //addGridItem("Batik Pekalongan", R.drawable.batik_pekalongan);
        //addGridItem("Batik Solo", R.drawable.batik_solo);
        //addGridItem("Batik Cirebon", R.drawable.batik_cirebon);
    }

    /**
     * Handles the click event for the scan button.
     * In a real application, you might start a camera activity for scanning.
     */
    public void onScanClicked(View view) {
        Toast.makeText(this, "Scan button clicked!", Toast.LENGTH_SHORT).show();
        // Example: Start a new activity for scanning
        // Intent scanIntent = new Intent(MainActivity.this, ScanActivity.class);
        // startActivity(scanIntent);
    }

    /**
     * Adds an item to the GridLayout.
     * Each item consists of an ImageView and a TextView.
     * @param name The name of the batik.
     * @param imageResId The drawable resource ID for the batik image.
     */
    private void addGridItem(String name, int imageResId) {
        // Inflate a new item layout
        // You might want to create a separate XML layout for your grid items (e.g., grid_item.xml)
        LayoutInflater inflater = LayoutInflater.from(this);
        View gridItemView = inflater.inflate(R.layout.grid_item, gridLayout, false);

        ImageView itemImage = gridItemView.findViewById(R.id.item_image);
        TextView itemName = gridItemView.findViewById(R.id.item_name);

        itemImage.setImageResource(imageResId);
        itemName.setText(name);

        // Optional: Set a click listener for each grid item
        gridItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked: " + name, Toast.LENGTH_SHORT).show();
                // Example: Start a detail activity for the clicked item
                // Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                // detailIntent.putExtra("batik_name", name);
                // detailIntent.putExtra("batik_image", imageResId);
                // startActivity(detailIntent);
            }
        });

        gridLayout.addView(gridItemView);
    }
}
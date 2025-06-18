package com.example.fp_batik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class BatikDetailActivity extends AppCompatActivity {

    // Views
    private ImageView batikImg;
    private TextView batikNameText;
    private TextView originNameText;
    private TextView originValue;
    private TextView eraValue;
    private TextView typeValue;
    private TextView meaningValue;
    private TextView toolbarTitle;
    private ImageButton backButton;
    private ImageButton scanButton;
    private RecyclerView relatedBatikRecyclerView;

    // Adapter
    private RelatedBatikAdapter relatedBatikAdapter;
    private List<BatikItem> relatedBatikList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initViews();
        setupRecyclerView();
        loadBatikData();
        setupClickListeners();
    }

    private void initViews() {
        // Initialize all views
        batikImg = findViewById(R.id.batik_img);
        batikNameText = findViewById(R.id.batik_name_text);
        originNameText = findViewById(R.id.origin_name_text);
        originValue = findViewById(R.id.originValue);
        eraValue = findViewById(R.id.eraValue);
        typeValue = findViewById(R.id.typeValue);
        meaningValue = findViewById(R.id.meaningValue);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        backButton = findViewById(R.id.backButton);
        scanButton = findViewById(R.id.scan);
        relatedBatikRecyclerView = findViewById(R.id.relatedBatikRecyclerView);
    }

    private void setupRecyclerView() {
        relatedBatikList = new ArrayList<>();
        relatedBatikAdapter = new RelatedBatikAdapter(relatedBatikList, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        relatedBatikRecyclerView.setLayoutManager(layoutManager);
        relatedBatikRecyclerView.setAdapter(relatedBatikAdapter);

        // Add some spacing between items
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_item_spacing);
        relatedBatikRecyclerView.addItemDecoration(new SpacingItemDecoration(spacingInPixels));
    }

    private void loadBatikData() {
        // Get data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            String batikName = intent.getStringExtra("batik_name");
            String batikOrigin = intent.getStringExtra("batik_origin");
            String batikEra = intent.getStringExtra("batik_era");
            String batikType = intent.getStringExtra("batik_type");
            String batikMeaning = intent.getStringExtra("batik_meaning");
            String batikImageUrl = intent.getStringExtra("batik_image_url");

            // Set data to views
            if (batikName != null) {
                batikNameText.setText(batikName);
                toolbarTitle.setText(batikName);
            }

            if (batikOrigin != null) {
                originNameText.setText(batikOrigin);
                originValue.setText(batikOrigin);
            }

            if (batikEra != null) {
                eraValue.setText(batikEra);
            }

            if (batikType != null) {
                typeValue.setText(batikType);
            }

            if (batikMeaning != null) {
                meaningValue.setText(batikMeaning);
            }

            // Load image using Glide
            if (batikImageUrl != null && !batikImageUrl.isEmpty()) {
                Glide.with(this)
                        .load(batikImageUrl)
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.error_image)
                        .into(batikImg);
            }
        }

        // Load related batik data
        loadRelatedBatikData();
    }

    private void loadRelatedBatikData() {
        // Sample data - replace with your actual data source
        relatedBatikList.clear();

        // Add sample related batik items
        relatedBatikList.add(new BatikItem("1", "Batik Parang", "Yogyakarta",
                "https://example.com/batik1.jpg", "Klasik", "Kekuatan"));
        relatedBatikList.add(new BatikItem("2", "Batik Kawung", "Solo",
                "https://example.com/batik2.jpg", "Tradisional", "Kesucian"));
        relatedBatikList.add(new BatikItem("3", "Batik Mega Mendung", "Cirebon",
                "https://example.com/batik3.jpg", "Pesisir", "Kesabaran"));
        relatedBatikList.add(new BatikItem("4", "Batik Truntum", "Yogyakarta",
                "https://example.com/batik4.jpg", "Klasik", "Cinta Kasih"));

        relatedBatikAdapter.notifyDataSetChanged();
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                onBackPressed();
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScanClicked(v);
            }
        });
    }

    public void onScanClicked(View view) {
        // Handle scan button click
        Intent scanIntent = new Intent(this, ScanActivity.class);
        startActivity(scanIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

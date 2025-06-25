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
    private TextView philosophyText;
    private ImageView variationImage1;
    private ImageView variationImage2;
    private ImageView variationImage3;
    private ImageView variationImage4;
    private TextView historyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initViews();
//        setupRecyclerView();
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
        philosophyText = findViewById(R.id.philosophyText);
        variationImage1 = findViewById(R.id.variationImage1);
        variationImage2 = findViewById(R.id.variationImage2);
        variationImage3 = findViewById(R.id.variationImage3);
        historyText = findViewById(R.id.historyText);
//        relatedBatikRecyclerView = findViewById(R.id.relatedBatikRecyclerView);
    }

//    private void setupRecyclerView() {
//        relatedBatikList = new ArrayList<>();
//        relatedBatikAdapter = new RelatedBatikAdapter(relatedBatikList, this);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        relatedBatikRecyclerView.setLayoutManager(layoutManager);
//        relatedBatikRecyclerView.setAdapter(relatedBatikAdapter);
//
//        // Add some spacing between items
//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_item_spacing);
//        relatedBatikRecyclerView.addItemDecoration(new SpacingItemDecoration(spacingInPixels));
//    }

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
            String batikPhilosophy = intent.getStringExtra("batik_philosophy");
            String batikHistory = intent.getStringExtra("batik_history");

            // Get variation images dari Roboflow
            String[] variationImages = intent.getStringArrayExtra("batik_variations");

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

            // Set philosophy text dari database (unik untuk setiap batik)
            if (batikPhilosophy != null && !batikPhilosophy.isEmpty()) {
                philosophyText.setText(batikPhilosophy);
            } else {
                // Fallback jika data database kosong
                philosophyText.setText("Data filosofi untuk " + (batikName != null ? batikName : "batik ini") + " sedang dimuat...");
            }

            // Set history text dari database (unik untuk setiap batik)
            if (batikHistory != null && !batikHistory.isEmpty()) {
                historyText.setText(batikHistory);
            } else {
                historyText.setText("Data sejarah untuk " + (batikName != null ? batikName : "batik ini") + " sedang dimuat...");
            }

            // Load main batik image dari Roboflow
            if (batikImageUrl != null && !batikImageUrl.isEmpty()) {
                Glide.with(this)
                        .load(batikImageUrl)
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.error_image)
                        .into(batikImg);
            }

            if (variationImages == null || variationImages.length < 3) {
                variationImages = getDefaultVariationUrls(batikName);
            }
            loadVariationImages(variationImages);
        }

        // Load related batik data
//        loadRelatedBatikData();
    }

    private void loadVariationImages(String[] variationImages) {
        if (variationImages != null && variationImages.length >= 3) {
            Glide.with(this)
                    .load(variationImages[0])
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_close_clear_cancel)
                    .into(variationImage1);

            Glide.with(this)
                    .load(variationImages[1])
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_close_clear_cancel)
                    .into(variationImage2);

            Glide.with(this)
                    .load(variationImages[2])
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_close_clear_cancel)
                    .into(variationImage3);
        }
    }

private String[] getDefaultVariationUrls(String batikName) {
    // Return default variation URLs berdasarkan nama batik dari Roboflow
    if (batikName != null) {
        switch (batikName.toLowerCase()) {
            case "batik parang":
                return new String[]{
                        "https://your-roboflow-dataset.com/parang-var1.jpg",
                        "https://your-roboflow-dataset.com/parang-var2.jpg",
                        "https://your-roboflow-dataset.com/parang-var3.jpg"
                };
            case "batik kawung":
                return new String[]{
                        "https://your-roboflow-dataset.com/kawung-var1.jpg",
                        "https://your-roboflow-dataset.com/kawung-var2.jpg",
                        "https://your-roboflow-dataset.com/kawung-var3.jpg"
                };
            case "batik mega mendung":
                return new String[]{
                        "https://your-roboflow-dataset.com/mega-mendung-var1.jpg",
                        "https://your-roboflow-dataset.com/mega-mendung-var2.jpg",
                        "https://your-roboflow-dataset.com/mega-mendung-var3.jpg"
                };
            case "batik truntum":
                return new String[]{
                        "https://your-roboflow-dataset.com/truntum-var1.jpg",
                        "https://your-roboflow-dataset.com/truntum-var2.jpg",
                        "https://your-roboflow-dataset.com/truntum-var3.jpg"
                };
            case "batik sido mukti":
                return new String[]{
                        "https://your-roboflow-dataset.com/sido-mukti-var1.jpg",
                        "https://your-roboflow-dataset.com/sido-mukti-var2.jpg",
                        "https://your-roboflow-dataset.com/sido-mukti-var3.jpg"
                };
            default:
                return new String[]{
                        "https://your-roboflow-dataset.com/default-var1.jpg",
                        "https://your-roboflow-dataset.com/default-var2.jpg",
                        "https://your-roboflow-dataset.com/default-var3.jpg"
                };
        }
    }
    return new String[]{"", "", ""};
}

//private void loadRelatedBatikData() {
//    // Get current batik name untuk filter
//    String currentBatikName = getIntent().getStringExtra("batik_name");
//
//    relatedBatikList.clear();
//
//    // Load batik terkait (SELAIN yang sedang dilihat)
//    // Semua gambar dari Roboflow dataset
////    if (!"Batik Parang".equals(currentBatikName)) {
////        relatedBatikList.add(new BatikItem("1", "Batik Parang", "Yogyakarta",
////                "https://your-roboflow-dataset.com/parang-main.jpg", "Klasik", "Kekuatan"));
////    }
////
////    if (!"Batik Kawung".equals(currentBatikName)) {
////        relatedBatikList.add(new BatikItem("2", "Batik Kawung", "Solo",
////                "https://your-roboflow-dataset.com/kawung-main.jpg", "Tradisional", "Kesucian"));
////    }
////
////    if (!"Batik Mega Mendung".equals(currentBatikName)) {
////        relatedBatikList.add(new BatikItem("3", "Batik Mega Mendung", "Cirebon",
////                "https://your-roboflow-dataset.com/mega-mendung-main.jpg", "Pesisir", "Kesabaran"));
////    }
////
////    if (!"Batik Truntum".equals(currentBatikName)) {
////        relatedBatikList.add(new BatikItem("4", "Batik Truntum", "Yogyakarta",
////                "https://your-roboflow-dataset.com/truntum-main.jpg", "Klasik", "Cinta Kasih"));
////    }
////
////    if (!"Batik Sido Mukti".equals(currentBatikName)) {
////        relatedBatikList.add(new BatikItem("5", "Batik Sido Mukti", "Solo",
////                "https://your-roboflow-dataset.com/sido-mukti-main.jpg", "Tradisional", "Kebahagiaan"));
////    }
//
//    relatedBatikAdapter.notifyDataSetChanged();
//}



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

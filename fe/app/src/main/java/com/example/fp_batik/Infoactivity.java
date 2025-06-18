package com.example.fp_batik;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.bumptech.glide.Glide;

public class Infoactivity extends AppCompatActivity {
    private String editTextValue1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/BVgZNpCo4N/wmavzypg_expires_30_days.png").into(findViewById(R.id.rspx6qhn2j2s));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/BVgZNpCo4N/yuxleeti_expires_30_days.png").into(findViewById(R.id.r8echa33fryg));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/BVgZNpCo4N/4wa2lu9j_expires_30_days.png").into(findViewById(R.id.reiiakluiqmq));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/BVgZNpCo4N/lib8qhmf_expires_30_days.png").into(findViewById(R.id.r01985z7w2vqc));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/BVgZNpCo4N/0ep8sgc1_expires_30_days.png").into(findViewById(R.id.r1ajkwxgqmu));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/BVgZNpCo4N/8t8pbig5_expires_30_days.png").into(findViewById(R.id.rrbnrmkbk9vq));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/BVgZNpCo4N/k13672c5_expires_30_days.png").into(findViewById(R.id.ryczgcz1lyt));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/BVgZNpCo4N/69o5nxs2_expires_30_days.png").into(findViewById(R.id.r08or4rzn91br));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/BVgZNpCo4N/rtsrjcyj_expires_30_days.png").into(findViewById(R.id.rglnm9xt3x9));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/BVgZNpCo4N/l7m9ydgm_expires_30_days.png").into(findViewById(R.id.rmm0dcmb7tg));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/BVgZNpCo4N/m1y6jzb3_expires_30_days.png").into(findViewById(R.id.r29aomnk0v4k));

        EditText editText1 = findViewById(R.id.rw0q9zkl4mmg);
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // before Text Changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextValue1 = s.toString();  // on Text Changed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // after Text Changed
            }
            fun onScanClicked(view: View) {
                // Handle klik scan di sini
                Toast.makeText(this, "Scan diklik", Toast.LENGTH_SHORT).show()
            }
            // Di Activity/Fragment
            val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
                    val toolbarTitle = findViewById<TextView>(R.id.toolbarTitle)

            // Update judul saat ganti halaman
            fun updateToolbarTitle(title: String) {
                toolbarTitle.text = title
            }

            // Contoh penggunaan:
            updateToolbarTitle("Batik Parang") // Untuk halaman Batik Parang
            updateToolbarTitle("Batik Megamendung") // Untuk halaman Megamendung
            backButton.setOnClickListener {
                onBackPressed() // Untuk Activity
                // atau
                // findNavController().navigateUp() // Untuk Navigation Component
            }
// Model data Batik
            data class Batik(
                    val id: String,
                    val name: String,
                    val imageUrl: String,
                    val origin: String,
                    val description: String
            )
            class BatikDetailActivity : AppCompatActivity() {
                private lateinit var binding: ActivityBatikDetailBinding

                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    binding = ActivityBatikDetailBinding.inflate(layoutInflater)
                    setContentView(binding.root)

                    // Dapatkan ID batik dari intent
                    val batikId = intent.getStringExtra("BATIK_ID") ?: ""

                    // Load data batik
                    loadBatikData(batikId)
                }

                private fun loadBatikData(batikId: String) {
                    lifecycleScope.launch {
                        try {
                            val response = BatikApi.instance.getBatikDetail(batikId)
                            if (response.isSuccessful) {
                                response.body()?.let { batik ->
                                        updateUI(batik)
                                }
                            }
                        } catch (e: Exception) {
                            Toast.makeText(this@BatikDetailActivity,
                            "Gagal memuat data", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                private fun updateUI(batik: Batik) {
                    // Update gambar menggunakan Glide/Picasso
                    Glide.with(this)
                            .load(batik.imageUrl)
                            .placeholder(R.drawable.placeholder_batik)
                            .into(binding.batikImg)

                    // Update teks
                    binding.batikName.text = batik.name
                    binding.originNameText.text = batik.origin

                    // Untuk caching (optional)
                    CacheManager.saveBatikToCache(this, batik)
                }
            }
            // Di adapter RecyclerView atau activity list
            fun onBatikItemClicked(batikId: String) {
                val intent = Intent(this, BatikDetailActivity::class.java).apply {
                    putExtra("BATIK_ID", batikId)
                }
                startActivity(intent)
            }

            data class Batik(
                    val id: String,
                    val name: String,
                    val imageUrl: String,
                    val origin: String,
                    val description: String,
                    val motif: String,
                    val history: String
            )
            class BatikDetailActivity : AppCompatActivity() {
                private lateinit var binding: ActivityBatikDetailBinding
                private val viewModel: BatikViewModel by viewModels()

                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    binding = ActivityBatikDetailBinding.inflate(layoutInflater)
                    setContentView(binding.root)

                    // Terima data batik yang dipilih
                    val batikId = intent.getStringExtra("BATIK_ID") ?: ""

                    // Setup toolbar
                    setupToolbar()

                    // Load data batik
                    viewModel.loadBatikDetail(batikId)
                    observeViewModel()
                }

                private fun setupToolbar() {
                    binding.toolbar.setNavigationOnClickListener {
                        onBackPressed()
                    }
                }

                private fun observeViewModel() {
                    viewModel.batikDetail.observe(this) { batik ->
                            batik?.let { updateUI(it) }
                    }

                    viewModel.isLoading.observe(this) { isLoading ->
                            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                    }

                    viewModel.errorMessage.observe(this) { error ->
                            error?.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                    }
                }

                private fun updateUI(batik: Batik) {
                    // Update Toolbar
                    binding.toolbarTitle.text = batik.name

                    // Update Image
                    Glide.with(this)
                            .load(batik.imageUrl)
                            .placeholder(R.drawable.placeholder_batik)
                            .error(R.drawable.error_batik)
                            .into(binding.batikImg)

                    // Update Text Views
                    binding.batikNameText.text = batik.name
                    binding.originNameText.text = batik.origin

                    // Untuk elemen lainnya bisa ditambahkan sesuai kebutuhan
                    binding.batikDescriptionText.text = batik.description
                    binding.batikMotifText.text = batik.motif
                    binding.batikHistoryText.text = batik.history
                }
            }
            // Di adapter RecyclerView atau activity list
            fun openBatikDetail(batikId: String) {
                val intent = Intent(this, BatikDetailActivity::class.java).apply {
                    putExtra("BATIK_ID", batikId)
                }
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
            // Di Activity/Fragment
            fun setBatikPhilosophy(philosophy: String) {
                binding.philosophyText.text = philosophy

                // Hitung tinggi minimal berdasarkan panjang teks
                val lineCount = binding.philosophyText.lineCount
                val minHeight = when {
                    lineCount > 10 -> resources.getDimensionPixelSize(R.dimen.long_text_height)
                    lineCount > 5 -> resources.getDimensionPixelSize(R.dimen.medium_text_height)
        else -> resources.getDimensionPixelSize(R.dimen.short_text_height)
                }

                binding.philosophyText.minHeight = minHeight
            }
            public class RelatedBatikAdapter extends RecyclerView.Adapter<RelatedBatikAdapter.ViewHolder> {
                private List<Batik> batikList;

                public RelatedBatikAdapter(List<Batik> batikList) {
                    this.batikList = batikList;
                }

                @Override
                public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_related_batik, parent, false);
                    return new ViewHolder(view);
                }

                @Override
                public void onBindViewHolder(ViewHolder holder, int position) {
                    Batik batik = batikList.get(position);
                    holder.imageView.setImageResource(batik.getImageResource());
                    holder.locationTextView.setText(batik.getLocation());
                }

                @Override
                public int getItemCount() {
                    return batikList.size();
                }

                public static class ViewHolder extends RecyclerView.ViewHolder {
                    public ImageView imageView;
                    public TextView locationTextView;

                    public ViewHolder(View itemView) {
                        super(itemView);
                        imageView = itemView.findViewById(R.id.relatedBatikImage);
                        locationTextView = itemView.findViewById(R.id.relatedBatikLocation);
                    }
                }
            }
            public class RelatedBatikAdapter extends RecyclerView.Adapter<RelatedBatikAdapter.ViewHolder> {
                private List<Batik> batikList;

                public RelatedBatikAdapter(List<Batik> batikList) {
                    this.batikList = batikList;
                }

                @Override
                public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_related_batik, parent, false);
                    return new ViewHolder(view);
                }

                @Override
                public void onBindViewHolder(ViewHolder holder, int position) {
                    Batik batik = batikList.get(position);
                    holder.imageView.setImageResource(batik.getImageResource());
                    holder.locationTextView.setText(batik.getLocation());
                }

                @Override
                public int getItemCount() {
                    return batikList.size();
                }

                public static class ViewHolder extends RecyclerView.ViewHolder {
                    public ImageView imageView;
                    public TextView locationTextView;

                    public ViewHolder(View itemView) {
                        super(itemView);
                        imageView = itemView.findViewById(R.id.relatedBatikImage);
                        locationTextView = itemView.findViewById(R.id.relatedBatikLocation);
                    }
                }
            }


        });
    }
}


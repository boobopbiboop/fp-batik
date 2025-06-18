package com.example.fp_batik;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class RelatedBatikAdapter extends RecyclerView.Adapter<RelatedBatikAdapter.ViewHolder> {

    private List<BatikItem> batikList;
    private Context context;

    public RelatedBatikAdapter(List<BatikItem> batikList, Context context) {
        this.batikList = batikList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_related_batik, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BatikItem batikItem = batikList.get(position);

        holder.batikName.setText(batikItem.getName());
        holder.batikOrigin.setText(batikItem.getOrigin());

        // Load image using Glide
        Glide.with(context)
                .load(batikItem.getImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(holder.batikImage);

        // Set click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BatikDetailActivity.class);
                intent.putExtra("batik_name", batikItem.getName());
                intent.putExtra("batik_origin", batikItem.getOrigin());
                intent.putExtra("batik_era", batikItem.getEra());
                intent.putExtra("batik_type", batikItem.getType());
                intent.putExtra("batik_meaning", batikItem.getMeaning());
                intent.putExtra("batik_image_url", batikItem.getImageUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return batikList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView batikImage;
        TextView batikName;
        TextView batikOrigin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            batikImage = itemView.findViewById(R.id.related_batik_image);
            batikName = itemView.findViewById(R.id.related_batik_name);
            batikOrigin = itemView.findViewById(R.id.related_batik_origin);
        }
    }
}

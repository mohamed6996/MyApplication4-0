package com.example.lenovo.myapplication;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 10/24/2016.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.VH> {
    List<ItemModel> mDataset;
    Context context;

    public ItemAdapter(List<ItemModel> mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @Override

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new VH(view, this.context, this.mDataset);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        ItemModel model = mDataset.get(position);

        String FULL_IMG = Constants.IMG_BASE + model.getImagePath();

        Glide.with(context).load(FULL_IMG).into(holder.imageView);
        holder.textView.setText(model.getFilm_name());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;

        Context vhContext;
        List<ItemModel> vhDataSet = new ArrayList<>();

        public VH(View itemView, Context context, List<ItemModel> vhDataSet) {
            super(itemView);

            this.vhContext = context;
            this.vhDataSet = vhDataSet;


            imageView = (ImageView) itemView.findViewById(R.id.img);
            imageView.setOnClickListener(this);

            textView = (TextView) itemView.findViewById(R.id.count);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            ItemModel m = vhDataSet.get(position);



            Intent intent = new Intent(this.vhContext, DetailActivity.class);
            intent.putExtra("OVER_VIEW_KEY",m.getOver_view());
            intent.putExtra("RELEASE_DATE_KEY", m.getRelease_date());
            intent.putExtra("VOTE_AVERAGE_KEY", m.getVote_average());
            intent.putExtra("IMAGE_KEY", m.getImagePath());
            this.vhContext.startActivity(intent);



        }
    }
}

package com.tejendramachinetest.imagetaskmodule;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tejendramachinetest.R;

import java.util.ArrayList;
import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> implements Filterable {

    private ArrayList<ImageListModel.HitsBean> hitsBeans;
    private ArrayList<ImageListModel.HitsBean> hitsBeansFiltered;
    private iImageClickListner iImageClickListnerl;

    ImageListAdapter(ArrayList<ImageListModel.HitsBean> hitsBeans, iImageClickListner iImageClickListnerl) {
        this.hitsBeans = hitsBeans;
        hitsBeansFiltered = hitsBeans;
        this.iImageClickListnerl = iImageClickListnerl;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        ImageListModel.HitsBean hitsBean = hitsBeansFiltered.get(position);


        holder.imageView.setImageBitmap(null);
        Glide.with(holder.imageView.getContext()).load(hitsBean.getWebformatURL()).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);

                return false;
            }
        }).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return hitsBeansFiltered.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    hitsBeansFiltered = hitsBeans;
                } else {
                    ArrayList<ImageListModel.HitsBean> filteredList = new ArrayList<>();
                    for (ImageListModel.HitsBean row : hitsBeans) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for user
                        if (row.getUser().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    hitsBeansFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = hitsBeansFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                hitsBeansFiltered = (ArrayList<ImageListModel.HitsBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        ProgressBar progressBar;
        CardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            progressBar = itemView.findViewById(R.id.progress_bar);
            cardView = itemView.findViewById(R.id.card_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iImageClickListnerl.onImageClick(getAdapterPosition(),hitsBeansFiltered);
                }
            });
        }
    }
}

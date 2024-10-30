package com.app.foodshipperapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodshipperapp.Model.ReviewShipper;
import com.app.foodshipperapp.R;

import java.util.List;

public class ReviewShipperAdapter extends RecyclerView.Adapter<ReviewShipperAdapter.ReviewViewHolder> {
    private List<ReviewShipper> reviews;
    private Context context;

    public ReviewShipperAdapter(List<ReviewShipper> reviews, Context context) {
        this.reviews = reviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_review_shipper, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewShipperAdapter.ReviewViewHolder holder, int position) {
        // Get the review at the current position
        ReviewShipper review = reviews.get(position);

        // Set the description and rating
        holder.textReviewShipper.setText(review.getComment());
        holder.ratingShipper.setText(String.valueOf(review.getRating()));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView textReviewShipper;
        TextView ratingShipper;
        ImageView imageStar;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views
            textReviewShipper = itemView.findViewById(R.id.textReviewShipper);
            ratingShipper = itemView.findViewById(R.id.ratingShipper);
            imageStar = itemView.findViewById(R.id.imageView6);
        }
    }
}

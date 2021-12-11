package org.techtown.capstone_final.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.capstone_final.Model.Review;
import org.techtown.capstone_final.R;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private static final String TAG = "RecordAdapter";
    private final ArrayList<Review> list;
    private final Context context;

    public ReviewAdapter(ArrayList<Review> reviewList, Context context) {
        this.list = reviewList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_reiview, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = list.get(position);
        holder.review.setText(review.getReview());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  review;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            review = itemView.findViewById(R.id.review);

        }
    }
    private  void startToast(String msg){ Toast.makeText(context, msg, Toast.LENGTH_SHORT).show(); }
}

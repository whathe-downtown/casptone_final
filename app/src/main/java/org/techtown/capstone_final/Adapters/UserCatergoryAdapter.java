package org.techtown.capstone_final.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.capstone_final.Model.UsersCategory;
import org.techtown.capstone_final.R;

import java.util.ArrayList;

public class UserCatergoryAdapter extends RecyclerView.Adapter<UserCatergoryAdapter.ViewHolder> {

    private static final String TAG = "UserCategoryAdapter";
    private final ArrayList<UsersCategory> list;
    private final Context context;

    public UserCatergoryAdapter(ArrayList<UsersCategory> categoryList, Context context) {
        this.list = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserCatergoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_chip, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UsersCategory category = list.get(position);
        holder.category.setText(category.getSubject());

        }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.categoryChip);

        }
    }
    private  void startToast(String msg){ Toast.makeText(context, msg, Toast.LENGTH_SHORT).show(); }
}

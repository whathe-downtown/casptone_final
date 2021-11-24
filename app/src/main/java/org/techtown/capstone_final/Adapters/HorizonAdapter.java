package org.techtown.capstone_final.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.techtown.capstone_final.Model.Users;
import org.techtown.capstone_final.R;

import java.util.ArrayList;

public class HorizonAdapter extends  RecyclerView.Adapter<HorizonAdapter.ViewHolder>{

    ArrayList<Users> list;
    Context context;



    public HorizonAdapter(ArrayList<Users> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.horizon_reclycer_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users = list.get(position);
        Picasso.get().load(users.getProfilepic()).placeholder(R.drawable.ic_group80).into(holder.image);
        holder.userName.setText(users.getName());

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView userName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.prefix);
            userName =itemView.findViewById(R.id.recylce_tv_name);
        }
    }
}

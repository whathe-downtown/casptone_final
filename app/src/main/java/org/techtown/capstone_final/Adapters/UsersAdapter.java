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

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {



    ArrayList<Users> list;
    Context context;

    //users에 들어가는 list context 가졍노다
    public UsersAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    // inflate즉 실제 보여지는 부위에 어떻게 올라가는지 엑티비티올라가는 부분

    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_invitation, parent,false);
        return new ViewHolder(view);// 매번 새롭게 초기화하는 느낌같은 왜냐면 데이터변형때문에
    }

    @Override // 실제 매칭해주는곳 각 아이템 마다 Glide 사용하기도함 하지만 우리는 picasso 활용
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users = list.get(position);  //users 리스트 포지션 잡고 피카소 모형에다가 이미지 삽입
        Picasso.get().load(users.getProfilepic()).placeholder(R.drawable.ic_group80).into(holder.image);
        holder.userName.setText(users.getName()); //username도 얻음
        holder.userinfo.setText(users.getUseroneinfo());



    }

    @Override
    public int getItemCount() {

        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView userName, userinfo;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.profile_image1);
            userName = itemView.findViewById(R.id.inviteUserName);
            userinfo = itemView.findViewById(R.id.inviteOneInfo);



        }
    }
}

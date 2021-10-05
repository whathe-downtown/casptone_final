package org.techtown.capstone_final.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.capstone_final.Model.Room;
import org.techtown.capstone_final.R;

import java.util.ArrayList;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {
    ArrayList<Room> list;
    Context context;


    public RoomsAdapter(ArrayList<Room> roomlist, Context context) {
        this.list= roomlist;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_list, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        Room room = list.get(position);  //users 리스트 포지션 잡고 피카소 모형에다가 이미지 삽입
       // Picasso.get().load(room.getRoomprofilepic()).placeholder(R.drawable.ic_user).into(holder.image);
        holder.roomname.setText(room.getRoomTitle(toString()));//username도 얻음
        holder.roomlocation.setText(room.getRoomPlace());
        holder.roomday.setText(room.getRoomDate());
    }

    @Override
    public int getItemCount(){

        return  list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView roomname, roomlocation, roomday;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            roomname = itemView.findViewById(R.id.Roomname);
            roomlocation = itemView.findViewById(R.id.RoomLocation);
            roomday = itemView.findViewById(R.id.RoomDay);

        }
    }
}


package org.techtown.capstone_final.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.techtown.capstone_final.Model.Room;
import org.techtown.capstone_final.R;

import java.util.ArrayList;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {
    ArrayList<Room> list;
    Context context;
    private static final String TAG = "RoomsAdapter";

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
        Picasso.get().load(room.getRoomprofilepic()).placeholder(R.drawable.ic_user).into(holder.image);
        holder.roomname.setText(room.getRoomTitle());//username도 얻음
        holder.roomlocation.setText(room.getRoomPlace());
        holder.roomday.setText(room.getRoomDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Roomiteem onclick");
                //Intent intent = new Intent(context, RoomseceondlistActivity.class);
                //intent.putExtra("RoomId", room.getRoomId());
               // intent.putExtra("Roomtitle",room.getRoomTitle());
                //intent.putExtra("Roomprofilepic",room.getRoomprofilepic());
                // intent.putExtra("Roomlocation",room.getRoomPlace());
                //intent.putExtra("Room");

                // context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){

        return  list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView roomname, roomlocation, roomday;
        ImageView image;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.profile_image);
            roomname = itemView.findViewById(R.id.Roomtitle);
            roomlocation = itemView.findViewById(R.id.RoomLocation);
            roomday = itemView.findViewById(R.id.RoomDay);

        }
    }
}

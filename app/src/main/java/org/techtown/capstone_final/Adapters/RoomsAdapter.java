
package org.techtown.capstone_final.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_room, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        Room room = list.get(position);  //users 리스트 포지션 잡고 피카소 모형에다가 이미지 삽입
        holder.roomname.setText(room.getRoomTitle());//username도 얻음
        holder.roomlocation.setText(room.getRoomlocation());
        holder.roomdate.setText(room.getRoomdate());

        holder.itemView.setTag(position); // 값 가져오고고

       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Roomitem onclick");
                startToast("itme을 클릭하셨습니다.");
                //Intent intent = new Intent(context, RoomseceondlistActivity.class);
                //intent.putExtra("RoomId", room.getRoomId());
               // intent.putExtra("Roomtitle",room.getRoomTitle());
                //intent.putExtra("Roomprofilepic",room.getRoomprofilepic());
                // intent.putExtra("Roomlocation",room.getRoomPlace());
                //intent.putExtra("Room");

                // context.startActivity(intent);
            }
        });
       holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               Log.d(TAG,"오래 누르면 방 상세페이지가 뜨게 만들것입니다.");
               startToast("item을 오래 클릭하셨습니다.");
               return false;
           }
       });
    }

    @Override
    public int getItemCount(){

        return  list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView roomname, roomlocation, roomdate;
        ImageView image;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);


            roomname = itemView.findViewById(R.id.roomname);
            roomlocation = itemView.findViewById(R.id.roomlocation);
            roomdate = itemView.findViewById(R.id.roomdate);

        }
    }
    private  void startToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}

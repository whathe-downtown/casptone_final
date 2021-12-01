package org.techtown.capstone_final.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.capstone_final.Model.Room;
import org.techtown.capstone_final.R;

import java.util.ArrayList;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {
    private final ArrayList<Room> list;
    private final Context context;
    private static final String TAG = "RoomsAdapter";
    private ImageView  bookmarkButton;
    int bookmark =0;

    public RoomsAdapter(ArrayList<Room> roomlist, Context context) {
        this.list= roomlist;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_room, parent,false);
        bookmarkButton=(ImageView)view.findViewById(R.id.bookmarkButton);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {

        Room room = list.get(position);  //users 리스트 포지션 잡고 피카소 모형에다가 이미지 삽입
        holder.roomname.setText(room.getRoomTitle());//username도 얻음
        holder.roomlocation.setText(room.getRoomlocation());
        holder.roomdate.setText(room.getRoomdate());
        holder.roomcategory.setText(room.getRoomcategory());
        holder.itemView.setTag(position); // 값 가져오고

//        switch (room.getRoomHeadcount()){
//            case "3" :
//                holder.profile_card4.setVisibility(View.VISIBLE);
//
//            case "2" :
//                holder.profile_card3.setVisibility(View.VISIBLE);
//
//            case "1" :
//                holder.profile_card2.setVisibility(View.VISIBLE);
//                holder.profile_card1.setVisibility(View.VISIBLE);
//                break;
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Roomitem onclick");
                startToast("itme을 클릭하셨습니다.");
//                Intent intent = new Intent( context , RoomDetailActivity.class);
//                intent.putExtra("",room.getUseruid());
//                context.startActivity(intent);
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

               if(bookmark ==0){
                    bookmarkButton.setImageResource(R.drawable.ic_ico_bookmark_orange);
                    bookmark ++;
               } else {
                   bookmarkButton.setImageResource(R.drawable.ic_ico_bookmark_gray);
                   bookmark --;
               }

              // Log.d(TAG,"오래 누르면 방 상세페이지가 뜨게 만들것입니다.");
              startToast("item을 오래 클릭하셨습니다.");
               return true;
           }
       });
    }

    @Override
    public int getItemCount(){

        return  list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView roomname, roomlocation, roomdate, roomcategory;
        TextView username1, username2,username3,username4,username5;
        ImageView user1, user2,user3,user4,user5;
        int maxperson, nowperson;
        LinearLayout profile_card1, profile_card2, profile_card3, profile_card4;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);


            roomname = itemView.findViewById(R.id.roomname);
            roomlocation = itemView.findViewById(R.id.roomlocation);
            roomdate = itemView.findViewById(R.id.roomdate);
            roomcategory = itemView.findViewById(R.id.roomcategory);
            profile_card1 = itemView.findViewById(R.id.profile_card1);
            profile_card2 = itemView.findViewById(R.id.profile_card2);
            profile_card3 = itemView.findViewById(R.id.profile_card3);
            profile_card4 = itemView.findViewById(R.id.profile_card4);






        }
    }
    private  void startToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}

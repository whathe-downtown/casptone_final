package org.techtown.capstone_final;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.techtown.capstone_final.databinding.ActivityRoomInfoPageBinding;

public class RoomDetailActivity extends AppCompatActivity {

    ActivityRoomInfoPageBinding binding;
    FirebaseFirestore db;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomInfoPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db= FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        String roomuser = auth.getUid();
        String roomuid = getIntent().getStringExtra("roomuid"); // roomuser == roomuid 삭제 수정 가능(버튼 visible)
        String roomtitle = getIntent().getStringExtra("roomtitle");
        String roomprofilepic = getIntent().getStringExtra("roomprofilepic");
        String receiveId =getIntent().getStringExtra("userId");
        String roomContent =getIntent().getStringExtra("roomContent");
        String roomcategory =getIntent().getStringExtra(" roomcategory");
        String roomdate=getIntent().getStringExtra("roomdate");
        String roomlocation=getIntent().getStringExtra("roomlocation");

        String roomTime=getIntent().getStringExtra("roomTime");
        String roomHeadcount=getIntent().getStringExtra("roomHeadcount");

        binding.roomName.setText(roomtitle);
        binding.roomInfo.setText(roomContent);
        binding.placeAndTume.setText(roomdate);
        Picasso.get().load(roomprofilepic).placeholder(R.drawable.ic_ico_profile).into(binding.profileImage);
        Picasso.get().load(roomprofilepic).placeholder(R.drawable.backgroundpage).into(binding.roomImg);

//        Map<String, Object> obj = new HashMap<>();
//        obj.put("roomprofilepic", uri.toString());
//        obj.put("roomTitle", roomname);
//        obj.put("roomContent", roominfo);
//        obj.put("roomcategory", roomcategory);
//        obj.put("roomdate", roomdate);
//        obj.put("roomTime", roomtime);
//        obj.put("roomlocation", roomlocation);
//        obj.put("roomlink", roomlink);
//        obj.put("roomuid", user.getUid());
//        obj.put("roomHeadcount", roomperson);



    }
}


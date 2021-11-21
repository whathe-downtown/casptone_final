package org.techtown.capstone_final.Detail.HomeDetail;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.techtown.capstone_final.R;
import org.techtown.capstone_final.databinding.ActivityMainRoomDetailBinding;

public class MainRoomDetailActivity extends AppCompatActivity {

    ActivityMainRoomDetailBinding binding;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    EditText roomname ,room_status;
    ImageView roomprofilepic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainRoomDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference("Room");

        roomname = (EditText) findViewById(R.id.Roomname);
        room_status =(EditText)findViewById(R.id.Room_status);
        roomprofilepic = (ImageView)findViewById(R.id.Roomprofilepic);


        auth = FirebaseAuth.getInstance();


        /*binding.makeroomDetailSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strcalendar =binding.calendar.getText().toString();
                String timeselect =binding.timeselect.getText().toString();
                String locationselect =binding.locationselect.getText().toString();
                String memberselect = binding.memberselect.getText().toString();
                String strRoomname = roomname.getText().toString(); // 여기서 깨짐 
                String strRoomImage = roomname.getText().toString();
                String strRoomstatus = room_status.getText().toString();

                Room room  = new Room();

                room.setRoomDate(strcalendar);
                room.setRoomTime(timeselect);
                room.setRoomPlace(locationselect);
                room.setRoomHeadcount(memberselect);
                room.setRoomTitle(strRoomname);
                room.setRoomprofilepic(strRoomImage);
                room.setRoomContent(strRoomstatus);

                Toast.makeText(MainRoomDetailActivity.this, "(" + strRoomname + ")방 생성에 성공하셨습니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainRoomDetailActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();

            }
        });
        binding.mypageDetailBackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainRoomDetailActivity.this , MakeRoomActivity.class);
                startActivity(intent);

            }
        });*/


    }
}
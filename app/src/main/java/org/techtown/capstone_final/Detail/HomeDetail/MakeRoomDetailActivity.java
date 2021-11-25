package org.techtown.capstone_final.Detail.HomeDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.techtown.capstone_final.R;
import org.techtown.capstone_final.RoomInfoPageActivity;
import org.techtown.capstone_final.databinding.ActivityMakeRoomDetailBinding;

import java.util.Calendar;

public class MakeRoomDetailActivity extends AppCompatActivity {

    ActivityMakeRoomDetailBinding binding;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    EditText roomname ,room_status;

    LinearLayout Layout_Get_Date,Layout_Get_Time,Layout_Get_place,Layout_Get_Personnel,Layout_Get_Link;
    TextView view_date,view_time,view_place,view_personnel,view_link;
    TimePicker timePicker;
    DatePicker datePicker;
    Calendar c;
    TextView getplace,getpersonnel,getlink;

    int dateState, timeState, placeState, personnelState, viewState = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMakeRoomDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference("Room");

        roomname = (EditText) findViewById(R.id.Roomname);
        room_status =(EditText)findViewById(R.id.Room_status);
        auth = FirebaseAuth.getInstance();

        /* id 찾기 */

        Layout_Get_Date = findViewById(R.id.Layout_Get_Date);
        Layout_Get_Time = findViewById(R.id.Layout_Get_Time);
        Layout_Get_place = findViewById(R.id.Layout_Get_place);
        Layout_Get_Personnel = findViewById(R.id.Layout_Get_Personnel);
        Layout_Get_Link = findViewById(R.id.Layout_Get_Link);

        view_date = findViewById(R.id.view_date);
        view_time = findViewById(R.id.view_time);
        view_place = findViewById(R.id.view_palce);
        view_personnel = findViewById(R.id.view_personnel);
        view_link = findViewById(R.id.view_link);

        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        getplace = findViewById(R.id.Get_place);
        getpersonnel = findViewById(R.id.Get_Personnel);



        auth = FirebaseAuth.getInstance();



        view_date.setText("언제 모이나요??");
        view_time.setText("몇시에 모이나요?");
        view_place.setText("어디소 모이나요?");
        view_personnel.setText("몇명이서 모이나요?");
        view_link.setText("채팅방 주소를 알려주세요");

        boolean open_date=true;

        binding.makeroomNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakeRoomDetailActivity.this, RoomInfoPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        binding.spreadDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Layout_Get_Date.setVisibility(Layout_Get_Date.VISIBLE);
                binding.spreadDate.setRotationX(180);
            }
        });

        binding.submitDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = datePicker.getYear();
                int month = (datePicker.getMonth()+1);
                int dayOfMonth = datePicker.getDayOfMonth();
                view_date.setText(year+"년"+month+"월"+dayOfMonth+"일");
                Layout_Get_Date.setVisibility(Layout_Get_Date.GONE);
                binding.spreadDate.setRotationX(0);
            }
        });

        /* 타임피커 피커 */

        binding.spreadTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Layout_Get_Time.setVisibility(Layout_Get_Time.VISIBLE);
                binding.spreadTime.setRotationX(180);
            }
        });

        binding.submitTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hourOfDay = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                view_time.setText(hourOfDay+"시"+minute+"분");

/*
                timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        view_time.setText(hourOfDay+"시"+minute+"분");
                    }
                });
*/
                Layout_Get_Time.setVisibility(Layout_Get_Time.GONE);
                binding.spreadTime.setRotationX(0);
            }
        });

        /* 장소 선택 */

        binding.spreadPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Layout_Get_place.setVisibility(Layout_Get_place.VISIBLE);
                binding.spreadPlace.setRotationX(180);
            }
        });

        binding.submitPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Layout_Get_place.setVisibility(Layout_Get_place.GONE);
                binding.spreadPlace.setRotationX(0);
                view_place.setText(getplace.getText());
            }
        });

        /* 인원 선택 */

        binding.spreadPersonnel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Layout_Get_Personnel.setVisibility(Layout_Get_Personnel.VISIBLE);
                binding.spreadPersonnel.setRotationX(180);
            }
        });

        binding.submitPersonnel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Layout_Get_Personnel.setVisibility(Layout_Get_Personnel.GONE);
                binding.spreadPersonnel.setRotationX(0);
                view_personnel.setText(getpersonnel.getText());
            }
        });

        /* 채팅방 링크 */

        binding.spreadLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Layout_Get_Link.setVisibility(Layout_Get_Link.VISIBLE);
                binding.spreadLink.setRotationX(180);
            }
        });
        binding.submitLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Layout_Get_Link.setVisibility(Layout_Get_Link.GONE);
                binding.spreadLink.setRotationX(0);
            }
        });



    }

}
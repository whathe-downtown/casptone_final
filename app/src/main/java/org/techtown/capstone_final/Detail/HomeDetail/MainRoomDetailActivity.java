package org.techtown.capstone_final.Detail.HomeDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import org.techtown.capstone_final.databinding.ActivityMainRoomDetailBinding;

import java.util.Calendar;

public class MainRoomDetailActivity extends AppCompatActivity {

    ActivityMainRoomDetailBinding binding;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    EditText roomname ,room_status;

    Button spread_date,spread_time,spread_place,spread_Personnel,spread_link;
    Button submit_date,submit_time,submit_place,submit_Personnel,submit_link;
    LinearLayout Layout_Get_Date,Layout_Get_Time,Layout_Get_place,Layout_Get_Personnel,Layout_Get_Link;
    TextView view_date,view_time,view_place,view_personnel,view_link;
    TimePicker timePicker;
    DatePicker datePicker;
    Calendar c;
    TextView getplace,getpersonnel,getlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainRoomDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference("Room");

        roomname = (EditText) findViewById(R.id.Roomname);
        room_status =(EditText)findViewById(R.id.Room_status);
        auth = FirebaseAuth.getInstance();

        /* id 찾기 */

        spread_date = findViewById(R.id.spread_date);
        spread_time = findViewById(R.id.spread_time);
        spread_place = findViewById(R.id.spread_place);
        spread_Personnel = findViewById(R.id.spread_Personnel);
        spread_link = findViewById(R.id.spread_Link);

        submit_date = findViewById(R.id.submit_date);
        submit_time = findViewById(R.id.submit_time);
        submit_place = findViewById(R.id.submit_place);
        submit_Personnel = findViewById(R.id.submit_personnel);
        submit_link = findViewById(R.id.submit_link);

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

        /* 데이터 피커 */

        spread_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(open_date) {
                    Layout_Get_Date.setVisibility(Layout_Get_Date.VISIBLE);

                }
                else{
                    Layout_Get_Date.setVisibility(Layout_Get_Date.GONE);
                }
                    spread_date.setRotationX(180);
            }
        });

        submit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = datePicker.getYear();
                int month = (datePicker.getMonth()+1);
                int dayOfMonth = datePicker.getDayOfMonth();
                view_date.setText(year+"년"+month+"월"+dayOfMonth+"일");
                Layout_Get_Date.setVisibility(Layout_Get_Date.GONE);
                spread_date.setRotationX(0);
            }
        });

        /* 타임피커 피커 */

        spread_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Layout_Get_Time.setVisibility(Layout_Get_Time.VISIBLE);
                spread_time.setRotationX(180);
            }
        });

        submit_time.setOnClickListener(new View.OnClickListener() {
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
                spread_time.setRotationX(0);
            }
        });

        /* 장소 선택 */

        spread_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Layout_Get_place.setVisibility(Layout_Get_place.VISIBLE);
                spread_place.setRotationX(180);
            }
        });

        submit_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Layout_Get_place.setVisibility(Layout_Get_place.GONE);
                spread_place.setRotationX(0);
                view_place.setText(getplace.getText());
            }
        });

        /* 인원 선택 */

        spread_Personnel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Layout_Get_Personnel.setVisibility(Layout_Get_Personnel.VISIBLE);
                spread_Personnel.setRotationX(180);
            }
        });

        submit_Personnel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Layout_Get_Personnel.setVisibility(Layout_Get_Personnel.GONE);
                spread_Personnel.setRotationX(0);
                view_personnel.setText(getpersonnel.getText());
            }
        });

        /* 채팅방 링크 */

        spread_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Layout_Get_Link.setVisibility(Layout_Get_Link.VISIBLE);
                spread_link.setRotationX(180);
            }
        });
        submit_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Layout_Get_Link.setVisibility(Layout_Get_Link.GONE);
                spread_link.setRotationX(0);
            }
        });



    }

}

package org.techtown.capstone_final.Detail.HomeDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.capstone_final.databinding.ActivityMakeRoomBinding;
import org.techtown.capstone_final.fragment.Home.HomeActivity;

public class MakeRoomActivity extends AppCompatActivity {

    ActivityMakeRoomBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMakeRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.makeroomNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Roomtitle = binding.Roomname.getText().toString();
                String Roomstatus = binding.RoomStatus.getText().toString();

                Intent intent = new Intent(MakeRoomActivity.this, MainRoomDetailActivity.class);
                intent.putExtra("Roomtitle", Roomtitle);
                intent.putExtra("Roomstatus", Roomstatus);
                startActivity(intent);
            }
        });

        binding.mypageDetailBackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakeRoomActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
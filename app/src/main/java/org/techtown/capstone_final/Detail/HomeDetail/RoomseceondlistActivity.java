package org.techtown.capstone_final.Detail.HomeDetail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.capstone_final.databinding.ActivityRoomseceondlistBinding;

public class RoomseceondlistActivity extends AppCompatActivity {

    ActivityRoomseceondlistBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomseceondlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
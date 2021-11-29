package org.techtown.capstone_final;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.capstone_final.databinding.ActivityRoomInfoPageBinding;

public class RoomDetailActivity extends AppCompatActivity {

    ActivityRoomInfoPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomInfoPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}


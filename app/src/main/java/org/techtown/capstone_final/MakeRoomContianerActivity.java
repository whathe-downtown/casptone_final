package org.techtown.capstone_final;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import org.techtown.capstone_final.databinding.ActivityMakeContainerBinding;

public class MakeRoomContianerActivity extends AppCompatActivity {
    ActivityMakeContainerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMakeContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }


}

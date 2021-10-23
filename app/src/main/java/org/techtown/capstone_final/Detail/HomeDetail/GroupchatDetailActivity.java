package org.techtown.capstone_final.Detail.HomeDetail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.capstone_final.databinding.ActivityGroupchatDetailBinding;

public class GroupchatDetailActivity extends AppCompatActivity {

    ActivityGroupchatDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupchatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
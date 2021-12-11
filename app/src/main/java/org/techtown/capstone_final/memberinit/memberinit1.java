package org.techtown.capstone_final.memberinit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.techtown.capstone_final.R;
import org.techtown.capstone_final.databinding.ActivityMemberinit1Binding;

import java.util.List;

public class memberinit1 extends AppCompatActivity {
    ActivityMemberinit1Binding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;

    private final String TAG = "memberinit1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemberinit1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ChipGroup chipGroup = (ChipGroup)findViewById(R.id.infacy_chip_group);

        binding.chipSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memberinit1.this, memberinit2.class);
                startActivity(intent);
            }
        });
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                List<Integer> ids = chipGroup.getCheckedChipIds();
                for (Integer id:ids){
                    Chip chip = chipGroup.findViewById(id);

                }

            }
        });

    }
}
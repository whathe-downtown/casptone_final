package org.techtown.capstone_final.memberinit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.techtown.capstone_final.R;

import java.util.List;

public class memberinit1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberinit1);

        ChipGroup chipGroup = (ChipGroup)findViewById(R.id.infacy_chip_group);

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
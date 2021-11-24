package org.techtown.capstone_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.capstone_final.databinding.ActivityMakeContainerBinding;
import org.techtown.capstone_final.fragment.Home.HomeActivity;

public class MakeRoomContianerActivity extends AppCompatActivity {
    ActivityMakeContainerBinding binding;
    View layout1 = findViewById(R.id.make_room1);
    View layout2 = findViewById(R.id.make_room2);
    Button one = findViewById(R.id.makeroom_next);
    Button two = findViewById(R.id.makeroom_next2);
    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMakeContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                page++;
            }
        });

        //CASE 첫 번째에서 backpresdded누르는 경우
        //CASE 두 번재에서 backpresddd누르는경우


    }
    public void onBackPressed() {
        super.onBackPressed();
        if (page ==2){
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
            page--;
        }else{
            Intent intent = new Intent(MakeRoomContianerActivity.this, HomeActivity.class);
            startActivity(intent);
        }

    }
}
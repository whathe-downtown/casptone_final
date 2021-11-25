package org.techtown.capstone_final;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.capstone_final.databinding.ActivityMakeContainerBinding;

import static android.service.controls.ControlsProviderService.TAG;

public class MakeRoomContianerActivity extends AppCompatActivity {
    ActivityMakeContainerBinding binding;

    int page = 1;
    int infoState=1;
    int state[] = {0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMakeContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        // VISBILE UNVISBLE 로직
        binding.makeRoom1.makeroomNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"next_step_one_Button_Click");
                binding.makeRoom1.getRoot().setVisibility(View.GONE);
                binding.makeRoom2.getRoot().setVisibility(View.VISIBLE);
                page++;

            }
        });
        binding.makeRoom2.makeroomNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"방 두번째 클릭");
                Intent intent = new Intent(MakeRoomContianerActivity.this, RoomInfoPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 첫 번째 방에 담는 content 로직
        binding.makeRoom1.spreadInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infoState == 1){
                    binding.makeRoom1.LayoutGetInfo.setVisibility(View.VISIBLE);
                    binding.makeRoom1.spreadInfo.setRotation(180);
                    infoState--; }
                else{
                    binding.makeRoom1.LayoutGetInfo.setVisibility(View.GONE);
                    binding.makeRoom1.spreadInfo.setRotation(0);
                    infoState++; }
            }
        });

        binding.makeRoom1.submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.makeRoom1.viewInfo.setText(binding.makeRoom1.RoomStatus.getText());
                binding.makeRoom1.LayoutGetInfo.setVisibility(View.GONE);
                binding.makeRoom1.spreadInfo.setRotation(0);
                infoState++;
            }
        });
        // 첫 번째 방 이미지 클릭했을때
        binding.makeRoom1.Roomprofilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 34);
            }
        });

        //  두 번째 방 로직 ------------------------------------------------------------------------------

        binding.makeRoom2.viewDate.setText("언제 모이나요??");
        binding.makeRoom2.viewTime.setText("몇시에 모이나요?");
        binding.makeRoom2.viewPalce.setText("어디소 모이나요?");
        binding.makeRoom2.viewPersonnel.setText("몇명이서 모이나요?");
        binding.makeRoom2.viewLink.setText("채팅방 주소를 알려주세요");


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.spread_date:
                        if(state[0]==0){
                            binding.makeRoom2.LayoutGetDate.setVisibility(View.VISIBLE);
                            binding.makeRoom2.spreadDate.setRotation(180);
                            state[0]++;}
                        else{
                            binding.makeRoom2.LayoutGetDate.setVisibility(View.GONE);
                            binding.makeRoom2.spreadDate.setRotation(0);
                            state[0]--;}
                        break;
                    case R.id.spread_time:
                        if(state[1]==0){
                            binding.makeRoom2.LayoutGetTime.setVisibility(View.VISIBLE);
                            binding.makeRoom2.spreadTime.setRotation(180);
                            state[1]++;}
                        else{
                            binding.makeRoom2.LayoutGetTime.setVisibility(View.GONE);
                            binding.makeRoom2.spreadTime.setRotation(0);
                            state[1]--;}
                        break;
                    case R.id.spread_place:
                        if(state[2]==0){
                            binding.makeRoom2.LayoutGetPlace.setVisibility(View.VISIBLE);
                            binding.makeRoom2.spreadPlace.setRotation(180);
                            state[2]++;}
                        else{
                            binding.makeRoom2.LayoutGetPlace.setVisibility(View.GONE);
                            binding.makeRoom2.spreadPlace.setRotation(0);
                            state[2]--;}
                        break;
                    case R.id.spread_Personnel:
                        if(state[3]==0){
                            binding.makeRoom2.LayoutGetPersonnel.setVisibility(View.VISIBLE);
                            binding.makeRoom2.spreadPersonnel.setRotation(180);
                            state[3]++;}
                        else{
                            binding.makeRoom2.LayoutGetPersonnel.setVisibility(View.GONE);
                            binding.makeRoom2.spreadPersonnel.setRotation(0);
                            state[3]--;}
                        break;
                    case R.id.spread_Link:
                        if(state[4]==0){
                            binding.makeRoom2.LayoutGetLink.setVisibility(View.VISIBLE);
                            binding.makeRoom2.spreadLink.setRotation(180);
                            state[4]++;}
                        else{
                            binding.makeRoom2.LayoutGetLink.setVisibility(View.GONE);
                            binding.makeRoom2.spreadLink.setRotation(0);
                            state[4]--;}
                        break;

                    case R.id.submit_date:
                        int Year = binding.makeRoom2.datePicker.getYear();
                        int Month = binding.makeRoom2.datePicker.getMonth();
                        int DayOfMonth = binding.makeRoom2.datePicker.getDayOfMonth();
                        binding.makeRoom2.viewDate.setText(Year + "년 " + Month + "월 " + DayOfMonth + "일" );

                        break;
                    case R.id.submit_time:
                        int hourOfDay = binding.makeRoom2.timePicker.getCurrentHour();
                        int minute = binding.makeRoom2.timePicker.getCurrentMinute();
                        binding.makeRoom2.viewTime.setText(hourOfDay+"시 "+minute+"분");

                        break;
                    case R.id.submit_place:
                        
                        break;
                    case R.id.submit_personnel:

                        break;
                    case R.id.submit_link:

                        break;
                }
            }
        };

        findViewById(R.id.spread_date).setOnClickListener(onClickListener);
        findViewById(R.id.spread_time).setOnClickListener(onClickListener);
        findViewById(R.id.spread_place).setOnClickListener(onClickListener);
        findViewById(R.id.spread_Personnel).setOnClickListener(onClickListener);
        findViewById(R.id.spread_Link).setOnClickListener(onClickListener);
        findViewById(R.id.submit_date).setOnClickListener(onClickListener);
        findViewById(R.id.submit_time).setOnClickListener(onClickListener);
        findViewById(R.id.submit_place).setOnClickListener(onClickListener);
        findViewById(R.id.submit_personnel).setOnClickListener(onClickListener);
        findViewById(R.id.submit_link).setOnClickListener(onClickListener);


/*
        //날짜 선택
        binding.makeRoom2.spreadDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.makeRoom2.LayoutGetDate.setVisibility(binding.makeRoom2.LayoutGetDate.VISIBLE);
                binding.makeRoom2.spreadDate.setRotationX(180);
            }
        });



        binding.makeRoom2.submitTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hourOfDay = binding.makeRoom2.timePicker.getCurrentHour();
                int minute = binding.makeRoom2.timePicker.getCurrentMinute();
                binding.makeRoom2.viewTime.setText(hourOfDay+"시"+minute+"분");


                timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        view_time.setText(hourOfDay+"시"+minute+"분");
                    }
                });

                binding.makeRoom2.LayoutGetTime.setVisibility(binding.makeRoom2.LayoutGetTime.GONE);
                binding.makeRoom2.spreadTime.setRotationX(0);
            }
        });
        //장소 선택

        binding.makeRoom2.spreadPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.makeRoom2.LayoutGetPlace.setVisibility(binding.makeRoom2.LayoutGetPlace.VISIBLE);
                binding.makeRoom2.spreadPlace.setRotationX(180);
            }
        });

        binding.makeRoom2.submitPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.makeRoom2.LayoutGetPlace.setVisibility(binding.makeRoom2.LayoutGetPlace.GONE);
                binding.makeRoom2.spreadPlace.setRotationX(0);
                binding.makeRoom2.viewPalce.setText(binding.makeRoom2.GetPersonnel.getText());
            }
        });

        // 인원 선택

        binding.makeRoom2.spreadPersonnel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.makeRoom2.LayoutGetPersonnel.setVisibility( binding.makeRoom2.LayoutGetPersonnel.VISIBLE);
                binding.makeRoom2.spreadPersonnel.setRotationX(180);
            }
        });

        binding.makeRoom2.submitPersonnel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.makeRoom2.LayoutGetPersonnel.setVisibility(binding.makeRoom2.LayoutGetPersonnel.GONE);
                binding.makeRoom2.spreadPersonnel.setRotationX(0);
                binding.makeRoom2.viewPersonnel.setText(binding.makeRoom2.GetPersonnel.getText());
            }
        });

        //채팅방 링크

        binding.makeRoom2.spreadLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.makeRoom2.LayoutGetLink.setVisibility(  binding.makeRoom2.LayoutGetLink.VISIBLE);
                binding.makeRoom2.spreadLink.setRotationX(180);
            }
        });
        binding.makeRoom2.submitLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.makeRoom2.LayoutGetLink.setVisibility(binding.makeRoom2.LayoutGetLink.GONE);
                binding.makeRoom2.spreadLink.setRotationX(0);
            }
        });

    }
    public void onBackPressed() {
        super.onBackPressed();
        if (page ==2){
            binding.makeRoom1.getRoot().setVisibility(View.VISIBLE);
            binding.makeRoom2.getRoot().setVisibility(View.GONE);
            page--;
        }else{
            Intent intent = new Intent(MakeRoomContianerActivity.this, HomeActivity.class);
            startActivity(intent);
        }
*/  }

}
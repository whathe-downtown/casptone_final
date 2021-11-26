package org.techtown.capstone_final;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.techtown.capstone_final.databinding.ActivityMakeContainerBinding;

import java.util.HashMap;
import java.util.Map;

public class MakeRoomContianerActivity extends AppCompatActivity {
    ActivityMakeContainerBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseStorage storage;
    int people = 1;
    int page = 1;
    int infoState=1;
    int state[] = {0,0,0,0,0},button_checked[] = {0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMakeContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

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


        //  두 번째 방 로직 ------------------------------------------------------------------------------
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
        findViewById(R.id.button_online).setOnClickListener(onClickListener);
        findViewById(R.id.button_offline).setOnClickListener(onClickListener);
        findViewById(R.id.button_individual).setOnClickListener(onClickListener);
        findViewById(R.id.button_Group).setOnClickListener(onClickListener);

        // 사용자 정보 로직
        findViewById(R.id.Roomprofilepic).setOnClickListener(onClickListener);
        findViewById(R.id.makeroom_next2).setOnClickListener(onClickListener);
        binding.makeRoom2.viewDate.setText("언제 모이나요??");
        binding.makeRoom2.viewTime.setText("몇시에 모이나요?");
        binding.makeRoom2.viewPalce.setText("어디서 모이나요?");
        binding.makeRoom2.viewPersonnel.setText("몇명이서 모이나요?");
        binding.makeRoom2.viewLink.setText("채팅방 주소를 알려주세요");
    }
    private void Roomprofileupdate(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 33);
    }
    private void save(){
        String roomname = binding.makeRoom1.Roomname.getText().toString();
        String roominfo = binding.makeRoom1.RoomStatus.getText().toString();
        String roomcategory = binding.makeRoom1.spinnerItem.getSelectedItem().toString();
        String roomdate = binding.makeRoom2.viewDate.getText().toString();
        String roomtime = binding.makeRoom2.viewDate.getText().toString();
        String roomlocation =binding.makeRoom2.viewPalce.getText().toString();
        String roomlink = binding.makeRoom2.viewLink.getText().toString();

        if(roomname.length()>0 && roominfo.length()>0 && roomcategory.length()>0 &&roomdate.length()>0 &&roomtime.length()>0 &&roomlocation.length()>0 &&roomlink.length()>0 ){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            Map<String, Object> obj = new HashMap<>();
            obj.put("roomTitle",roomname);
            obj.put("roomContent",roominfo);
            obj.put("roomcategory",roomcategory);
            obj.put("roomdate",roomdate);
            obj.put("roomTime",roomtime);
            obj.put("roomlocation",roomlocation);
            obj.put("roomlink",roomlink);
            if (people ==1){
            db.collection("Room").document("1:1").update(obj)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            startToast("저장이 완료 되었습니다");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    startToast("저장을 실패 하였습니다");
                }
            });
            }else{
                db.collection("Room").document("1:N").update(obj)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                startToast("저장이 완료 되었습니다");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("저장을 실패 하였습니다");
                    }
                });
            }

        }else{
            startToast("방 내용을 모두 채워주세요");
        }
//        this.roomId = roomId;
//        this.roomprofilepic = roomprofilepic;
//        this.publisher = publisher;
//        this.roomTitle = roomTitle;
//
//        this.roomcategory = roomcategory;
//        this.roomContent = roomContent;
//        this.roomdate = roomdate;
//        this.roomTime = roomTime;
//        this.roomlocation = roomlocation;
//        this.roomHeadcount = roomHeadcount;
    }
    private void get_Button_checked (int PP, int what){

        button_checked[PP] = what;

        if(PP==0){
            if (button_checked[0]==0){
            binding.makeRoom2.GetPlace.setVisibility(View.GONE);
            }
            else if(button_checked[0]==1){
                binding.makeRoom2.GetPlace.setVisibility(View.GONE);
            }
            else if(button_checked[0]==2){
                binding.makeRoom2.GetPlace.setVisibility(View.VISIBLE);
            }
        }
        else if(PP==1){
            if (button_checked[1]==0){
               binding.makeRoom2.GetPersonnel.setVisibility(View.GONE);
            }
            else if(button_checked[1]==1){
                binding.makeRoom2.GetPersonnel.setVisibility(View.GONE);
            }
            else if(button_checked[1]==2){
                binding.makeRoom2.GetPersonnel.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null) {
            // sFile은  URL에 사진(데이터)를 넣어서 http 형식으로 storge 저장 하게 만들기
            Uri sFile = data.getData();
            binding.makeRoom1.Roomprofilepic.setImageURI(sFile);

            storage = FirebaseStorage.getInstance();
            final StorageReference reference = storage.getReference().child("profile pictures")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                        @Override
                        public void onSuccess(Uri uri) {
                            db.collection("users").document(FirebaseAuth.getInstance().getUid()).update("profilepic",uri.toString())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            startToast("성공적으로 이미지가 업로드 완료되었습니다.");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            startToast("이미지가 업로드 되지 않았습니다.");
                                        }
                                    });

                        }
                    });

                }
            });
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Roomprofilepic:
                    Roomprofileupdate();
                    break;

                case R.id.makeroom_next2:
                    save();
                    break;

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
                    binding.makeRoom2.LayoutGetDate.setVisibility(View.GONE);
                    binding.makeRoom2.spreadDate.setRotation(0);
                    state[0]--;

                    break;
                case R.id.submit_time:
                    int hourOfDay = binding.makeRoom2.timePicker.getCurrentHour();
                    int minute = binding.makeRoom2.timePicker.getCurrentMinute();
                    binding.makeRoom2.viewTime.setText(hourOfDay+"시 "+minute+"분");
                    binding.makeRoom2.LayoutGetTime.setVisibility(View.GONE);
                    binding.makeRoom2.spreadTime.setRotation(0);
                    state[1]--;

                    break;
                case R.id.submit_place:
                    if(button_checked[0]==0){
                        binding.makeRoom2.viewPalce.setText("어디서 모이나요?");
                    }
                    else if(button_checked[0]==1){
                    binding.makeRoom2.viewPalce.setText("온라인");
                    }
                    else{
                        binding.makeRoom2.viewPalce.setText(binding.makeRoom2.GetPlace.getText());
                    }
                    binding.makeRoom2.LayoutGetPlace.setVisibility(View.GONE);
                    binding.makeRoom2.spreadPlace.setRotation(0);
                    state[2]--;
                    break;
                case R.id.submit_personnel:
                    if(button_checked[1]==0){
                        binding.makeRoom2.viewPersonnel.setText("몇명이서 모이나요?");
                    }
                    else if(button_checked[1]==1){
                        binding.makeRoom2.viewPersonnel.setText("1");
                    }
                    else{
                        binding.makeRoom2.viewPersonnel.setText(binding.makeRoom2.GetPersonnel.getText());
                    }
                    binding.makeRoom2.LayoutGetPersonnel.setVisibility(View.GONE);
                    binding.makeRoom2.spreadPersonnel.setRotation(0);
                    state[3]--;
                    break;
                case R.id.submit_link:
                    binding.makeRoom2.viewLink.setText(binding.makeRoom2.GetLink.getText());
                    binding.makeRoom2.LayoutGetLink.setVisibility(View.GONE);
                    binding.makeRoom2.spreadLink.setRotation(0);
                    state[4]--;
                    break;

                case R.id.button_online:
                    get_Button_checked (0,1);
                    break;

                case R.id.button_offline:
                    get_Button_checked (0,2);
                    break;

                case R.id.button_individual:
                    get_Button_checked (1,1);
                    break;

                case R.id.button_Group:
                    get_Button_checked (1,2);
                    break;
            }
        }

    };



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
    */
    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
}
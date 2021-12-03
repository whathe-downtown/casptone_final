package org.techtown.capstone_final.Detail.MypageDetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import org.techtown.capstone_final.R;
import org.techtown.capstone_final.databinding.ActivityMypageDetailBinding;
import org.techtown.capstone_final.fragment.mypage.MypageActivity;

import java.util.HashMap;
import java.util.Map;

public class MypageDetailActivity extends AppCompatActivity {

    ActivityMypageDetailBinding binding;
    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseFirestore db;
    int[] ChipArray = new int[14];
    String testText ="";
    int ChipMax = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMypageDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        findViewById(R.id.mypage_detail_profileImage).setOnClickListener(onClickListener);
        findViewById(R.id.profile_update).setOnClickListener(onClickListener);
        findViewById(R.id.basic1).setOnClickListener(onClickListener);
        findViewById(R.id.basic2).setOnClickListener(onClickListener);
        findViewById(R.id.basic3).setOnClickListener(onClickListener);
        findViewById(R.id.basic4).setOnClickListener(onClickListener);
        findViewById(R.id.basic5).setOnClickListener(onClickListener);
        findViewById(R.id.basic6).setOnClickListener(onClickListener);
        findViewById(R.id.basic7).setOnClickListener(onClickListener);
        findViewById(R.id.basic8).setOnClickListener(onClickListener);
        findViewById(R.id.basic9).setOnClickListener(onClickListener);
        findViewById(R.id.basic10).setOnClickListener(onClickListener);
        findViewById(R.id.basic11).setOnClickListener(onClickListener);
        findViewById(R.id.basic12).setOnClickListener(onClickListener);
        findViewById(R.id.basic13).setOnClickListener(onClickListener);
        findViewById(R.id.basic14).setOnClickListener(onClickListener);



    }
    public void ChipChecked (int cartegory){
        int ChipCount=0;

        if(ChipArray[cartegory-1]==0)
            ChipArray[cartegory-1]++;
        else if(ChipArray[cartegory-1]==1)
            ChipArray[cartegory-1]--;
        testText = "";
        for (int i=0; i<14 ; i++)
        {
            testText = testText + Integer.toString(ChipArray[i]) + "/";
            ChipCount += ChipArray[i] ;
            if(ChipCount>=ChipMax){
                if(ChipArray[0]==0) binding.basic1.setEnabled(false);if(ChipArray[1]==0) binding.basic2.setEnabled(false);
                if(ChipArray[2]==0) binding.basic3.setEnabled(false);if(ChipArray[3]==0) binding.basic4.setEnabled(false);
                if(ChipArray[4]==0) binding.basic5.setEnabled(false);if(ChipArray[5]==0) binding.basic6.setEnabled(false);
                if(ChipArray[6]==0) binding.basic7.setEnabled(false);if(ChipArray[7]==0) binding.basic8.setEnabled(false);
                if(ChipArray[8]==0) binding.basic9.setEnabled(false);if(ChipArray[9]==0) binding.basic10.setEnabled(false);
                if(ChipArray[10]==0) binding.basic11.setEnabled(false);if(ChipArray[11]==0) binding.basic12.setEnabled(false);
                if(ChipArray[12]==0) binding.basic13.setEnabled(false);if(ChipArray[13]==0) binding.basic14.setEnabled(false);
            } else {
                binding.basic1.setEnabled(true);binding.basic2.setEnabled(true);binding.basic3.setEnabled(true);
                binding.basic4.setEnabled(true);binding.basic5.setEnabled(true);binding.basic6.setEnabled(true);
                binding.basic7.setEnabled(true);binding.basic8.setEnabled(true);binding.basic9.setEnabled(true);
                binding.basic10.setEnabled(true);binding.basic11.setEnabled(true);binding.basic12.setEnabled(true);
                binding.basic13.setEnabled(true);binding.basic14.setEnabled(true);
            }
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.basic1 :
                    ChipChecked(1);
                    break;
                case R.id.basic2 :
                    ChipChecked(2);
                    break;
                case R.id.basic3 :
                    ChipChecked(3);
                    break;
                case R.id.basic4 :
                    ChipChecked(4);
                    break;
                case R.id.basic5 :
                    ChipChecked(5);
                    break;
                case R.id.basic6 :
                    ChipChecked(6);
                    break;
                case R.id.basic7 :
                    ChipChecked(7);
                    break;
                case R.id.basic8 :
                    ChipChecked(8);
                    break;
                case R.id.basic9 :
                    ChipChecked(9);
                    break;
                case R.id.basic10 :
                    ChipChecked(10);
                    break;
                case R.id.basic11 :
                    ChipChecked(11);
                    break;
                case R.id.basic12 :
                    ChipChecked(12);
                    break;
                case R.id.basic13 :
                    ChipChecked(13);
                    break;
                case R.id.basic14 :
                    ChipChecked(14);
                    break;


                case R.id.mypage_detail_profileImage:
                    profileUpdate();
                    break;

                case R.id.profile_update:
                    userinfo();
                    Intent intent = new Intent(MypageDetailActivity.this, MypageActivity.class);
                    startActivity(intent);
                    finish();
                    break;

            }
        }
    };

    private void back(){
        Intent intent = new Intent(MypageDetailActivity.this, MypageActivity.class);
        startActivity(intent);
        finish();
    }
    private void profileUpdate(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 33);
    }


    //데이터 값을 받아 오기위한 로직
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null) {

            // sFile은  URL에 사진(데이터)를 넣어서 http 형식으로 storge 저장 하게 만들기
            Uri sFile = data.getData();
            binding.mypageDetailProfileImage.setImageURI(sFile);

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

    private void userinfo(){
        String username = binding.userName.getText().toString();
        String status = binding.status.getText().toString();
        String userhistory = binding.career.getText().toString();

        if(username.length()>0 && userhistory.length()>0 && status.length()>0 ){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            Map<String, Object> obj = new HashMap<>();
            obj.put("name",username);
            obj.put("status",status);
            obj.put("userhistory",userhistory);

            db.collection("users").document(user.getUid()).update(obj)
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
    }
    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
}
//이미지상세피이지 뒤로가기 버튼 로직
//        binding.mypageDetailBackarrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MypageDetailActivity.this, MypageActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        //이미지 세이브버튼 로직
//        binding.mypageDetailSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                String username = binding.username.getText().toString();
////                String status = binding.status.getText().toString();
////                String profilepic=binding.profilepic.getText().toString();
////                String userhistory = binding.userhistory.getText().toString();
////
////                if(username.length()>0 && profilepic.length()>0 && userhistory.length()>0 && status.length()>0 ){
////                    FirebaseFirestore db = FirebaseFirestore.getInstance();
////                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////
////                    Users users = new Users(username, status,profilepic,userhistory);
////                            db.collection("users").document(user.getUid()).set(users)
////                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
////                                        @Override
////                                        public void onSuccess(Void unused) {
////                                            startToast("저장이 완료되었습니다.");
////                                        }
////                                    })
////                                    .addOnFailureListener(new OnFailureListener() {
////                                        @Override
////                                        public void onFailure(@NonNull Exception e) {
////                                            startToast("저장을 실패하였습니다");
////                                        }
////                                    });
////                }else{
////                    startToast("회원정보를 입력해주세요");
////                }
//
//                //파이어베이스 DB 안에 userName -> HashMap으로 변경하는거
//
////                HashMap<String, Object> obj = new HashMap<>();
////                obj.put("userName", username);
////                obj.put("status", status);
//
//                //obj에 만든것들을 update 해주는 작업업
////               database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
////                        .updateChildren(obj);
//                Toast.makeText(MypageDetailActivity.this, "프로필 업데이트 완료", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //자기소개서 내용 올리기
////        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
////                .addListenerForSingleValueEvent(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
////                        Users users = snapshot.getValue(Users.class);
////                        Picasso.get()
////                                .load(users.getProfilepic())
////                                .placeholder(R.drawable.ic_user)
////                                .into(binding.profileImage);
////                        binding.status.setText(users.getUserinfo());
////                        binding.username.setText(users.getName());
////
////
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull  DatabaseError error) {
////
////                    }
////                });
//        //프로파일 이미지클릭시 자신의 이미지 파일에서 사진 가져오기
//        binding.profileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(intent, 33);
//            }
//        });
//    }
//
//    //데이터 값을 받아 오기위한 로직
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(data.getData()!=null){
//
//            // sFile은  URL에 사진(데이터)를 넣어서 http 형식으로 storge 저장 하게 만들기
//            Uri sFile =data.getData();
//            binding.profileImage.setImageURI(sFile);
//
//            storage = FirebaseStorage.getInstance();
//            final StorageReference reference = storage.getReference().child("profile pictures")
//                    .child(FirebaseAuth.getInstance().getUid());
//
//            //이미지 storage URl을 가져와서 유저리스트의 이미지를 다른곳 뿌려주는거
//            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        // 이미지 파
//                        public void onSuccess(Uri uri) {
//
//
//                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                            FirebaseFirestore db = FirebaseFirestore.getInstance();
//                            db.collection("userpick").document(user.getUid()).set(uri.toString());
//
//                            Toast.makeText(MypageDetailActivity.this, "이미지가 업로드 되었습니다.", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(getApplicationContext(), "다운로드 실패", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                }
//            });
//
//        }
//    }

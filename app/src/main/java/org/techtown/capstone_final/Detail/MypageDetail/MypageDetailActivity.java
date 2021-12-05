package org.techtown.capstone_final.Detail.MypageDetail;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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
    protected Uri sFile;
    String[] chip = {"인문","미술","법과","경영","음악","공과","정보","농과","체육","수산","예술","사회과학","자연과학","생할과학"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMypageDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        getUserInfo();

        findViewById(R.id.mypage_detail_profileImage).setOnClickListener(onClickListener);
        findViewById(R.id.profile_update).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic1).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic2).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic3).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic4).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic5).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic6).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic7).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic8).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic9).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic10).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic11).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic12).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic13).setOnClickListener(onClickListener);
        findViewById(R.id.mbasic14).setOnClickListener(onClickListener);

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
            if(ChipCount>=ChipMax){ //Chip max = 3 값 3개만 눌린다.
                if(ChipArray[0]==0) binding.mbasic1.setEnabled(false);if(ChipArray[1]==0) binding.mbasic2.setEnabled(false);
                if(ChipArray[2]==0) binding.mbasic3.setEnabled(false);if(ChipArray[3]==0) binding.mbasic4.setEnabled(false);
                if(ChipArray[4]==0) binding.mbasic5.setEnabled(false);if(ChipArray[5]==0) binding.mbasic6.setEnabled(false);
                if(ChipArray[6]==0) binding.mbasic7.setEnabled(false);if(ChipArray[7]==0) binding.mbasic8.setEnabled(false);
                if(ChipArray[8]==0) binding.mbasic9.setEnabled(false);if(ChipArray[9]==0) binding.mbasic10.setEnabled(false);
                if(ChipArray[10]==0) binding.mbasic11.setEnabled(false);if(ChipArray[11]==0) binding.mbasic12.setEnabled(false);
                if(ChipArray[12]==0) binding.mbasic13.setEnabled(false);if(ChipArray[13]==0) binding.mbasic14.setEnabled(false);
            } else {
                binding.mbasic1.setEnabled(true);binding.mbasic2.setEnabled(true);binding.mbasic3.setEnabled(true);
                binding.mbasic4.setEnabled(true);binding.mbasic5.setEnabled(true);binding.mbasic6.setEnabled(true);
                binding.mbasic7.setEnabled(true);binding.mbasic8.setEnabled(true);binding.mbasic9.setEnabled(true);
                binding.mbasic10.setEnabled(true);binding.mbasic11.setEnabled(true);binding.mbasic12.setEnabled(true);
                binding.mbasic13.setEnabled(true);binding.mbasic14.setEnabled(true);
            }
        }

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mbasic1 :
                    ChipChecked(1);
                    break;
                case R.id.mbasic2 :
                    ChipChecked(2);
                    break;
                case R.id.mbasic3 :
                    ChipChecked(3);
                    break;
                case R.id.mbasic4 :
                    ChipChecked(4);
                    break;
                case R.id.mbasic5 :
                    ChipChecked(5);
                    break;
                case R.id.mbasic6 :
                    ChipChecked(6);
                    break;
                case R.id.mbasic7 :
                    ChipChecked(7);
                    break;
                case R.id.mbasic8 :
                    ChipChecked(8);
                    break;
                case R.id.mbasic9 :
                    ChipChecked(9);
                    break;
                case R.id.mbasic10 :
                    ChipChecked(10);
                    break;
                case R.id.mbasic11 :
                    ChipChecked(11);
                    break;
                case R.id.mbasic12 :
                    ChipChecked(12);
                    break;
                case R.id.mbasic13 :
                    ChipChecked(13);
                    break;
                case R.id.mbasic14 :
                    ChipChecked(14);
                    break;


                case R.id.mypage_detail_profileImage:
                    profileUpdate();
                    break;

                case R.id.profile_update:
                    userinfo();
                    chipsave();
                    Intent intent = new Intent(MypageDetailActivity.this,MypageActivity.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    break;

            }
        }
    };
    private void getUserInfo(){
        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getUid());

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    Log.d("MypageDetailActivitiy", "Current data: " + document.getData());

                    if (document.exists()) {
                        binding.status.setText(document.getData().get("status").toString());
                        binding.userName.setText(document.getData().get("name").toString());
                        binding.career.setText(document.getData().get("userhistory").toString());
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
//        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
//                if (e != null) {
//                    Log.w("MypageDetailActivitiy", "Listen failed.", e);
//                    return;
//                }
//
//                if (snapshot != null && snapshot.exists()) {
//                    Log.d("MypageDetailActivitiy", "Current data: " + snapshot.getData());
//                    binding.status.setText(snapshot.getData().get("status").toString());
//                    binding.userName.setText(snapshot.getData().get("name").toString());
//                    binding.career.setText(snapshot.getData().get("userhistory").toString());
////                    if (snapshot.getData().get("profilepic") !=null)
////                        Glide.with(getApplicationContext()).
////                                load(snapshot.getData().get("profilepic")).centerCrop().override(500).placeholder(R.drawable.ic_group80).into(binding.mypageDetailProfileImage);
////                    else if(snapshot.getData().get("profilepic") ==null){
////                        Glide.with(getApplicationContext()).
////                                load(R.drawable.ic_group80).centerCrop().override(500).into(binding.mypageDetailProfileImage);
////                    }
//
//                } else {
//                    Log.d("MypageDetailActivitiy", "Current data: null");
//                }
//            }
//        });
    }
    public void pluschip(String x , int y ){
        Map<String, Object> data = new HashMap<>();
        data.put("subject", x);
        data.put("value",y);
        db.collection("users").document(FirebaseAuth.getInstance().getUid()).collection("category").document(x)
                .set(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startToast(x+" 값이 저장되었습니다.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast(x+" 값이 저장안되었습니다.");
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }
    public void chipsave()
    {
        // db 안에 document 삭제 (모든데이터 날아감)

        for(int i=0;i<14;i++){
            pluschip( chip[i],0  );

            if(ChipArray[i]==1){
                pluschip( chip[i],15 );
          }
     }
    }
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
            sFile = data.getData();
            binding.mypageDetailProfileImage.setImageURI(sFile);

//            storage = FirebaseStorage.getInstance();
//            final StorageReference reference = storage.getReference().child("profile pictures")
//                    .child(FirebaseAuth.getInstance().getUid());

        } else{
           startToast("이미지를 가져오기를 실패 했습니다.");
        }
    }
    private void userinfo(){
        String username = binding.userName.getText().toString();
        String status = binding.status.getText().toString();
        String userhistory = binding.career.getText().toString();

        storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage.getReference().child("profile pictures").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        storageRef.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                if(sFile!=null){
                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            if(username.length() >0 && status.length() >0 && userhistory.length() >0){
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                Map<String, Object> obj = new HashMap<>();
                                obj.put("profilepic", uri.toString());
                                obj.put("name",username);
                                obj.put("status",status);
                                obj.put("userhistory",userhistory);

                                DocumentReference doCRF= db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                        doCRF.update(obj)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                startToast("프로필 저장을 성공하였습니다.");

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                startToast("프로필 저장을 실패 하였습니다.");

                                            }
                                        });



                            }
                        }
                    });
                }
            }
        });
    }
    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
}

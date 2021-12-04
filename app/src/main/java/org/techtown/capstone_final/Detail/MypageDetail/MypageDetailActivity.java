package org.techtown.capstone_final.Detail.MypageDetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
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
    private Uri sFile;

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
        binding.career.setText(testText);
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
    private void getUserInfo(){
        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getUid());

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("MypageDetailActivitiy", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d("MypageDetailActivitiy", "Current data: " + snapshot.getData());
                    binding.status.setText(snapshot.getData().get("status").toString());
                    binding.userName.setText(snapshot.getData().get("name").toString());
                    binding.career.setText(snapshot.getData().get("userhistory").toString());
                    if (snapshot.getData().get("profilepic") !=null)
                        Glide.with(getApplicationContext()).
                                load(snapshot.getData().get("profilepic")).centerCrop().override(500).placeholder(R.drawable.ic_group80).into(binding.mypageDetailProfileImage);
                    else if(snapshot.getData().get("profilepic") ==null){
                        Glide.with(getApplicationContext()).
                                load(R.drawable.ic_group80).centerCrop().override(500).into(binding.mypageDetailProfileImage);
                    }

                } else {
                    Log.d("MypageDetailActivitiy", "Current data: null");
                }
            }
        });
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
            Uri sFile = data.getData();
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
        final StorageReference storageRef = storage.getReference().child("profile pictures").child(FirebaseAuth.getInstance().getUid());

        storageRef.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                if(sFile!=null){
                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                            //실시간 데이터 가져오기 user DB에서
                            db.collection("users").document(FirebaseAuth.getInstance().getUid())
                                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                            if( error != null){
                                                Log.w("MypageDetailActivity", "Listen failed.", error);
                                                return;
                                            }
                                            String source = value != null && value.getMetadata().hasPendingWrites()
                                                    ? "Local" : "Server";
                                            if (value != null && value.exists()) {
                                                Log.d("MypageDetailActivity", source + " data: " + value.getData());
                                            } else {
                                                Log.d("MypageDetailActivity", source + " data: null");
                                            }
                                        }
                                    });
                                Map<String, Object> obj = new HashMap<>();

                                obj.put("name",username);
                                obj.put("status",status);
                                obj.put("userhistory",userhistory);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
            }
        });
    }
    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
}

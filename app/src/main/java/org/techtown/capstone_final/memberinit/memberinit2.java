package org.techtown.capstone_final.memberinit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import org.techtown.capstone_final.Model.Users;
import org.techtown.capstone_final.R;
import org.techtown.capstone_final.databinding.ActivityMemberinit2Binding;

public class memberinit2 extends AppCompatActivity {

    ActivityMemberinit2Binding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    private final String TAG = "memberinit2";
    private final int GALLERY_CODE = 10;
    private FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemberinit2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage =  FirebaseStorage.getInstance();
        findViewById(R.id.what_your_name_save_button).setOnClickListener(onClickListener);
        findViewById(R.id.what_nanme_back).setOnClickListener(onClickListener);
        findViewById(R.id.userprofile).setOnClickListener(onClickListener);


    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        moveTaskToBack(true);
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(1);
//    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {


                case R.id.what_your_name_save_button:
                    profileUpdate();
                    Intent intent = new Intent(memberinit2.this, memberinit3.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.what_nanme_back:
                    Intent intent2 = new Intent(memberinit2.this, memberinit1.class);
                    startActivity(intent2);
                    break;
                case R.id.userprofile:
                    loadAlBum();
                    break;

            }
        }
    };
    private void  loadAlBum(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, GALLERY_CODE);

    }
    private void profileUpdate() {
        String userName = ((EditText) findViewById(R.id.userNickName)).getText().toString();


        if (userName.length() > 0) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Users users = new Users(userName);
            if (user !=null){
                db.collection("users").document(user.getUid()).set(users)
                       .addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void avoid) {
                               startToast("회원정보 등록에 성공하였습니다.");
                           }
                       })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("회원정보 등록에 실패하였습니다.");
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }



        } else {
            startToast("회원정보를 입력해주세요.");
        }
    }

    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
}
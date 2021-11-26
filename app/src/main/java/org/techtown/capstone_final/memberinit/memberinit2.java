package org.techtown.capstone_final.memberinit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.techtown.capstone_final.R;
import org.techtown.capstone_final.databinding.ActivityMemberinit2Binding;

import java.util.HashMap;
import java.util.Map;

public class memberinit2 extends AppCompatActivity {

    ActivityMemberinit2Binding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseFirestore db;
    private final String TAG = "memberinit2";
    private final int GALLERY_CODE = 10;

    private FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemberinit2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        findViewById(R.id.what_your_name_save_button).setOnClickListener(onClickListener);
        findViewById(R.id.userprofile).setOnClickListener(onClickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() !=null){

            Uri sFile = data.getData();
            binding.userprofile.setImageURI(sFile);

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
                                            startToast("성공적으로 이미지 업로드 되었습니다");
                                        }
                                    }).
                                    addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            startToast("이미지가 업로드 되지 않았습니다.");
                                        }
                                    });
                        }
                    });
                }
            });

        }else{
            startToast("이미지를 넣어주세요");
        }
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

                case R.id.userprofile:
                    loadAlBum();
                    break;

            }
        }
    };

    private void loadAlBum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_CODE);

    }


    private void profileUpdate() {
        String name = ((EditText) findViewById(R.id.userNickName)).getText().toString();

        if (name.length() >0){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            Map<String, Object> obj = new HashMap<>();
            obj.put("name",name);

            db.collection("users").document(user.getUid()).set(obj)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            startToast("저장이 완료 되었습니다.");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    startToast("저장을 실패 하였습니다");
                }
            });
        }else{
            startToast("이름을 입력해주세요");
        }


//        if (name.length() > 0) {
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            FirebaseFirestore db = FirebaseFirestore.getInstance();
//            Log.w(TAG, "userName.length()>0 함수 실행 환료  " );
//            Users users = new Users(name);
//            if (user != null){
//                Log.w(TAG, "user null 값인지 확인중입니다 " );
//                db.collection("users").document(user.getUid()).set(users)
//                       .addOnSuccessListener(new OnSuccessListener<Void>() {
//                           @Override
//                           public void onSuccess(Void avoid) {
//                               startToast("회원정보 등록에 성공하였습니다.");
//
//                           }
//                       })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                startToast("회원정보 등록에 실패하였습니다.");
//                                Log.w(TAG, "Error adding document", e);
//                            }
//                        });
//            }
//
//
//
//        } else {
//            startToast("회원정보를 입력해주세요.");
//        }
    }


    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
}
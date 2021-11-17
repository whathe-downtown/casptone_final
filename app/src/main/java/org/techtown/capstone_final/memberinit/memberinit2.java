package org.techtown.capstone_final.memberinit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

import java.io.InputStream;

public class memberinit2 extends AppCompatActivity {

    ActivityMemberinit2Binding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;

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
        storage = FirebaseStorage.getInstance();
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

    private void loadAlBum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            //request code를 받으면 이미지를 올리는로직
            if (requestCode == GALLERY_CODE) {
                Uri file = data.getData();
                binding.userprofile.setImageURI(file);
                // storage 파일을 참조해서 거기에 데이터를 삽입함
                final StorageReference reference = storage.getReference().child("profile pictures")
                        .child(FirebaseAuth.getInstance().getUid());
                // 받은 데이터 값을 참조해서 putfile(file)넣는다.
                reference.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            // 이미지 파
                            public void onSuccess(Uri uri) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("users").document(user.getUid()).set(uri.toString());
                                startToast("이미지가 업로드 되었습니다.");

                            }
                        });
                    }
                });
                UploadTask uploadTask = reference.putFile(file);
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        startToast("사진이 정상적으로 업로드 되지 않았습니다.");
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                        startToast("사진이 정상적으로 업로드 되었습니다.");
                    }
                });


            }


        }else{
            startToast("회원 정보를 입력 or 삽입 해주세요.");
        }
    }




    private void profileUpdate() {
        String name = ((EditText) findViewById(R.id.userNickName)).getText().toString();


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
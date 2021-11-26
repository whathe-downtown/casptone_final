package org.techtown.capstone_final.memberinit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import org.techtown.capstone_final.R;
import org.techtown.capstone_final.databinding.ActivityMemberinit3Binding;
import org.techtown.capstone_final.fragment.Home.HomeActivity;

import java.util.HashMap;
import java.util.Map;

public class memberinit3 extends AppCompatActivity {

    ActivityMemberinit3Binding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    private final String TAG = "SignUpActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemberinit3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        findViewById(R.id.self_introduce_button).setOnClickListener(onClickListener);



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


                case R.id.self_introduce_button:
                    Intent intent = new Intent(memberinit3.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    profileUpdate();
                    break;

            }
        }
    };

    private void profileUpdate() {

        String oneline = ((EditText) findViewById(R.id.status)).getText().toString();
        String manyline = ((EditText) findViewById(R.id.userintrouduce)).getText().toString();

        if (oneline.length() > 0 && manyline.length() > 0) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            Map<String, Object> obj = new HashMap<>();
            obj.put("useroneinfo",oneline);
            obj.put("userinfo",manyline);

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
        } else {
            startToast("회원정보를 입력해주세요.");
        }
    }

    private  void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


//    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//            .setDisplayName("Jane Q. User")
//            .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
//            .build();
//
//            user.updateProfile(profileUpdates)
//            .addOnCompleteListener(new OnCompleteListener<Void>() {
//        @Override
//        public void onComplete(@NonNull Task<Void> task) {
//            if (task.isSuccessful()) {
//                startToast("회원정보를 등록에 성공 하셨습니다.");
//            }
//        }
//    });

//    private void myStartActivity(Class c) {
//        Intent intent = new Intent(this, c);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//    }
}
//        progressDialog = new ProgressDialog(SignUpActivity.this);
//        progressDialog.setTitle("계정 만드는중입니다");
//        progressDialog.setMessage("당신의 계정이 만들어졌습니다.");
//
//        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressDialog.show();
//                auth.createUserWithEmailAndPassword(binding.signUpInputEmail.getText().toString(),binding.signUpInputPwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull  Task<AuthResult> task) {
//                        progressDialog.dismiss();
//
//                        if(task.isSuccessful()){
//                            Users user = new Users(binding.signUpInputUserName.getText().toString(), binding.signUpInputEmail.getText().toString(),
//                                    binding.signUpInputPwd.getText().toString());
//
//                            String id = task.getResult().getUser().getUid();
//                            database.getReference().child("Users").child(id).setValue(user);
//
//                            Toast.makeText(SignUpActivity.this,"회원 가입 생성이 완료되었습니다.",Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        else {
//                            if (task.getException() ! =null){
//
//                            }
//
//                            Toast.makeText(SignUpActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
//                binding.AlreadyAccount.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//            }
//        });


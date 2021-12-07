package org.techtown.capstone_final;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.techtown.capstone_final.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    private final String TAG = "SignUpActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        findViewById(R.id.signUpButton).setOnClickListener(onClickListener);
        findViewById(R.id.sign_up_go_back).setOnClickListener(onClickListener);
        findViewById(R.id.sign_up_have_account).setOnClickListener(onClickListener);
        findViewById(R.id.email_vertification_buttion).setOnClickListener(onClickListener);





    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.signUpButton:
                    signUp();
                    break;
                case R.id.email_vertification_buttion:
                    emailcheck();
                    break;
                case R.id.sign_up_have_account:
                    Intent intent = new Intent(SignUpActivity.this, Password_Reset.class);
                    startActivity(intent);
                    break;
                case R.id.sign_up_go_back:
                    Intent intent2 = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    };
    private void signUp() {
        String email = ((EditText) findViewById(R.id.sign_up_input_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.sign_up_input_pwd)).getText().toString();
        String passwordCheck = ((EditText) findViewById(R.id.sign_up_input_pwd2)).getText().toString();

        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0 ){
            if(password.equals(passwordCheck)){
//                final RelativeLayout loaderLayout = findViewById(R.id.loaderLyaout);
//                loaderLayout.setVisibility(View.VISIBLE);
               auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                loaderLayout.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    startToast("회원가입에 성공하였습니다");
                                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                    startActivity(intent);
                                }else{
                                    if(task.getException() != null){
                                        startToast( task.getException().toString());
                                    }
                                }
//                                    auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()){
//                                                if(auth.getCurrentUser().isEmailVerified()){
//                                                    startToast("사용자 등록을 위해 보낸 이메일을 확인하세요");
//                                                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
//                                                    startActivity(intent);
//                                                }else{
//                                                    startToast("이메일 인증 확인을 확인하세요");
//                                                }
//
//                                            }else {
//                                                startToast(task.getException().getMessage());
//
//                                            }
//                                        }
//                                    });
//                                   startToast( "회원가입에 성공하였습니다.");
//                                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
//                                    startActivity(intent);
//                                } else {
//                                    if(task.getException() != null){
//                                        startToast( task.getException().toString());
//                                    }
//                                }
                            }
                        });
            }else{
               startToast( "비밀번호가 일치하지 않습니다.");
            }
        }else {
           startToast( "이메일 또는 비밀번호를 입력해 주세요.");
        }
    }

    private void emailcheck(){
        binding.signUpButton.setEnabled(true);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(intent);

    }
    private  void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
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


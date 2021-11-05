package org.techtown.capstone_final;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.techtown.capstone_final.databinding.ActivitySignInBinding;

public class MemberInitActivtiy extends AppCompatActivity {

    ActivitySignInBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(MemberInitActivtiy.this);
        progressDialog.setTitle("로그인");
        progressDialog.setMessage("로그인 하는중 입니다.");

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                //로그인 요청 trim 은 왜붙은지모르겠음
                String stremail = binding.signInInputEmail.getText().toString().trim();
                String strpwd = binding.signInInputPwd.getText().toString().trim();
                if(stremail.isEmpty()){
                    Toast.makeText(MemberInitActivtiy.this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strpwd.isEmpty()){
                    Toast.makeText(MemberInitActivtiy.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(stremail,strpwd).addOnCompleteListener(MemberInitActivtiy.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //로그인 성공
                            Toast.makeText(MemberInitActivtiy.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(MemberInitActivtiy.this,MainActivity.class);
                            startActivity(intent);//현재 엑티비티 파괴
                            finish();
                        }else{
                            Toast.makeText(MemberInitActivtiy.this, "로그인 실패", Toast.LENGTH_SHORT).show();

                            //alert_message = (TextView) findViewById(R.id.alert_message);
                            //alert_message.startAnimation(alertMessageAnim);

                        }

                    }
                });

            }
        });
        binding.goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberInitActivtiy.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }

        });
        binding.findAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberInitActivtiy.this, Password_Reset.class);
                startActivity(intent);
                finish();
            }
        });
        if (auth.getCurrentUser()!= null)
        {
            Intent intent = new Intent(MemberInitActivtiy.this,MainActivity.class);
            startActivity(intent);
        }

    }
}
package org.techtown.capstone_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.techtown.capstone_final.databinding.ActivityPasswordResetBinding;

public class Password_Reset extends AppCompatActivity {
    private static final String TAG ="PasswordRessetActivity";
    ActivityPasswordResetBinding binding;
    private  FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasswordResetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        findViewById(R.id.sendButton).setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sendButton:
                        send();
                    break;

            }

        }

    };
    private void send() {
        String email = ((EditText) findViewById(R.id.sendemail)).getText().toString();


        if(email.length() > 0 ){
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startToast("이메일을 보냈습니다.");
                                Intent intent =new Intent(Password_Reset.this,SignInActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        }
                    });



        }else {
            startToast( "이메일  입력해 주세요.");
        }
    }

    private  void startToast(String msg){ Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }


    }
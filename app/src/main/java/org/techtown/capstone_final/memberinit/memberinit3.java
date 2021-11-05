package org.techtown.capstone_final.memberinit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

import org.techtown.capstone_final.MainActivity;
import org.techtown.capstone_final.databinding.ActivityMemberinit3Binding;

public class memberinit3 extends AppCompatActivity {

    ActivityMemberinit3Binding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemberinit3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (auth.getCurrentUser()!= null)
        {
            Intent intent = new Intent(memberinit3.this, MainActivity.class);
            startActivity(intent);
        }

//        progressDialog = new ProgressDialog(SignInActivity.this);
//        progressDialog.setTitle("로그인");
//        progressDialog.setMessage("로그인 하는중 입니다.");

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.selfIntroduceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName("Jane Q. User")
                        .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                   startToast("회원정보 등록에 성공하였습니다");
                                }
                            }
                        });
            }else{
               startToast("회원정보를 입력해주세요");
            }
        });

    }
    private  void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
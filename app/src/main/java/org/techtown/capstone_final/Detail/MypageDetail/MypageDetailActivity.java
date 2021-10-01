 package org.techtown.capstone_final.Detail.MypageDetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import org.techtown.capstone_final.Model.Users;
import org.techtown.capstone_final.R;
import org.techtown.capstone_final.databinding.ActivityMypageDetailBinding;
import org.techtown.capstone_final.fragment.mypage.MypageActivity;

import java.util.HashMap;

public class MypageDetailActivity extends AppCompatActivity {

    ActivityMypageDetailBinding binding;
    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMypageDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        //이미지상세피이지 뒤로가기 버튼 로직
        binding.mypageDetailBackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypageDetailActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });
        //이미지 세이브버튼 로직
        binding.mypageDetailSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.userName.getText().toString();
                String status = binding.status.getText().toString();

                //파이어베이스 DB 안에 userName -> HashMap으로 변경하는거
                HashMap<String, Object> obj = new HashMap<>();
                obj.put("userName", username);
                obj.put("status", status);

                //obj에 만든것들을 update 해주는 작업업
               database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .updateChildren(obj);
                Toast.makeText(MypageDetailActivity.this, "프로필 업데이트 완료", Toast.LENGTH_SHORT).show();
            }
        });

        //자기소개서 내용 올리기
        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        Users users = snapshot.getValue(Users.class);
                        Picasso.get()
                                .load(users.getProfilepic())
                                .placeholder(R.drawable.ic_user)
                                .into(binding.profileImage);
                        binding.status.setText(users.getStatus());
                        binding.userName.setText(users.getUserName());


                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });
        //프로파일 이미지클릭시 자신의 이미지 파일에서 사진 가져오기
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 33);
            }
        });
    }

    //데이터 값을 받아 오기위한 로직
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!=null){

            // sFile은  URL에 사진(데이터)를 넣어서 http 형식으로 storge 저장 하게 만들기
            Uri sFile =data.getData();
            binding.profileImage.setImageURI(sFile);

            final StorageReference reference = storage.getReference().child("profile pictures")
                    .child(FirebaseAuth.getInstance().getUid());

            //이미지 storage URl을 가져와서 유저리스트의 이미지를 다른곳 뿌려주는거
            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        // 이미지 파
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                    .child("profilepic").setValue(uri.toString());
                            Toast.makeText(MypageDetailActivity.this, "이미지가 업로드 되었습니다.", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });

        }
    }
}
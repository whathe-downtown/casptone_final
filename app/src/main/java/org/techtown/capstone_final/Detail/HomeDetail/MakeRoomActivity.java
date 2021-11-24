package org.techtown.capstone_final.Detail.HomeDetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import org.techtown.capstone_final.R;
import org.techtown.capstone_final.databinding.ActivityMakeRoomBinding;
import org.techtown.capstone_final.fragment.Home.HomeActivity;

public class MakeRoomActivity extends AppCompatActivity {

    ActivityMakeRoomBinding binding;
    FirebaseFirestore db;
    FirebaseStorage storage;
    FirebaseAuth auth;
    Spinner spinner;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMakeRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        findViewById(R.id.Roomprofilepic).setOnClickListener(onClickListener);
        findViewById(R.id.makeroom_next).setOnClickListener(onClickListener);
        findViewById(R.id.back_imageview).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Roomprofilepic:
                    pickImage();
                    break;

                case R.id.makeroom_next:
                    Save();
                    break;

                case R.id.back_imageview:
                    backButton();
                    break;

            }
        }
    };


    private void pickImage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 34);
    }

    private void Save() {
        Intent intent = new Intent(MakeRoomActivity.this, MakeRoomDetailActivity.class);
        startActivity(intent);
    }

    private void backButton() {
        Intent intent = new Intent(MakeRoomActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null) {

            // sFile은  URL에 사진(데이터)를 넣어서 http 형식으로 storge 저장 하게 만들기
           Uri sFile = data.getData();
//           binding.Roomprofilepic.setImageURI(sFile);


        }

    }
}
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null) {

            // sFile은  URL에 사진(데이터)를 넣어서 http 형식으로 storge 저장 하게 만들기
            Uri sFile = data.getData();
            binding.Roomprofilepic.setImageURI(sFile);

            final StorageReference reference = storage.getReference().child("Roomprofile pictures")
                    .child(FirebaseAuth.getInstance().getUid());


            //이미지 storage URl을 가져와서 유저리스트의 이미지를 다른곳 뿌려주는거
            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                        @Override
                        // 이미지 파
                        public void onSuccess(Uri uri) {
                            database.getReference("Room").child(FirebaseAuth.getInstance().getUid()).push()
                                    .child("Roomprofilepic").setValue(uri.toString());
                            Toast.makeText(MakeRoomActivity.this, "이미지가 업로드 되었습니다.", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });

        }


        binding.spinnerItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.makeroomNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Roomtitle = binding.Roomname.getText().toString();
                String Roomstatus = binding.RoomStatus.getText().toString();
                String RoomDate = binding.RoomDate.getText().toString();
                String Roomlocation = binding.RoomLocation.getText().toString();
                String Roomcategory = binding.spinnerItem.getSelectedItem().toString();


                HashMap<String, Object> obj = new HashMap<>();
                obj.put("Roomtitle", Roomtitle);
                obj.put("Roomstatus", Roomstatus);
                obj.put("RoomDate", RoomDate);
                obj.put("Roomlocation", Roomlocation);
                obj.put("Roomcategory", Roomcategory);


                database.getReference().child("Room").child(FirebaseAuth.getInstance().getUid()).push()
                        .setValue(obj);


                Intent intent = new Intent(MakeRoomActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(MakeRoomActivity.this, "방 만들기 완료", Toast.LENGTH_SHORT).show();
            }
        });

        binding.mypageDetailBackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ient intent = new Intent(MakeRoomActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();nt
            }
        });*/


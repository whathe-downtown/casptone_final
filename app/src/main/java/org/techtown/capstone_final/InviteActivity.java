package org.techtown.capstone_final;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.FirebaseFirestore;

import org.techtown.capstone_final.Adapters.UsersAdapter;
import org.techtown.capstone_final.Model.Users;
import org.techtown.capstone_final.databinding.ActivityInviteBinding;

import java.util.ArrayList;

public class InviteActivity extends AppCompatActivity {

    ActivityInviteBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInviteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();

        UsersAdapter adapter = new UsersAdapter(list, getApplicationContext());
        binding.inviteRecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.inviteRecyclerview.setLayoutManager(layoutManager);

//        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()){
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Users users= document.toObject(Users.class);
//                        users.getName();
//                        list.add(users);
//
//                    }
//                }else{
//                    Log.d("InviteActvitiy", "Current data: null");
//
//
//                }adapter.notifyDataSetChanged();
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
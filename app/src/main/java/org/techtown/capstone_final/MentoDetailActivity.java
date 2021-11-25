package org.techtown.capstone_final;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import org.techtown.capstone_final.Model.Users;
import org.techtown.capstone_final.databinding.SampleShowRoomBinding;

import java.util.ArrayList;

public class MentoDetailActivity extends AppCompatActivity {

    public MentoDetailActivity(){

    }
    private static final String TAG = "MentoDetailActivity";
    SampleShowRoomBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SampleShowRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        HorizonAdapter adapter = new HorizonAdapter(list, getApplicationContext());
//        binding.roomProifleRecyclerview.setAdapter(adapter);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        binding.roomProifleRecyclerview.setLayoutManager(layoutManager);

//        DocumentReference dbcRef = db.collection("user").document((FirebaseAuth.getInstance().getCurrentUser().getUid()));
//        dbcRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                list.clear();
//                if (error != null) {
//                    Log.w(TAG, "Listen failed.", error);
//                    return;
//                }
//
//                if (value != null && value.exists()) {
//                    Log.d(TAG, "Current data: " + value.getData());
//                    Users users = value.toObject(Users.class);
//                    users.getName();
//                    list.add(users);
//
//
//                } else {
//                    Log.d(TAG, "Current data: null");
//                }adapter.notifyDataSetChanged();
//            }
//        });

    }
}

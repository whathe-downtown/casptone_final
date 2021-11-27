package org.techtown.capstone_final.fragment.Home.viewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.techtown.capstone_final.Adapters.RoomsAdapter;
import org.techtown.capstone_final.Model.Room;
import org.techtown.capstone_final.databinding.FragmentMentorBinding;

import java.util.ArrayList;


public class MentorFragment extends Fragment {
    FirebaseFirestore db;


    public MentorFragment() {
        // Required empty public constructor
    }
    FragmentMentorBinding binding;
    FirebaseUser user;
    FirebaseDatabase database;
    private static final String TAG = "MentorFragemnt";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        database = FirebaseDatabase.getInstance();
        binding = FragmentMentorBinding.inflate(inflater, container, false);
        db = FirebaseFirestore.getInstance();
        ArrayList<Room> list = new ArrayList<>();
        RoomsAdapter adapter =  new RoomsAdapter(list, getContext());

        binding.MentorrecyclerView.setHasFixedSize(true);
        binding.MentorrecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.MentorrecyclerView.setLayoutManager(layoutManager);

        CollectionReference coRef = db.collection("1:1");
        coRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Room room = document.toObject(Room.class);
                        room.getRoomTitle();
                        list.add(room);

                    }
                }else{
                    Log.d(TAG, "Current data: null");


                }adapter.notifyDataSetChanged();
            }
        });
//        DocumentReference docRef = db.collection("1:1").document(FirebaseAuth.getInstance().getUid());
//        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
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
//                    Room room = value.toObject(Room.class);
//                    room.getRoomTitle();
//                    list.add(room);
//
//
//                } else {
//                    Log.d(TAG, "Current data: null");
//                }adapter.notifyDataSetChanged();
//            }
//        });







//        addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                list.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Room room = dataSnapshot.getValue(Room.class);
//                    room.getRoomTitle();
//                    room.getRoomprofilepic();
//                    room.getRoomDate();
//                    room.getRoomPlace();
//                    room.setRoomId(dataSnapshot.getKey()); // set RoomId가 setkey받아서 메인값으로 넘겨줌
//                    list.add(room);
//                }
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });

        return binding.getRoot();
    }
}
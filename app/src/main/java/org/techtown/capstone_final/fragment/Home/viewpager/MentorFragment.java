package org.techtown.capstone_final.fragment.Home.viewpager;

import android.os.Bundle;
import android.os.Handler;
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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.techtown.capstone_final.Adapters.RoomsAdapter;
import org.techtown.capstone_final.Model.Room;
import org.techtown.capstone_final.R;
import org.techtown.capstone_final.databinding.FragmentMentorBinding;

import java.util.ArrayList;


public class MentorFragment extends Fragment {
    FirebaseFirestore db;
    private String x;
    private ArrayList<Room> list;
    private RoomsAdapter adapter;
    Handler mHandler;
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
        View v= inflater.inflate(R.layout.fragment_home,container, false);
        list = new ArrayList<>();
        adapter = new RoomsAdapter(list, getContext());

        binding.MentorrecyclerView.setHasFixedSize(true);
        binding.MentorrecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.MentorrecyclerView.setLayoutManager(layoutManager);
//        v.findViewById(R.id.cchip0).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip1).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip2).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip3).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip4).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip5).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip6).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip7).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip8).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip9).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip10).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip11).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip12).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip13).setOnClickListener(onClickListener);
//        v.findViewById(R.id.cchip14).setOnClickListener(onClickListener);


//                CollectionReference coRef = db.collection("1:1");
//                Query query = coRef.orderBy(x);
//                Log.d("DiaryActivity","TextChanged :: $it");
//
//                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                                Room room = document.toObject(Room.class);
//                                room.getRoomTitle();
//                                list.add(room);
//                            }
//                            Log.d(TAG, x+ "x값을 잘가져왔습니다");
//
//                        }else{
//                            Log.d(TAG, "Current data: null");
//                        }adapter.notifyDataSetChanged();
//                    }
//                });
//            }else{
        CollectionReference coRef = db.collection("1:1");
        coRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Room room = document.toObject(Room.class);
                        room.getRoomTitle();
                        list.add(room);

                    }
                } else {
                    Log.d(TAG, "Current data: null");


                }
                adapter.notifyDataSetChanged();
            }
        });

        //TODO 어떤 버튼을 눌렀을때 이 x 가져온다


//        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getUid());
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

    private void chipFiliter(String x) {
        CollectionReference coRef = db.collection("1:1");
        Query query = coRef.orderBy(x);
        Log.d("DiaryActivity", "TextChanged :: $it");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Room room = document.toObject(Room.class);
                        room.getRoomTitle();
                        list.add(room);
                    }
                    Log.d(TAG, x + "x값을 잘가져왔습니다");

                } else {
                    Log.d(TAG, "Current data: null");
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void chipDefault() {
        CollectionReference coRef = db.collection("1:1");

        coRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Room room = document.toObject(Room.class);
                        room.getRoomTitle();
                        list.add(room);
                    }
                    Log.d(TAG, x + "x값을 잘가져왔습니다");

                } else {
                    Log.d(TAG, "Current data: null");
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.cchip0:
//                    chipDefault();
//                    break;
//                case R.id.cchip1:
//                    chipFiliter("인문");
//                    break;
//                case R.id.cchip2:
//                    chipFiliter("미술");
//                    break;
//                case R.id.cchip3:
//                    chipFiliter("법과");
//                    break;
//                case R.id.cchip4:
//                    chipFiliter("경영");
//                    break;
//                case R.id.cchip5:
//                    chipFiliter("음악");
//                    break;
//                case R.id.cchip6:
//                    chipFiliter("공과");
//                    break;
//                case R.id.cchip7:
//                    chipFiliter("정보");
//                    break;
//                case R.id.cchip8:
//                    chipFiliter("농과");
//                    break;
//                case R.id.cchip9:
//                    chipFiliter("체육");
//                    break;
//                case R.id.cchip10:
//                    chipFiliter("수산");
//                    break;
//                case R.id.cchip11:
//                    chipFiliter("예술");
//                    break;
//                case R.id.cchip12:
//                    chipFiliter("사회과학");
//                    break;
//                case R.id.cchip13:
//                    chipFiliter("자연과학");
//                    break;
//                case R.id.cchip14:
//                    chipFiliter("생활과학");
//                    break;
//
//}}};

}
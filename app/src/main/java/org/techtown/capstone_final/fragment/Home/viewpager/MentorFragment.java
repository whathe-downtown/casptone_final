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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.techtown.capstone_final.Adapters.RoomsAdapter;
import org.techtown.capstone_final.Model.Room;
import org.techtown.capstone_final.Model.UsersCategory;
import org.techtown.capstone_final.databinding.FragmentMentorBinding;

import java.util.ArrayList;


public class MentorFragment extends Fragment {
    FirebaseFirestore db;

    private ArrayList<Room> list;
    private RoomsAdapter adapter;
    private String arr;
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

        list = new ArrayList<>();
        adapter = new RoomsAdapter(list, getContext());

        binding.MentorrecyclerView.setHasFixedSize(true);
        binding.MentorrecyclerView.setAdapter(adapter);
//        Button button1 = (Button) getView().findViewById(R.id.cchip0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.MentorrecyclerView.setLayoutManager(layoutManager);
        chipDefault();
        binding.cchip0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipDefault();
            }
        });
        binding.cchip15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipALl();
            }
        });
        binding.cchip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("인문");
            }
        });
        binding.cchip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("미술");
            }
        });
        binding.cchip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("법과");
            }
        });
        binding.cchip4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("경영");
            }
        });
        binding.cchip5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("음악");
            }
        });
        binding.cchip6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("공과");
            }
        });
        binding.cchip7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("정보");
            }
        });
        binding.cchip8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("농과");
            }
        });
        binding.cchip9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("체육");
            }
        });
        binding.cchip10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("수산");
            }
        });
        binding.cchip11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("예술");
            }
        });
        binding.cchip12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("사회과학");
            }
        });
        binding.cchip13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("자연과학");
            }
        });
        binding.cchip14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                chipFiliter("생활과학");
            }
        });








// <!--------------------------------------------------------------------------------------------------------------------------->




        //TODO 어떤 버튼을 눌렀을때 이 x 가져온다



        return binding.getRoot();
    }
    private void chipALl(){
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
    }

    private void chipFiliter(String x) {
        CollectionReference coRef = db.collection("1:1");
        Query query = coRef.whereEqualTo("roomcategory", x);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
    }
    private void chipDefault() {
        CollectionReference docategoryRef = db.collection("users").document(FirebaseAuth.getInstance().getUid()).collection("category");

        Query query1 = docategoryRef.orderBy("value", Query.Direction.DESCENDING).limit(3);
        query1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() { //최대값 3개가져옴

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Handler mHandler = new Handler(); mHandler.postDelayed(new Runnable() { public void run() {}
                }, 2000); // 0.5초후
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        UsersCategory category = document.toObject(UsersCategory.class);
                        arr = category.getSubject();

                        CollectionReference coRef = db.collection("1:1");
                        Query query = coRef.whereEqualTo("roomcategory",arr);
                        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                    }
                    Log.d(TAG, "Category 값 가져오기 성공 ");

                }else{
                    Log.d(TAG, "Current data: null");
                }adapter.notifyDataSetChanged();
            }
        });
    }

            }

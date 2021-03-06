package org.techtown.capstone_final.fragment.Home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import org.techtown.capstone_final.Adapters.RoomsAdapter;
import org.techtown.capstone_final.MakeRoomContianerActivity;
import org.techtown.capstone_final.Model.Room;
import org.techtown.capstone_final.databinding.FragmentHomeBinding;
import org.techtown.capstone_final.fragment.Home.Adapters.FragementAdapter;

import java.util.ArrayList;

public class HomeActivity extends Fragment {
    private Context context;
    FragmentHomeBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseDatabase database;
    RecyclerView recyclerView;
    FirebaseFirestore db;
    private static final String TAG = "HomeFragemnt";
    private ArrayList<Room> list;
    private RoomsAdapter adapter;
    private String arr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

//        binding.getRoot().findViewById(R.id.cchip0).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip1).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip2).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip3).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip4).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip5).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip6).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip7).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip8).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip9).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip10).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip11).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip12).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip13).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip14).setOnClickListener(onClickListener);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.viewPager.setAdapter(new FragementAdapter(getChildFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewPager);


    }

    @Nullable
    @Override // oncreatview MainActivty => activy_main_xml ???????????? ??????????????? ?????????????????????. ???????????? ????????????
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        context = container.getContext();
//        chipDefault();
////        RoomsAdapter adapter = new RoomsAdapter(list,getContext());
//        binding.getRoot().findViewById(R.id.cchip0).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip1).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip2).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip3).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip4).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip5).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip6).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip7).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip8).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip9).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip10).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip11).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip12).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip13).setOnClickListener(onClickListener);
//        binding.getRoot().findViewById(R.id.cchip14).setOnClickListener(onClickListener);


        binding.basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MakeRoomContianerActivity.class);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }

//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.cchip0:
//                    Toast.makeText(context, "bascic got 1", Toast.LENGTH_SHORT).show();
//                    chipDefault();
//
//                    break;
//                case R.id.cchip1:
//                    Toast.makeText(context, "bascic got 2", Toast.LENGTH_SHORT).show();
//                    chipFiliter("??????");
//                    break;
//                case R.id.cchip2:
//                    Toast.makeText(context, "bascic got 3", Toast.LENGTH_SHORT).show();
//                    chipFiliter("??????");
//                    break;
//                case R.id.cchip3:
//                    chipFiliter("??????");
//                    break;
//                case R.id.cchip4:
//                    chipFiliter("??????");
//                    break;
//                case R.id.cchip5:
//                    chipFiliter("??????");
//                    break;
//                case R.id.cchip6:
//                    chipFiliter("??????");
//                    break;
//                case R.id.cchip7:
//                    chipFiliter("??????");
//                    break;
//                case R.id.cchip8:
//                    chipFiliter("??????");
//                    break;
//                case R.id.cchip9:
//                    chipFiliter("??????");
//                    break;
//                case R.id.cchip10:
//                    chipFiliter("??????");
//                    break;
//                case R.id.cchip11:
//                    chipFiliter("??????");
//                    break;
//                case R.id.cchip12:
//                    chipFiliter("????????????");
//                    break;
//                case R.id.cchip13:
//                    chipFiliter("????????????");
//                    break;
//                case R.id.cchip14:
//                    chipFiliter("????????????");
//                    break;
//
//            }
//        }
//    };
//}
//    private void chipFiliter(String x){
//        CollectionReference coRef = db.collection("1:1");
//        Query query = coRef.whereEqualTo("roomcategory",x);
//        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Room room = document.toObject(Room.class);
//                        room.getRoomTitle();
//                        list.add(room);
//
//                    }
//                } else {
//                    Log.d(TAG, "Current data: null");
//
//
//                }
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }
//    private void chipDefault() {
//        CollectionReference docategoryRef = db.collection("users").document(FirebaseAuth.getInstance().getUid()).collection("category");
//
//        Query query1 = docategoryRef.orderBy("value", Query.Direction.DESCENDING).limit(3);
//        query1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() { //????????? 3????????????
//
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                Handler mHandler = new Handler(); mHandler.postDelayed(new Runnable() { public void run() {}
//                }, 2000); // 0.5??????
//                if (task.isSuccessful()){
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.d(TAG, document.getId() + " => " + document.getData());
//                        UsersCategory category = document.toObject(UsersCategory.class);
//                        arr = category.getSubject();
//                        chipFiliter(arr);
//
//                    }
//                    Log.d(TAG, "Category ??? ???????????? ?????? ");
//
//                }else{
//                    Log.d(TAG, "Current data: null");
//                }adapter.notifyDataSetChanged();
//            }
//        });
}



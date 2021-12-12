package org.techtown.capstone_final.fragment.Home;

import android.app.ProgressDialog;
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

import org.techtown.capstone_final.MakeRoomContianerActivity;
import org.techtown.capstone_final.Model.Room;
import org.techtown.capstone_final.databinding.FragmentHomeBinding;
import org.techtown.capstone_final.fragment.Home.Adapters.FragementAdapter;

import java.util.ArrayList;

public class HomeActivity extends Fragment {

    FragmentHomeBinding binding;
    ArrayList<Room> list = new ArrayList<>();
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseDatabase database;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
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
//
        }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.viewPager.setAdapter(new FragementAdapter(getChildFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewPager);


    }

    @Nullable
    @Override // oncreatview MainActivty => activy_main_xml 연동하는 느낌이라고 생각하면됩니다. 연결되게 해주는거
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        binding = FragmentHomeBinding.inflate(inflater,container,false);

//        RoomsAdapter adapter = new RoomsAdapter(list,getContext());


        binding.basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MakeRoomContianerActivity.class);
                startActivity(intent);
            }
        });
        return  binding.getRoot();
    }

//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.cchip0:
//
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

package org.techtown.capstone_final.fragment.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import org.techtown.capstone_final.Detail.MypageDetail.MypageDetailActivity;
import org.techtown.capstone_final.SignInActivity;
import org.techtown.capstone_final.databinding.FragmentMypageBinding;

public class MypageActivity extends Fragment {
    private static final String TAG = "MainActivity";
    FragmentMypageBinding binding;
    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;


    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMypageBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();


    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState)
    {


        binding = FragmentMypageBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Mypage_Detail로 넘어가는 프로필 클릭");
                Intent intent = new Intent(getActivity().getApplicationContext(), MypageDetailActivity.class);
                startActivity(intent);

            }
        });

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"clicked the logout Button");
                auth.signOut();
                Intent intent = new Intent(getActivity().getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(getActivity(), "로그아웃이 되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });

        return  view;

    }


    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}

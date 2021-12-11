package org.techtown.capstone_final.fragment.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import org.techtown.capstone_final.Adapters.UserCategoryAdapter;
import org.techtown.capstone_final.Detail.MypageDetail.MypageDetailActivity;
import org.techtown.capstone_final.Model.UsersCategory;
import org.techtown.capstone_final.R;
import org.techtown.capstone_final.SignInActivity;
import org.techtown.capstone_final.databinding.FragmentMypageBinding;
import org.techtown.capstone_final.fragment.mypage.Adapters.ViewpagerAdapter;

import java.util.ArrayList;

public class MypageActivity extends Fragment {
    private static final String TAG = "MypageActivity";
    FragmentMypageBinding binding;
    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseFirestore db;
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMypageBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.viewPagerMypage.setAdapter(new ViewpagerAdapter(getChildFragmentManager()));
        binding.tablayoutMypage.setupWithViewPager(binding.viewPagerMypage);


    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentMypageBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        ArrayList<UsersCategory> list = new ArrayList<>();
        UserCategoryAdapter adapter = new UserCategoryAdapter(list,getContext());
        binding.mypageChipRecyclerview.setHasFixedSize(true);
        binding.mypageChipRecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        binding.mypageChipRecyclerview.setLayoutManager(layoutManager);

        //ProfileImage Click intent to MypageDetailActivity
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Mypage_Detail로 넘어가는 프로필 클릭");
                Intent intent = new Intent(getActivity().getApplicationContext(), MypageDetailActivity.class);
                startActivity(intent);

            }
        });

        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getUid());
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                Handler mHandler = new Handler(); mHandler.postDelayed(new Runnable() { public void run() {}
                }, 1000); // 0.5초후
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());
                    binding.username.setText(snapshot.getData().get("name").toString());
                    binding.userMypageHistory.setText(snapshot.getData().get("userinfo").toString());
                    binding.userinfo.setText(snapshot.getData().get("userinfo").toString());
                    if (snapshot.getData().get("profilepic") !=null)
                    Glide.with(getActivity()).
                            load(snapshot.getData().get("profilepic")).centerCrop().override(500).placeholder(R.drawable.ic_group80).into(binding.profileImage);
                    else if(snapshot.getData().get("profilepic") ==null){
                        Glide.with(getActivity()).
                                load(R.drawable.ic_group80).centerCrop().override(500).into(binding.profileImage);
                    }

                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });

        CollectionReference docategoryRef = db.collection("users").document(FirebaseAuth.getInstance().getUid()).collection("category");


        Query query = docategoryRef.orderBy("value", Query.Direction.DESCENDING).limit(3);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() { //최대값 3개가져옴

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        UsersCategory category = document.toObject(UsersCategory.class);

                        list.add(category);
                    }
                    Log.d(TAG, "Category 값 가져오기 성공 ");

                }else{
                    Log.d(TAG, "Current data: null");
                }adapter.notifyDataSetChanged();
            }
        });
//        @Override
//        public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
//            Handler mHandler = new Handler(); mHandler.postDelayed(new Runnable() { public void run() {}
//            }, 1000); // 0.5초후
//            if (e != null) {
//                Log.w(TAG, "Listen failed.", e);
//                return;
//            }
//            if (snapshot != null && snapshot.exists()) {
//                binding.textView5.setText(snapshot.getData().toString());
//                binding.textView4.setText(snapshot.getData().toString());
//                binding.textView6.setText(snapshot.getData().toString());
//
//
//                }else{
//                Log.d(TAG, "Current data: null");
//            }
//        }
//        });





       //로그아웃 버튼
        binding.logout.setOnClickListener(new View.OnClickListener() {
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
    private void startToast(String msg) { Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show(); }
}

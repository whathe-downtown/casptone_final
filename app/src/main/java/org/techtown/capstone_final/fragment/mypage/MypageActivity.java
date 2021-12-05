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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import org.techtown.capstone_final.Detail.MypageDetail.MypageDetailActivity;
import org.techtown.capstone_final.R;
import org.techtown.capstone_final.databinding.FragmentMypageBinding;
import org.techtown.capstone_final.fragment.mypage.Adapters.ViewpagerAdapter;

public class MypageActivity extends Fragment {
    private static final String TAG = "MyapgeActivity";
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentMypageBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.viewPagerMypage.setAdapter(new ViewpagerAdapter(getActivity().getSupportFragmentManager()));
        binding.tablayoutMypage.setupWithViewPager(binding.viewPagerMypage);

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
                    binding.userinfo.setText(snapshot.getData().get("status").toString());
                    binding.username.setText(snapshot.getData().get("name").toString());
                    binding.userMypageHistory.setText(snapshot.getData().get("userhistory").toString());
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
                    Log.d(TAG, "$onComplete: 값이 잘 받아왔나");
                    String textview5 = task.toString();
                    binding.textView5.setText(textview5);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                startToast("Query 동작 실패");
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





//       로그아웃 버튼
//        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG,"clicked the logout Button");
//                auth.signOut();
//                Intent intent = new Intent(getActivity().getApplicationContext(), SignInActivity.class);
//                startActivity(intent);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                Toast.makeText(getActivity(), "로그아웃이 되었습니다.", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        return  view;

    }


    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
    private void startToast(String msg) { Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show(); }
}

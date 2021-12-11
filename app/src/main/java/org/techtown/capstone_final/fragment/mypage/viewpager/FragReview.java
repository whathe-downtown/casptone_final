package org.techtown.capstone_final.fragment.mypage.viewpager;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.techtown.capstone_final.Adapters.ReviewAdapter;
import org.techtown.capstone_final.Model.Review;
import org.techtown.capstone_final.databinding.ActivityFragReviewBinding;

import java.util.ArrayList;

public class FragReview extends Fragment {

    private FirebaseFirestore db;

    private ActivityFragReviewBinding binding;
    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityFragReviewBinding.inflate(inflater,container,false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        ArrayList<Review> list= new ArrayList<>();
        ReviewAdapter adapter = new ReviewAdapter(list,getContext());
        binding.reviewRecyclerview.setHasFixedSize(true);
        binding.reviewRecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.reviewRecyclerview.setLayoutManager(layoutManager);

        CollectionReference coRef = db.collection("review");

        coRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Review review= document.toObject(Review.class);
                        list.add(review);

                    }
                }else{
                    Log.d("FragRecord", "Current data: null");


                }adapter.notifyDataSetChanged();
            }
        });
        return binding.getRoot();
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_frag_record);
//    }
}
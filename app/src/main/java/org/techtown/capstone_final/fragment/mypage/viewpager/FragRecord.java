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

import org.techtown.capstone_final.Adapters.RecordAdapter;
import org.techtown.capstone_final.Model.Record;
import org.techtown.capstone_final.databinding.ActivityFragRecordBinding;

import java.util.ArrayList;

public class FragRecord extends Fragment {
    private FirebaseFirestore db;
    private View view;

    public  FragRecord (){ }
    private ActivityFragRecordBinding binding;
    private FirebaseAuth auth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityFragRecordBinding.inflate(inflater,container,false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        View view = binding.getRoot();

        ArrayList<Record> list= new ArrayList<>();
        RecordAdapter adapter = new RecordAdapter(list,getContext());
        binding.recordRecyclerview.setHasFixedSize(true);
        binding.recordRecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recordRecyclerview.setLayoutManager(layoutManager);

        CollectionReference coRef = db.collection("record");

        coRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Record record= document.toObject(Record.class);
                        list.add(record);

                    }
                }else{
                    Log.d("FragRecord", "Current data: null");


                }adapter.notifyDataSetChanged();
            }
        });



        return view;
    }

}
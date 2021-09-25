package org.techtown.capstone_final.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.techtown.capstone_final.Adapters.UsersAdapter;
import org.techtown.capstone_final.Model.Users;
import org.techtown.capstone_final.databinding.FragmentGroupchatBinding;

import java.util.ArrayList;

public class GroupchatActivity extends Fragment {

    public GroupchatActivity(){
        // Required empty public constructor
    }

    FragmentGroupchatBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseDatabase database;


    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        database = FirebaseDatabase.getInstance();
        binding = FragmentGroupchatBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        UsersAdapter adapter = new UsersAdapter(list, getContext());
        binding.chatRecyclarview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.chatRecyclarview.setLayoutManager(layoutManager);

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Users users = dataSnapshot.getValue(Users.class);
                    users.getUserId(dataSnapshot.getKey());// get userId가 getKey받아서 메인값으로 넣어줌
                    list.add(users);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


        return view;
    }
}

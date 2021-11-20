package org.techtown.capstone_final.fragment.Home.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.techtown.capstone_final.Adapters.RoomsAdapter;
import org.techtown.capstone_final.Model.Room;
import org.techtown.capstone_final.databinding.FragmentCompetitionBinding;

import java.util.ArrayList;


public class CompetitionFragment extends Fragment {

    public CompetitionFragment(){

    }
    FragmentCompetitionBinding binding;
    ArrayList<Room> list  = new ArrayList<>();
    FirebaseDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompetitionBinding.inflate(inflater, container, false);
        database = FirebaseDatabase.getInstance();

        RoomsAdapter adapter = new RoomsAdapter(list, getContext());
        binding.roomRecyclarView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.roomRecyclarView.setLayoutManager(layoutManager);

        database.getReference().child("Room").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Room room = dataSnapshot.getValue(Room.class);
                    room.getRoomTitle();
                    list.add(room);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        return  binding.getRoot();
    }
}
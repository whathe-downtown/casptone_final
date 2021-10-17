package org.techtown.capstone_final.fragment.Home.viewpager;

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

import org.jetbrains.annotations.NotNull;
import org.techtown.capstone_final.Adapters.RoomsAdapter;
import org.techtown.capstone_final.Model.Room;
import org.techtown.capstone_final.databinding.FragmentMentorBinding;

import java.util.ArrayList;


public class MentorFragment extends Fragment {



    public MentorFragment() {
        // Required empty public constructor
    }
    FragmentMentorBinding binding;
    ArrayList<Room> list = new ArrayList<>();
    FirebaseDatabase database;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        database = FirebaseDatabase.getInstance();
        binding = FragmentMentorBinding.inflate(inflater, container, false);

        RoomsAdapter adapter =  new RoomsAdapter(list, getContext());
        binding.MentorrecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.MentorrecyclerView.setLayoutManager(layoutManager);

        database.getReference().child("Room").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Room room = dataSnapshot.getValue(Room.class);
                    room.getRoomTitle();
                    room.getRoomprofilepic();
                    room.getRoomDate();
                    room.getRoomPlace();
                    room.setRoomId(dataSnapshot.getKey()); // set RoomId가 setkey받아서 메인값으로 넘겨줌
                    list.add(room);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return binding.getRoot();
    }
}
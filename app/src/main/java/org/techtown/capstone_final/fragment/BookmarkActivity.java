package org.techtown.capstone_final.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.techtown.capstone_final.databinding.FragmentBookmarkBinding;

public class BookmarkActivity extends Fragment {

    private FragmentBookmarkBinding binding;


    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        binding = FragmentBookmarkBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return  view;
    }
}

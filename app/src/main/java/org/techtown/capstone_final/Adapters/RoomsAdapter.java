package org.techtown.capstone_final.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomViewHolder> {
    @NonNull

    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull  RoomsAdapter.RoomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        public RoomViewHolder(@NonNull  View itemView) {
            super(itemView);
        }
    }
}

package org.techtown.capstone_final.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.capstone_final.Model.Record;
import org.techtown.capstone_final.R;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private static final String TAG = "RecordAdapter";
    private final ArrayList<Record> list;
    private final Context context;

    public RecordAdapter(ArrayList<Record> recordList, Context context) {
        this.list = recordList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_record, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {

        Record record = list.get(position);
        holder.roomtitle.setText(record.getRoomtitle());
        holder.roomcategory.setText(record.getRoomcategory());
        holder.roomlocation.setText(record.getRoomlocation());
        holder.roomdate.setText(record.getRoomdate());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  roomtitle , roomcategory, roomlocation , roomdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roomtitle = itemView.findViewById(R.id.recordTitle);
            roomcategory= itemView.findViewById(R.id.recordCategory);
            roomlocation = itemView.findViewById(R.id.recordlocation);
            roomdate = itemView.findViewById(R.id.recordDate);
        }
    }
    private  void startToast(String msg){ Toast.makeText(context, msg, Toast.LENGTH_SHORT).show(); }
}

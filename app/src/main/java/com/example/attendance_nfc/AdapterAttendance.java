package com.example.attendance_nfc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class AdapterAttendance extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private LayoutInflater inflater;
    private List<Attendance> data = Collections.emptyList();

    AdapterAttendance(Context context, List<Attendance> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_daily, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position){

        MyHolder myHolder = (MyHolder) holder;
    }
}

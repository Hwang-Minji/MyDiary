package com.minji.mydiary.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minji.mydiary.R;
import com.minji.mydiary.presenter.MainPresenter;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final String TAG = "MyAdapter";
    private List<String> dateList;
    private MainPresenter presenter;

    public MyAdapter(List<String> dateList, MainPresenter presenter) {
        this.dateList = dateList;
        this.presenter = presenter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;

        public ViewHolder(View view) {
            super(view);
            dateTextView = view.findViewById(R.id.timeline_title);
        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {
        viewHolder.dateTextView.setText(dateList.get(index));
        viewHolder.dateTextView.setOnClickListener(new ClickListener(index));
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    //Implement OnClickListener
    private class ClickListener implements View.OnClickListener {
        private int index;

        public ClickListener(int index) {
            this.index = index;
            //Log.d(TAG, "index: " + index);
        }

        @Override
        public void onClick(View v) {
            presenter.requestPostDAO(dateList.get(index));
            Log.d(TAG, dateList.get(index));
        }
    }
}

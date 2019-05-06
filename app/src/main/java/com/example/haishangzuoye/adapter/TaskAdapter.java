package com.example.haishangzuoye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haishangzuoye.R;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List list;
    private OnItemClickListener onItemClickListener;

    public TaskAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.title.setText(((Map) list.get(position)).get("taskTitle") + "");
        viewHolder.addr.setText(((Map) list.get(position)).get("taskAddress") + "");
        viewHolder.date.setText(((Map) list.get(position)).get("taskDate") + "");
        viewHolder.num.setText(((Map) list.get(position)).get("taskNumber") + "");
        viewHolder.type.setText(((Map) list.get(position)).get("taskType") + "");
        String status = ((Map) list.get(position)).get("taskStatus") + "";
        if ("0".equals(status)) {
            viewHolder.status.setText("未接收");
        } else {
            viewHolder.status.setText("进行中");
        }

        viewHolder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_title)
        TextView title;
        @BindView(R.id.task_status)
        TextView status;
        @BindView(R.id.task_addr)
        TextView addr;
        @BindView(R.id.task_date)
        TextView date;
        @BindView(R.id.task_type)
        TextView type;
        @BindView(R.id.task_num)
        TextView num;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(int position);
    }
}

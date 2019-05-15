package com.example.haishangzuoye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haishangzuoye.R;
import com.example.haishangzuoye.info.CommentInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息页面适配器
 */
public class CommentAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<CommentInfo> list;

    public CommentAdapter(Context context, List<CommentInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        CommentInfo commentInfo = list.get(position);
        viewHolder.time.setText("留言时间：" + commentInfo.getCreate_time());
        viewHolder.content.setText("留言内容：" + commentInfo.getContent());
        viewHolder.name.setText("留言人：" + commentInfo.getRealName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.name)
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.example.qxx0101.haishangzuoye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qxx0101.haishangzuoye.R;

import net.tsz.afinal.FinalBitmap;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends RecyclerView.Adapter {

    private Context context;
    private List list;

    public MessageAdapter(Context context, List list) {
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
        String title = ((Map) list.get(position)).get("messageTitle") + "";
        viewHolder.title.setText(title);
        viewHolder.content.setText(((Map) list.get(position)).get("messageContent") + "");
        String imgUrl = ((Map) list.get(position)).get("messageImg") + "";
        imgUrl = "https://www.baidu.com/img/bd_logo1.png?where=super";
        FinalBitmap bitmap = FinalBitmap.create(context);
        bitmap.configBitmapMaxHeight(100);
        bitmap.configBitmapMaxWidth(200);
        bitmap.display(viewHolder.imageView, imgUrl);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message_img)
        ImageView imageView;
        @BindView(R.id.message_title)
        TextView title;
        @BindView(R.id.message_content)
        TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

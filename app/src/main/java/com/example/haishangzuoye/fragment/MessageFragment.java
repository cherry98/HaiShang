package com.example.haishangzuoye.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.example.haishangzuoye.R;
import com.example.haishangzuoye.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    public MessageFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.message_recyleview)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMessageList();
    }

    private void getMessageList() {
        String json = "[\n" +
            "    {\n" +
            "        \"messageImg\":\"\",\n" +
            "        \"messageTitle\":\"消息标题\",\n" +
            "        \"messageContent\":\"消息内容\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"messageImg\":\"\",\n" +
            "        \"messageTitle\":\"消息标题\",\n" +
            "        \"messageContent\":\"消息内容\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"messageImg\":\"\",\n" +
            "        \"messageTitle\":\"消息标题\",\n" +
            "        \"messageContent\":\"消息内容\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"messageImg\":\"\",\n" +
            "        \"messageTitle\":\"消息标题\",\n" +
            "        \"messageContent\":\"消息内容\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"messageImg\":\"\",\n" +
            "        \"messageTitle\":\"消息标题\",\n" +
            "        \"messageContent\":\"消息内容\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"messageImg\":\"\",\n" +
            "        \"messageTitle\":\"消息标题\",\n" +
            "        \"messageContent\":\"消息内容\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"messageImg\":\"\",\n" +
            "        \"messageTitle\":\"消息标题\",\n" +
            "        \"messageContent\":\"消息内容\"\n" +
            "    }\n" +
            "]";
        ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) JSON.parseObject(json, List.class);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        MessageAdapter messageAdapter = new MessageAdapter(getContext(), list);
        recyclerView.setAdapter(messageAdapter);
    }
}

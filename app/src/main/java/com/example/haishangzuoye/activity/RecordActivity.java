package com.example.haishangzuoye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
import com.example.haishangzuoye.R;
import com.example.haishangzuoye.adapter.TaskAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordActivity extends BaseActivity {

    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);
        setToolBar();
        getTaskList();
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("日志记录");
    }

    private void getTaskList() {
        String json = "[\n" +
            "    {\n" +
            "        \"taskId\":\"1\",\n" +
            "        \"taskTitle\":\"任务标题1\",\n" +
            "        \"taskType\":\"任务类型1\",\n" +
            "        \"taskStatus\":\"0（未接收）1（正在进行）\",\n" +
            "        \"taskAddress\":\"任务地点1\",\n" +
            "        \"taskDate\":\"任务时间1\",\n" +
            "        \"taskNumber\":\"任务需要人数1\",\n" +
            "\"taskContent\":\"哈哈哈哈啊哈哈哈哈\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"taskId\":\"1\",\n" +
            "        \"taskTitle\":\"任务标题2\",\n" +
            "        \"taskType\":\"任务类型2\",\n" +
            "        \"taskStatus\":\"0（未接收）1（正在进行）\",\n" +
            "        \"taskAddress\":\"任务地点2\",\n" +
            "        \"taskDate\":\"任务时间2\",\n" +
            "        \"taskNumber\":\"任务需要人数2\",\n" +
            "\"taskContent\":\"哈哈哈哈啊哈哈哈哈\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"taskId\":\"1\",\n" +
            "        \"taskTitle\":\"任务标题3\",\n" +
            "        \"taskType\":\"任务类型3\",\n" +
            "        \"taskStatus\":\"0（未接收）1（正在进行）\",\n" +
            "        \"taskAddress\":\"任务地点3\",\n" +
            "        \"taskDate\":\"任务时间3\",\n" +
            "        \"taskNumber\":\"任务需要人数3\",\n" +
            "\"taskContent\":\"哈哈哈哈啊哈哈哈哈\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"taskId\":\"1\",\n" +
            "        \"taskTitle\":\"任务标题4\",\n" +
            "        \"taskType\":\"任务类型4\",\n" +
            "        \"taskStatus\":\"0（未接收）1（正在进行）\",\n" +
            "        \"taskAddress\":\"任务地点4\",\n" +
            "        \"taskDate\":\"任务时间4\",\n" +
            "        \"taskNumber\":\"任务需要人数\",\n" +
            "\"taskContent\":\"哈哈哈哈啊哈哈哈哈\"\n" +
            "    }\n" +
            "]";
//        ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) JSON.parseObject(json, List.class);
//        TaskAdapter taskAdapter = new TaskAdapter(this, list);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(taskAdapter);
    }
}

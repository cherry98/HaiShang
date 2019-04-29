package com.example.qxx0101.haishangzuoye.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.example.qxx0101.haishangzuoye.R;
import com.example.qxx0101.haishangzuoye.activity.AttendanceActivity;
import com.example.qxx0101.haishangzuoye.activity.PublishActivity;
import com.example.qxx0101.haishangzuoye.activity.TaskDetailActivity;
import com.example.qxx0101.haishangzuoye.adapter.TaskAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkFragment extends Fragment {


    public WorkFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.task_recyleview)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_work, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getTaskList();
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
        ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) JSON.parseObject(json, List.class);
        TaskAdapter taskAdapter = new TaskAdapter(getContext(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(getContext(), TaskDetailActivity.class);
            intent.putExtra("taskTitle", ((Map) list.get(position)).get("taskTitle").toString());
            intent.putExtra("taskAddress", ((Map) list.get(position)).get("taskAddress").toString());
            intent.putExtra("taskDate", ((Map) list.get(position)).get("taskDate").toString());
            intent.putExtra("taskNumber", ((Map) list.get(position)).get("taskNumber").toString());
            intent.putExtra("taskType", ((Map) list.get(position)).get("taskType").toString());
            intent.putExtra("taskContent", ((Map) list.get(position)).get("taskContent").toString());
            startActivity(intent);
        });
    }

    @OnClick(R.id.publish)
    public void publishClick() {
        startActivity(new Intent(getContext(), PublishActivity.class));
    }

    @OnClick(R.id.record)
    public void recordClick() {
    }

    @OnClick(R.id.attendance)
    public void attendanceClick() {
        startActivity(new Intent(getContext(), AttendanceActivity.class));
    }
}

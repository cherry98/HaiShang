package com.example.haishangzuoye.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.haishangzuoye.R;
import com.example.haishangzuoye.Utils.Api;
import com.example.haishangzuoye.Utils.SharedPreferencesUtils;
import com.example.haishangzuoye.adapter.TaskAdapter;
import com.example.haishangzuoye.info.TaskInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskHistoryActivity extends BaseActivity {

    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    private ArrayList<TaskInfo> list = new ArrayList();
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_history);
        ButterKnife.bind(this);
        setToolBar();
        taskAdapter = new TaskAdapter(this, list, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(taskAdapter);
        getTaskList();
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("历史任务");
    }

    private void getTaskList() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://wxooxw.com:8180/sea/seaController/") // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
            .build();
        Api api = retrofit.create(Api.class);
        Map<String, String> params = new HashMap<>();
        params.put("userId", SharedPreferencesUtils.getUserId(this));
        String vars = new JSONObject(params).toString();
        Call<ResponseBody> call = api.userAllTaskList(vars);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = new String(response.body().bytes());

                    JSONObject jsonObject = new JSONObject(str);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        JSONArray jsonArray = jsonObject.optJSONArray("result");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            TaskInfo taskInfo = new TaskInfo();
                            taskInfo.setCreate_time(jsonObject1.getString("create_time"));
                            taskInfo.setTaskAddress(jsonObject1.getString("taskAddress"));
                            taskInfo.setTaskContent(jsonObject1.getString("taskContent"));
                            taskInfo.setTaskDate(jsonObject1.getString("taskDate"));
                            taskInfo.setTaskId(jsonObject1.getString("taskId"));
                            taskInfo.setType_id(jsonObject1.getString("type_id"));
                            taskInfo.setTaskNumber(jsonObject1.getString("taskNumber"));
                            taskInfo.setTypeName(jsonObject1.getString("typeName"));
                            taskInfo.setTaskTitle(jsonObject1.getString("taskTitle"));
                            taskInfo.setTaskStatus(jsonObject1.getString("taskStatus"));
                            taskInfo.setTaskHave(jsonObject1.getString("taskHave"));
                            list.add(taskInfo);
                        }
                        taskAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(TaskHistoryActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}

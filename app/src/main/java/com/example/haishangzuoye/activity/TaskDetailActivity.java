package com.example.haishangzuoye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haishangzuoye.R;
import com.example.haishangzuoye.Utils.Api;
import com.example.haishangzuoye.Utils.SharedPreferencesUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskDetailActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.address)
    TextView addr;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.number)
    TextView number;
    private String taskStatus;
    @BindView(R.id.accept_task)
    Button accept_task;
    private String taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        ButterKnife.bind(this);
        setToolBar();
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        taskStatus = intent.getStringExtra("taskStatus");
        taskId = intent.getStringExtra("taskId");
        if ("0".equals(taskStatus)) {
            accept_task.setText("接受任务");
        } else if ("1".equals(taskStatus)) {
            accept_task.setText("完成任务");
        } else {
            accept_task.setText("已完成");
        }
        title.setText("任务标题：" + intent.getStringExtra("taskTitle"));
        type.setText("任务类型：" + intent.getStringExtra("taskType"));
        addr.setText("任务地址：" + intent.getStringExtra("taskAddress"));
        date.setText("任务时间：" + intent.getStringExtra("taskDate"));
        content.setText("任务内容：" + intent.getStringExtra("taskContent"));
        number.setText("任务需要人数：" + intent.getStringExtra("taskNumber"));
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setTitle("任务详情");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick(R.id.accept_task)
    public void acceptClick() {
        if (!"2".equals(taskStatus)) {
            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://wxooxw.com:8180/sea/seaController/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
            Api api = retrofit.create(Api.class);
            Map<String, String> params = new HashMap<>();
            params.put("userId", SharedPreferencesUtils.getUserId(this));
            params.put("taskId", taskId);
            String vars = new JSONObject(params).toString();
            Call<ResponseBody> call = null;
            if ("0".equals(taskStatus)) {
                call = api.acceptTask(vars);
            } else if ("1".equals(taskStatus)) {
                call = api.overTask(vars);
            }
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String str = new String(response.body().bytes());

                        JSONObject jsonObject = new JSONObject(str);
                        int status = jsonObject.getInt("status");
                        if (status == 1) {
                            Toast.makeText(TaskDetailActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(TaskDetailActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
}

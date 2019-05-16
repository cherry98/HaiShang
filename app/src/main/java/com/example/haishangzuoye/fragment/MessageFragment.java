package com.example.haishangzuoye.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haishangzuoye.R;
import com.example.haishangzuoye.Utils.Api;
import com.example.haishangzuoye.Utils.SharedPreferencesUtils;
import com.example.haishangzuoye.activity.CommentActivity;
import com.example.haishangzuoye.adapter.TaskAdapter;
import com.example.haishangzuoye.info.TaskInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
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


/**
 * 消息页面
 */
public class MessageFragment extends Fragment {


    public MessageFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.message_recyleview)
    RecyclerView recyclerView;
    private ArrayList<TaskInfo> list = new ArrayList<>();
    private TaskAdapter taskAdapter;

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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        taskAdapter = new TaskAdapter(getContext(), list);
        taskAdapter.setMessage(true);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), CommentActivity.class);
                intent.putExtra("taskId", list.get(position).getTaskId());
                startActivity(intent);
            }

            @Override
            public void comment(int position) {
                EditText et = new EditText(getContext());
                new AlertDialog.Builder(getContext())
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle("请输入您要评论的内容")
                    .setView(et)
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        addMessage(et.getText().toString(), list.get(position).getTaskId());
                    }).show();
            }
        });
    }

    @Override
    public void onResume() {
        getMessageList();
        super.onResume();
    }

    private void getMessageList() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://wxooxw.com:8180/sea/seaController/") // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
            .build();
        Api api = retrofit.create(Api.class);
        Map<String, String> params = new HashMap<>();
        params.put("userId", SharedPreferencesUtils.getUserId(getContext()));
        String vars = new JSONObject(params).toString();
        Call<ResponseBody> call = api.getUserAllTask(vars);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = new String(response.body().bytes());

                    JSONObject jsonObject = new JSONObject(str);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        if (!list.isEmpty()) {
                            list.clear();
                        }
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
                        Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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

    private void addMessage(String content, String taskId) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://wxooxw.com:8180/sea/seaController/") // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
            .build();
        Api api = retrofit.create(Api.class);
        Map<String, String> params = new HashMap<>();
        params.put("userId", SharedPreferencesUtils.getUserId(getContext()));
        params.put("taskId", taskId);
        params.put("content", content);
        String vars = new JSONObject(params).toString();
        Call<ResponseBody> call = api.addMessage(vars);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = new String(response.body().bytes());

                    JSONObject jsonObject = new JSONObject(str);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        Toast.makeText(getContext(), "评论成功", Toast.LENGTH_SHORT).show();
                        getMessageList();
                    } else {
                        Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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

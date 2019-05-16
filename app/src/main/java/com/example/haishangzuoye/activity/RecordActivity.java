package com.example.haishangzuoye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.haishangzuoye.R;
import com.example.haishangzuoye.Utils.Api;
import com.example.haishangzuoye.Utils.SharedPreferencesUtils;
import com.example.haishangzuoye.adapter.TaskAdapter;
import com.example.haishangzuoye.info.RecordInfo;
import com.example.haishangzuoye.info.TaskInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 日志
 */
public class RecordActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ArrayList<RecordInfo> list = new ArrayList<>();
    private RecordAdapter recordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);
        setToolBar();
        recordAdapter = new RecordAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recordAdapter);
        recordAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(RecordActivity.this, NewRecordActivity.class);
            intent.putExtra("title", list.get(position).getTitle());
            intent.putExtra("content", list.get(position).getContent());
            intent.putExtra("type", "0");
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        getLogList();
        super.onResume();
    }

    @OnClick(R.id.fab)
    public void fabClick() {
        startActivity(new Intent(this, NewRecordActivity.class));
    }

    private void getLogList() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://wxooxw.com:8180/sea/seaController/") // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
            .build();
        Api api = retrofit.create(Api.class);
        Map<String, String> params = new HashMap<>();
        params.put("userId", SharedPreferencesUtils.getUserId(this));
        String vars = new JSONObject(params).toString();
        Call<ResponseBody> call = api.logList(vars);
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
                            RecordInfo recordInfo = new RecordInfo();
                            recordInfo.setContent(jsonObject1.getString("content"));
                            recordInfo.setCreate_time(jsonObject1.getString("create_time"));
                            recordInfo.setTitle(jsonObject1.getString("title"));
                            list.add(recordInfo);
                        }
                        recordAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(RecordActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("日志记录");
    }

}

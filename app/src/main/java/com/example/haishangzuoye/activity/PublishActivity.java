package com.example.haishangzuoye.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haishangzuoye.R;
import com.example.haishangzuoye.Utils.Api;
import com.example.haishangzuoye.Utils.SharedPreferencesUtils;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublishActivity extends BaseActivity {

    @BindView(R.id.input_title)
    EditText title;
    @BindView(R.id.input_addr)
    EditText address;
    @BindView(R.id.input_content)
    EditText content;
    @BindView(R.id.input_date)
    EditText date;
    @BindView(R.id.input_num)
    EditText number;
    @BindView(R.id.select_type)
    TextView select_type;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        setToolBar();

    }


    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("发布任务");
        }
    }

    @OnClick(R.id.publish)
    public void publishClick() {
        if (TextUtils.isEmpty(title.getText().toString())) {
            Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(type)) {
            Toast.makeText(this, "请选择类型", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(address.getText().toString())) {
            Toast.makeText(this, "请输入地点", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(date.getText().toString())) {
            Toast.makeText(this, "请输入时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(number.getText().toString())) {
            Toast.makeText(this, "请输入需求人数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(content.getText().toString())) {
            Toast.makeText(this, "请输入任务内容", Toast.LENGTH_SHORT).show();
            return;
        }
        //调用发布接口

        publish();
    }

    private void publish() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://wxooxw.com:8180/sea/seaController/") // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
            .build();
        Api api = retrofit.create(Api.class);
        Map<String, String> params = new HashMap<>();
        params.put("taskTitle", title.getText().toString());
        params.put("type_id", type);
        params.put("taskAddress", address.getText().toString());
        params.put("taskDate", date.getText().toString());
        params.put("taskNumber", number.getText().toString());
        params.put("taskContent", content.getText().toString());
        params.put("userId", SharedPreferencesUtils.getUserId(this));
        String vars = new JSONObject(params).toString();
        Call<ResponseBody> call = api.fabuTask(vars);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = new String(response.body().bytes());

                    JSONObject jsonObject = new JSONObject(str);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        Toast.makeText(PublishActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(PublishActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.select_type)
    public void selectType() {
        String[] items = {"救援", "渔业", "开采业", "旅游业"};
        new AlertDialog.Builder(this)
            .setIcon(R.mipmap.ic_launcher)
            .setTitle("请选择")
            .setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    type = (i + 1) + "";
                    select_type.setText(items[i]);
                }
            }).show();
    }
}

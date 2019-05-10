package com.example.haishangzuoye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haishangzuoye.R;
import com.example.haishangzuoye.Utils.Api;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.input_account)
    EditText account;
    @BindView(R.id.input_phone)
    EditText phone;
    @BindView(R.id.input_pwd)
    EditText pwd;
    @BindView(R.id.input_pwd2)
    EditText pwd2;
    @BindView(R.id.input_name)
    EditText name;
    @BindView(R.id.input_nickname)
    EditText nickname;
    @BindView(R.id.input_sex)
    EditText sex;
    @BindView(R.id.input_org)
    EditText org;
    @BindView(R.id.input_age)
    EditText age;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.code)
    EditText code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        int type = getIntent().getIntExtra("type", 0);
        if (type == 0) {//注册
            submit.setText("注册");
        } else {
            submit.setText("修改");
        }
    }

    @OnClick(R.id.submit)
    public void submitClick() {
        if (TextUtils.isEmpty(account.getText().toString())) {
            Toast.makeText(this, "请输入您的账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(code.getText().toString())) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd.getText().toString())) {
            Toast.makeText(this, "请输入您的密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd2.getText().toString())) {
            Toast.makeText(this, "请再次输入您的密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.getText().toString().equals(pwd2.getText().toString())) {
            Toast.makeText(this, "前后两次密码不一致，请重新输入您的密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(nickname.getText().toString())) {
            Toast.makeText(this, "请输入您的昵称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name.getText().toString())) {
            Toast.makeText(this, "请输入您的真实姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(sex.getText().toString())) {
            Toast.makeText(this, "请输入您的性别", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(age.getText().toString())) {
            Toast.makeText(this, "请输入您的年龄", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone.getText().toString())) {
            Toast.makeText(this, "请输入您的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(org.getText().toString())) {
            Toast.makeText(this, "请输入您的工作单位", Toast.LENGTH_SHORT).show();
            return;
        }
        register();
    }

    private void register() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://wxooxw.com:8180/sea/seaController/") // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
            .build();
        Api api = retrofit.create(Api.class);
        Map<String, String> params = new HashMap<>();
        params.put("phone", account.getText().toString());
        params.put("pwd", pwd.getText().toString());
        params.put("code", "111111");
        params.put("sex", sex.getText().toString());
        params.put("age", age.getText().toString());
        params.put("nickName", nickname.getText().toString());
        params.put("realName", name.getText().toString());
        params.put("workAddress", org.getText().toString());
        params.put("realPhone", phone.getText().toString());
        String vars = new JSONObject(params).toString();
        Call<ResponseBody> call = api.register(vars);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = new String(response.body().bytes());

                    JSONObject jsonObject = new JSONObject(str);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("account", account.getText().toString());
                        intent.putExtra("pwd", pwd.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}

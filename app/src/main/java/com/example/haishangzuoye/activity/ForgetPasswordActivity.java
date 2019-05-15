package com.example.haishangzuoye.activity;

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

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haishangzuoye.MainActivity;
import com.example.haishangzuoye.R;
import com.example.haishangzuoye.Utils.Api;
import com.example.haishangzuoye.Utils.SharedPreferencesUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity {

    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.input_phone)
    EditText phone;
    @BindView(R.id.input_pwd)
    EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.codeButton)
    public void codeButtonClick() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                code.setText("111111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @OnClick(R.id.submit)
    public void submitClick() {
        if (TextUtils.isEmpty(phone.getText().toString())) {
            Toast.makeText(this, "请输入您的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd.getText().toString())) {
            Toast.makeText(this, "请输入您的密码", Toast.LENGTH_SHORT).show();
            return;
        }
        change();
    }

    private void change() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://wxooxw.com:8180/sea/seaController/") // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
            .build();
        Api api = retrofit.create(Api.class);
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone.getText().toString());
        params.put("pwd", pwd.getText().toString());
        params.put("code", "111111");
        String vars = new JSONObject(params).toString();
        Call<ResponseBody> call = api.editPwd(vars);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = new String(response.body().bytes());

                    JSONObject jsonObject = new JSONObject(str);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        Toast.makeText(ForgetPasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("account", phone.getText().toString());
                        intent.putExtra("pwd", pwd.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(ForgetPasswordActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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

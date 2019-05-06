package com.example.haishangzuoye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haishangzuoye.R;
import com.example.haishangzuoye.Utils.Urls;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.input_phone)
    EditText phone;
    @BindView(R.id.input_pwd)
    EditText pwd;
    @BindView(R.id.input_pwd2)
    EditText pwd2;
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
        if (TextUtils.isEmpty(phone.getText().toString())) {
            Toast.makeText(this, "请输入您的手机号码", Toast.LENGTH_SHORT).show();
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
        register();
    }

    private void register() {
        FinalHttp finalHttp = new FinalHttp();
//        Map<String, String> params = new HashMap<>();
        AjaxParams params=new AjaxParams();
        params.put("phone", phone.getText().toString());
        params.put("pwd", pwd.getText().toString());
        params.put("code", "111111");
//        JSONObject jsonObject = new JSONObject(params);
        finalHttp.get(Urls.URL + "register?vars=", params, new AjaxCallBack<Object>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onSuccess(Object o) {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("account", phone.getText().toString());
                intent.putExtra("pwd", pwd.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
}

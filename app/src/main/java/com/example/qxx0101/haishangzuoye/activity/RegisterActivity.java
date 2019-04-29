package com.example.qxx0101.haishangzuoye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qxx0101.haishangzuoye.R;

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
        //TODO
        Intent intent = new Intent();
        intent.putExtra("account", phone.getText().toString());
        intent.putExtra("pwd", pwd.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}

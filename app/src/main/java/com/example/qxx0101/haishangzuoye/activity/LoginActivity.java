package com.example.qxx0101.haishangzuoye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qxx0101.haishangzuoye.MainActivity;
import com.example.qxx0101.haishangzuoye.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.input_phone)
    EditText phone;
    @BindView(R.id.input_pwd)
    EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    public void loginClick() {
//        if (TextUtils.isEmpty(phone.getText().toString())) {
//            Toast.makeText(this, "请输入您的手机号码", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(pwd.getText().toString())) {
//            Toast.makeText(this, "请输入您的密码", Toast.LENGTH_SHORT).show();
//            return;
//        }
        //TODO
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.register)
    public void registerClick() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("type", 0);//注册
        startActivityForResult(intent, 0);
    }

    @OnClick(R.id.forget_pwd)
    public void forgetClick() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("type", 1);//修改密码
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 0 && data != null) {
            phone.setText(data.getStringExtra("account"));
            pwd.setText(data.getStringExtra("pwd"));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

package com.example.haishangzuoye.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.haishangzuoye.MainActivity;
import com.example.haishangzuoye.R;
import com.example.haishangzuoye.Utils.SharedPreferencesUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 欢迎页面
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.enter)
    public void enterClick() {
        if (SharedPreferencesUtils.getLoggedStatus(this)) {//已登录
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}

package com.example.haishangzuoye.activity;

import android.os.Bundle;

import com.example.haishangzuoye.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.ActionBar;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttendanceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        ButterKnife.bind(this);
        setToolBar();
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setTitle("考勤记录");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick(R.id.sign_in)
    public void signInOnClick() {//由于地点需要地图的sdk需要到开发者平台申请流程比较慢所以只做时间签到

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
    }

}

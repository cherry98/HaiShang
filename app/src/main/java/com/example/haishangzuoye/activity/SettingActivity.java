package com.example.haishangzuoye.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.haishangzuoye.R;
import com.example.haishangzuoye.Utils.SharedPreferencesUtils;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SwitchCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.setting_message)
    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        setToolBar();
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(SettingActivity.this, "已开启消息提醒", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingActivity.this, "已关闭消息提醒", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setTitle("设置");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void version_update(View view) {
        Toast.makeText(this, "敬请期待", Toast.LENGTH_SHORT).show();
    }

    public void button_loginout(View view) {
        SharedPreferencesUtils.setLoggedStatus(this, false);
    }
}

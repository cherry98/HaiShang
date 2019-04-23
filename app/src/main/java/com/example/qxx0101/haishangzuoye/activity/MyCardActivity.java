package com.example.qxx0101.haishangzuoye.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toolbar;

import com.example.qxx0101.haishangzuoye.R;

import androidx.annotation.RequiresApi;
import butterknife.BindView;

public class MyCardActivity extends Activity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);
        toolbar.setTitle("我的名片");
    }
}

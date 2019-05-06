package com.example.haishangzuoye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.haishangzuoye.R;

import androidx.appcompat.app.ActionBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskDetailActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.address)
    TextView addr;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.number)
    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        ButterKnife.bind(this);
        setToolBar();
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        title.setText(intent.getStringExtra("taskTitle"));
        type.setText(intent.getStringExtra("taskType"));
        addr.setText(intent.getStringExtra("taskAddress"));
        date.setText(intent.getStringExtra("taskDate"));
        content.setText(intent.getStringExtra("taskContent"));
        number.setText(intent.getStringExtra("taskNumber"));
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setTitle("任务详情");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick(R.id.accept_task)
    public void acceptClick() {
        finish();
    }
}

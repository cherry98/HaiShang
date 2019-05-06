package com.example.haishangzuoye.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haishangzuoye.R;

public class PublishActivity extends BaseActivity {

    @BindView(R.id.input_title)
    EditText title;
    @BindView(R.id.input_addr)
    EditText address;
    @BindView(R.id.input_content)
    EditText content;
    @BindView(R.id.input_date)
    EditText date;
    @BindView(R.id.input_num)
    EditText number;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        setToolBar();
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("发布任务");
        }
    }

    @OnClick(R.id.publish)
    public void publishClick() {
        if (TextUtils.isEmpty(title.getText().toString())) {
            Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(type)) {
            Toast.makeText(this, "请选择类型", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(address.getText().toString())) {
            Toast.makeText(this, "请输入地点", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(date.getText().toString())) {
            Toast.makeText(this, "请输入时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(number.getText().toString())) {
            Toast.makeText(this, "请输入需求人数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(content.getText().toString())) {
            Toast.makeText(this, "请输入任务内容", Toast.LENGTH_SHORT).show();
            return;
        }
        //调用发布接口
    }

    @OnClick(R.id.select_type)
    public void selectType() {
        String[] items = {"救援", "渔业", "开采业", "旅游业"};
        new AlertDialog.Builder(this)
            .setIcon(R.mipmap.ic_launcher)
            .setTitle("请选择")
            .setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i) {
                        case 0: {
                            type = "0";
                        }
                        break;
                        case 1: {
                            type = "1";
                        }
                        break;
                        case 2: {
                            type = "2";
                        }
                        break;
                        case 3: {
                            type = "3";
                        }
                        break;
                    }
                }
            }).show();
    }
}

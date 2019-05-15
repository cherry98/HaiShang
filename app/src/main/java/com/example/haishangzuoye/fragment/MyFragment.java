package com.example.haishangzuoye.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haishangzuoye.R;
import com.example.haishangzuoye.activity.MyCardActivity;
import com.example.haishangzuoye.activity.SettingActivity;
import com.example.haishangzuoye.activity.TaskHistoryActivity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 我的页面
 */
public class MyFragment extends Fragment implements View.OnClickListener {


    public MyFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.my_card)
    View myCard;
    @BindView(R.id.my_task)
    View myTask;
    @BindView(R.id.my_setting)
    View mySetting;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myCard.setOnClickListener(this);
        myTask.setOnClickListener(this);
        mySetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_card: {
                startActivity(new Intent(getContext(), MyCardActivity.class));
            }
            break;
            case R.id.my_task: {
                startActivity(new Intent(getContext(), TaskHistoryActivity.class));
            }
            break;
            case R.id.my_setting: {
                startActivity(new Intent(getContext(), SettingActivity.class));
            }
            break;
        }
    }
}

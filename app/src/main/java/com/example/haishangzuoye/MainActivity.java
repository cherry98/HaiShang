package com.example.haishangzuoye;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haishangzuoye.fragment.CalendarFragment;
import com.example.haishangzuoye.fragment.MessageFragment;
import com.example.haishangzuoye.fragment.MyFragment;
import com.example.haishangzuoye.fragment.WorkFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.main_work_text)
    TextView workText;
    @BindView(R.id.main_calender_text)
    TextView calenderText;
    @BindView(R.id.main_message_text)
    TextView messageText;
    @BindView(R.id.main_my_text)
    TextView myText;

    @BindView(R.id.main_work_img)
    ImageView workImg;
    @BindView(R.id.main_calender_img)
    ImageView calendarImg;
    @BindView(R.id.main_message_img)
    ImageView messageImg;
    @BindView(R.id.main_my_img)
    ImageView myImg;

    @BindView(R.id.main_work)
    View work;
    @BindView(R.id.main_calender)
    View calendar;
    @BindView(R.id.main_message)
    View message;
    @BindView(R.id.main_my)
    View my;

    private WorkFragment workFragment;
    private CalendarFragment calendarFragment;
    private MessageFragment messageFragment;
    private MyFragment myFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        work.setOnClickListener(this);
        calendar.setOnClickListener(this);
        message.setOnClickListener(this);
        my.setOnClickListener(this);
        onClick(work);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_work: {
                workImg.setImageResource(R.drawable.ic_work2);
                workText.setTextColor(getResources().getColor(R.color.colorAccent));
                calendarImg.setImageResource(R.drawable.ic_calendar);
                calenderText.setTextColor(getResources().getColor(R.color.colorPrimary));
                messageImg.setImageResource(R.drawable.ic_message);
                messageText.setTextColor(getResources().getColor(R.color.colorPrimary));
                myImg.setImageResource(R.drawable.ic_my);
                myText.setTextColor(getResources().getColor(R.color.colorPrimary));
                createWorkFragment();
            }
            break;
            case R.id.main_calender: {
                workImg.setImageResource(R.drawable.ic_work);
                workText.setTextColor(getResources().getColor(R.color.colorPrimary));
                calendarImg.setImageResource(R.drawable.ic_calendar2);
                calenderText.setTextColor(getResources().getColor(R.color.colorAccent));
                messageImg.setImageResource(R.drawable.ic_message);
                messageText.setTextColor(getResources().getColor(R.color.colorPrimary));
                myImg.setImageResource(R.drawable.ic_my);
                myText.setTextColor(getResources().getColor(R.color.colorPrimary));
                createCalendarFragment();
            }
            break;
            case R.id.main_message: {
                workImg.setImageResource(R.drawable.ic_work);
                workText.setTextColor(getResources().getColor(R.color.colorPrimary));
                calendarImg.setImageResource(R.drawable.ic_calendar);
                calenderText.setTextColor(getResources().getColor(R.color.colorPrimary));
                messageImg.setImageResource(R.drawable.ic_message2);
                messageText.setTextColor(getResources().getColor(R.color.colorAccent));
                myImg.setImageResource(R.drawable.ic_my);
                myText.setTextColor(getResources().getColor(R.color.colorPrimary));
                createMessageFragment();
            }
            break;
            case R.id.main_my: {
                workImg.setImageResource(R.drawable.ic_work);
                workText.setTextColor(getResources().getColor(R.color.colorPrimary));
                calendarImg.setImageResource(R.drawable.ic_calendar);
                calenderText.setTextColor(getResources().getColor(R.color.colorPrimary));
                messageImg.setImageResource(R.drawable.ic_message);
                messageText.setTextColor(getResources().getColor(R.color.colorPrimary));
                myImg.setImageResource(R.drawable.ic_my2);
                myText.setTextColor(getResources().getColor(R.color.colorAccent));
                createMyFragment();
            }
            break;
        }
    }

    private void createMyFragment() {
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        myFragment = (MyFragment) fragmentManager.findFragmentByTag("myFragment");
        if (myFragment == null) {
            myFragment = new MyFragment();
        }
        fragmentTransaction.replace(R.id.fragment, myFragment, "myFragment");
        fragmentTransaction.addToBackStack("myFragment");
        fragmentTransaction.commit();
    }

    private void createMessageFragment() {
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        messageFragment = (MessageFragment) fragmentManager.findFragmentByTag("messageFragment");
        if (messageFragment == null) {
            messageFragment = new MessageFragment();
        }
        fragmentTransaction.replace(R.id.fragment, messageFragment, "messageFragment");
        fragmentTransaction.addToBackStack("messageFragment");
        fragmentTransaction.commit();
    }

    private void createCalendarFragment() {
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        calendarFragment = (CalendarFragment) fragmentManager.findFragmentByTag("calendarFragment");
        if (calendarFragment == null) {
            calendarFragment = new CalendarFragment();
        }
        fragmentTransaction.replace(R.id.fragment, calendarFragment, "calendarFragment");
        fragmentTransaction.addToBackStack("calendarFragment");
        fragmentTransaction.commit();
    }

    private void createWorkFragment() {
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        workFragment = (WorkFragment) fragmentManager.findFragmentByTag("workFragment");
        if (workFragment == null) {
            workFragment = new WorkFragment();
        }
        fragmentTransaction.replace(R.id.fragment, workFragment, "workFragment");
        fragmentTransaction.addToBackStack("workFragment");
        fragmentTransaction.commitAllowingStateLoss();
    }
}

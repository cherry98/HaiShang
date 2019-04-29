package com.example.qxx0101.haishangzuoye.calendar.year;

import android.text.format.Time;

import com.example.qxx0101.haishangzuoye.calendar.constants.ConstData;
import com.example.qxx0101.haishangzuoye.calendar.view.VerticalPagerAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class YearPagerAdapter extends VerticalPagerAdapter {//VerticalPagerAdapter
    public YearViewFragment yearViewFragment;
    private Time mSelectedDay;

    public void setSelectedDay(Time mSelectedDay) {
        this.mSelectedDay = mSelectedDay;
    }

    public YearPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public YearPagerAdapter(FragmentManager fm, Time mSelectedDay) {
        super(fm);
        this.mSelectedDay = mSelectedDay;
    }

    @Override
    public Fragment getItem(int position) {
        yearViewFragment = (YearViewFragment) YearViewFragment.create(position + 1, mSelectedDay);
        return yearViewFragment;
    }

    @Override
    public int getCount() {
        return (ConstData.MAX_YEAR - ConstData.MIN_YEAR + 1);
    }

}

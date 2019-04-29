package com.example.qxx0101.haishangzuoye.calendar.view;


import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class VerticalPagerAdapter extends CustomFragmentStatePagerAdapter {//FragmentStatePagerAdapter
	private List<Fragment> fragments;
	

	public VerticalPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	public VerticalPagerAdapter(FragmentManager fm, List<Fragment> oneListFragments){
		super(fm);
		this.fragments=oneListFragments;
	}

	@Override
	public Fragment getItem(int arg0) {
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}
	
	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

}

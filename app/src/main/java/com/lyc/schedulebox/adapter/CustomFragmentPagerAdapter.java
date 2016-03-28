package com.lyc.schedulebox.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lyc.schedulebox.ui.fragment.ScheduleFragment;
import com.lyc.schedulebox.ui.fragment.MindFragment;
import com.lyc.schedulebox.ui.fragment.UserFragment;

/**
 * Created by lianyuchen on 16/2/23.
 */
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter{
    public CustomFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new MindFragment();
                break;
            case 1:
                fragment = new ScheduleFragment();
                break;
            case 2:
                fragment = new UserFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

package com.transsnet.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  zengfeng
 * Time  :  2020/9/23 14:16
 * Des   :
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments = new ArrayList<>();

    public HomePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> srcList) {
        fragments.clear();
        fragments.addAll(srcList);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

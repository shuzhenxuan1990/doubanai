package com.dushu1.doubanai.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public abstract class BaseHomeAdapter extends FragmentPagerAdapter {


  protected  final List<Fragment> mfragments = new ArrayList<>();
  protected   final  List<String> mtitles = new ArrayList<>();

    public BaseHomeAdapter(FragmentManager fm) {
        super(fm);
    }

    public abstract void  addFragment(Fragment fragment,String title);

    @Override
    public Fragment getItem(int position) {
        return mfragments.get(position);
    }

    @Override
    public int getCount() {
        return mfragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtitles.get(position);
    }
}

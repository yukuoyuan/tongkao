package com.kuoyuan.yu.common.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created on 2020/6/20
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public class BaseFragmentPageAdapter extends FragmentPagerAdapter {

    public BaseFragmentPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}

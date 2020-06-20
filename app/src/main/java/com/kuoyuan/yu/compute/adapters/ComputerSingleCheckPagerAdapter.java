package com.kuoyuan.yu.compute.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kuoyuan.yu.common.adapters.BaseFragmentStatePageAdapter;
import com.kuoyuan.yu.compute.fragments.ComputerSingleCheckPagerFragment;
import com.kuoyuan.yu.compute.beans.ComputerSingleCheckListBean;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public class ComputerSingleCheckPagerAdapter extends BaseFragmentStatePageAdapter<ComputerSingleCheckListBean.ComputerSingleDataBean> {

    public ComputerSingleCheckPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putParcelable("data", getItemData(position));
        return ComputerSingleCheckPagerFragment.newInstance(bundle);
    }
}

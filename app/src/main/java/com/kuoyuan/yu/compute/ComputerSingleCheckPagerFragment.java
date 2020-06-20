package com.kuoyuan.yu.compute;

import android.os.Bundle;

import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.fragments.BaseFragment;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public class ComputerSingleCheckPagerFragment extends BaseFragment {

    public static ComputerSingleCheckPagerFragment newInstance(Bundle args) {
        ComputerSingleCheckPagerFragment fragment = new ComputerSingleCheckPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData(Bundle extras) {
        if (extras != null) {
            int position = extras.getInt("position");
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_computer_single_check;
    }
}

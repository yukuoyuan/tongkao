package com.kuoyuan.yu.compute.fragments;

import android.os.Bundle;
import android.widget.TextView;

import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.fragments.BaseFragment;
import com.kuoyuan.yu.compute.beans.ComputerSingleCheckListBean;

import butterknife.BindView;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public class ComputerSingleCheckPagerFragment extends BaseFragment {
    @BindView(R.id.tv_computer_single_check_title)
    TextView tvComputerSingleCheckTitle;
    /**
     * 第几个
     */
    private int mPosition;
    /**
     * 数据
     */
    private ComputerSingleCheckListBean.ComputerSingleDataBean mComputerSingleDataBean;

    public static ComputerSingleCheckPagerFragment newInstance(Bundle args) {
        ComputerSingleCheckPagerFragment fragment = new ComputerSingleCheckPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData(Bundle extras) {
        if (extras != null) {
            mPosition = extras.getInt("position");
            mComputerSingleDataBean = extras.getParcelable("data");
        }
        tvComputerSingleCheckTitle.setText(mPosition + ". " + mComputerSingleDataBean.title);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_computer_single_check;
    }
}

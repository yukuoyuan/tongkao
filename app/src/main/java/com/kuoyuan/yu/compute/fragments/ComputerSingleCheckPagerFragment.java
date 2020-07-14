package com.kuoyuan.yu.compute.fragments;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.fragments.BaseFragment;
import com.kuoyuan.yu.compute.adapters.ComputerSingleCheckListAdapter;
import com.kuoyuan.yu.compute.beans.ComputerSingleCheckListBean;

import butterknife.BindView;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public class ComputerSingleCheckPagerFragment extends BaseFragment {
    @BindView(R.id.tv_computer_single_check_title)
    TextView tvComputerSingleCheckTitle;
    @BindView(R.id.rcv_computer_single_check_list)
    RecyclerView rcvComputerSingleCheckList;
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
        tvComputerSingleCheckTitle.setText(mPosition + ".\u3000" + mComputerSingleDataBean.title);
        initList();
    }

    /**
     * 初始化列表
     */
    private void initList() {
        rcvComputerSingleCheckList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ComputerSingleCheckListAdapter computerSingleCheckListAdapter = new ComputerSingleCheckListAdapter(getActivity());
        rcvComputerSingleCheckList.setAdapter(computerSingleCheckListAdapter);
        computerSingleCheckListAdapter.setData(mComputerSingleDataBean.checkData);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_computer_single_check;
    }
}

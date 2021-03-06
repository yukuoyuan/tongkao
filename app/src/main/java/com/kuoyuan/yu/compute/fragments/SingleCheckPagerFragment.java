package com.kuoyuan.yu.compute.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.adapters.BaseRecyclerViewAdapter;
import com.kuoyuan.yu.common.db.DbSingleBean;
import com.kuoyuan.yu.common.fragments.BaseFragment;
import com.kuoyuan.yu.common.utils.DbHelper;
import com.kuoyuan.yu.compute.adapters.SingleCheckListAdapter;
import com.kuoyuan.yu.compute.beans.SingleCheckListBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public class SingleCheckPagerFragment extends BaseFragment implements BaseRecyclerViewAdapter.OnItemClickListener<SingleCheckListBean.SingleDataBean.ComputerSingleCheckDataBean> {
    @BindView(R.id.tv_computer_single_check_title)
    TextView tvComputerSingleCheckTitle;
    @BindView(R.id.rcv_computer_single_check_list)
    RecyclerView rcvComputerSingleCheckList;
    @BindView(R.id.tv_computer_single_check_error_tip)
    TextView tvComputerSingleCheckErrorTip;
    /**
     * 第几个
     */
    private int mPosition;
    /**
     * 数据
     */
    private DbSingleBean mComputerSingleDataBean;

    public static SingleCheckPagerFragment newInstance(Bundle args) {
        SingleCheckPagerFragment fragment = new SingleCheckPagerFragment();
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
        SingleCheckListAdapter singleCheckListAdapter = new SingleCheckListAdapter(getActivity());
        rcvComputerSingleCheckList.setAdapter(singleCheckListAdapter);
        List<SingleCheckListBean.SingleDataBean.ComputerSingleCheckDataBean> computerSingleCheckDataBeanList = new Gson().fromJson(mComputerSingleDataBean.answers, new TypeToken<List<SingleCheckListBean.SingleDataBean.ComputerSingleCheckDataBean>>() {
        }.getType());
        singleCheckListAdapter.setData(computerSingleCheckDataBeanList);
        singleCheckListAdapter.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_computer_single_check;
    }

    @Override
    public void onItemClick(SingleCheckListBean.SingleDataBean.ComputerSingleCheckDataBean computerSingleCheckDataBean, int position, int type) {
        /*
         * 是否错误了
         */
        DbSingleBean dbSingleBean = DbHelper.getInstance().getDbSingleBeanById(mComputerSingleDataBean.id);
        if (type == 1) {
            /*
             * 选择正确
             */
            tvComputerSingleCheckErrorTip.setTextColor(Color.BLUE);
            tvComputerSingleCheckErrorTip.setText(TextUtils.isEmpty(mComputerSingleDataBean.tip) ? "" : "内容翻译:\n\n" + mComputerSingleDataBean.tip);
            dbSingleBean.isWrong = false;
        } else if (type == 2) {
            /*
             * 选择错误
             */
            tvComputerSingleCheckErrorTip.setTextColor(Color.RED);
            tvComputerSingleCheckErrorTip.setText(TextUtils.isEmpty(mComputerSingleDataBean.tip) ? "" : "错误提示:\n\n" + mComputerSingleDataBean.tip);
            dbSingleBean.isWrong = true;
        }
        dbSingleBean.todo = true;
        /*
         * 更新一下本地数据库
         */
        DbHelper.getInstance().updateSingleBean(dbSingleBean);
    }
}

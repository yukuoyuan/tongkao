package com.kuoyuan.yu.compute.views;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.adapters.BaseRecyclerViewAdapter;
import com.kuoyuan.yu.common.utils.AssetsUtils;
import com.kuoyuan.yu.common.utils.ListUtils;
import com.kuoyuan.yu.common.views.BaseBottomDialog;
import com.kuoyuan.yu.compute.adapters.SingCheckBottomListAdapter;
import com.kuoyuan.yu.compute.beans.ComputerSingleCheckListBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author yukuoyuan
 * @date 2020/6/21
 */
public class SingCheckBottomDialog extends BaseBottomDialog implements
        BaseRecyclerViewAdapter.OnItemClickListener<ComputerSingleCheckListBean.ComputerSingleDataBean> {

    @BindView(R.id.rcv_dialog_single_check_list)
    RecyclerView rcvDialogSingleCheckList;
    private OnSingleCheckBottomDialogListener onSingleCheckBottomDialogListener;

    public SingCheckBottomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initData() {
        rcvDialogSingleCheckList.setLayoutManager(new GridLayoutManager(context, 6));
        getListData();
    }

    /**
     * 获取列表数据
     */
    public void getListData() {
        String listData = AssetsUtils.getInstance().getJson("computersinglecheck.json", context);
        ComputerSingleCheckListBean computerSingleCheckBean = new Gson().fromJson(listData, ComputerSingleCheckListBean.class);
        if (computerSingleCheckBean != null && !ListUtils.isEmpty(computerSingleCheckBean.data)) {
            initData2View(computerSingleCheckBean.data);
        }
    }

    /**
     * 初始化数据到界面
     *
     * @param data 数据
     */
    private void initData2View(List<ComputerSingleCheckListBean.ComputerSingleDataBean> data) {
        SingCheckBottomListAdapter singCheckBottomListAdapter = new SingCheckBottomListAdapter(context);
        rcvDialogSingleCheckList.setAdapter(singCheckBottomListAdapter);
        singCheckBottomListAdapter.setData(data);
        singCheckBottomListAdapter.setOnItemClickListener(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.check_single_check_bottom_dialog;
    }

    @Override
    public void onItemClick(ComputerSingleCheckListBean.ComputerSingleDataBean computerSingleDataBean, int position, int type) {
        if (onSingleCheckBottomDialogListener != null) {
            onSingleCheckBottomDialogListener.onSingleCheckBottomDialogCheck(position);
        }
        dismiss();
    }

    /**
     * 回调监听方法
     */
    public interface OnSingleCheckBottomDialogListener {
        /**
         * 某个条目选中后的回调方法
         *
         * @param checkPosition 选中的索引
         */
        void onSingleCheckBottomDialogCheck(int checkPosition);
    }

    /**
     * 设置监听
     *
     * @param onSingleCheckBottomDialogListener 回调
     */
    public void setOnSingleCheckBottomDialogListener(OnSingleCheckBottomDialogListener onSingleCheckBottomDialogListener) {
        this.onSingleCheckBottomDialogListener = onSingleCheckBottomDialogListener;
    }
}

package com.kuoyuan.yu.compute.views;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.adapters.BaseRecyclerViewAdapter;
import com.kuoyuan.yu.common.utils.ListUtils;
import com.kuoyuan.yu.common.views.BaseBottomDialog;
import com.kuoyuan.yu.compute.adapters.SingCheckBottomListAdapter;
import com.kuoyuan.yu.compute.beans.SingleCheckListBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author yukuoyuan
 * @date 2020/6/21
 */
public class SingCheckBottomDialog extends BaseBottomDialog implements
        BaseRecyclerViewAdapter.OnItemClickListener<SingleCheckListBean.SingleDataBean> {

    @BindView(R.id.rcv_dialog_single_check_list)
    RecyclerView rcvDialogSingleCheckList;
    private OnSingleCheckBottomDialogListener onSingleCheckBottomDialogListener;

    public SingCheckBottomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initData() {
        rcvDialogSingleCheckList.setLayoutManager(new GridLayoutManager(context, 6));
    }

    /**
     * 初始化数据到界面
     *
     * @param data 数据
     */
    private void initData2View(List<SingleCheckListBean.SingleDataBean> data) {
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
    public void onItemClick(SingleCheckListBean.SingleDataBean computerSingleDataBean, int position, int type) {
        if (onSingleCheckBottomDialogListener != null) {
            onSingleCheckBottomDialogListener.onSingleCheckBottomDialogCheck(position);
        }
        dismiss();
    }

    /**
     * 设置数据源
     *
     * @param listData 数据源
     */
    public void setData(List<SingleCheckListBean.SingleDataBean> listData) {
        if (!ListUtils.isEmpty(listData)) {
            initData2View(listData);
        }
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

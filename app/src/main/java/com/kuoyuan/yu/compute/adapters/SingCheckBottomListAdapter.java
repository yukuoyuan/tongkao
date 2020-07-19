package com.kuoyuan.yu.compute.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.adapters.BaseRecyclerViewAdapter;
import com.kuoyuan.yu.common.adapters.BaseViewHolder;
import com.kuoyuan.yu.common.db.DbSingleBean;
import com.kuoyuan.yu.compute.beans.SingleCheckListBean;

import butterknife.BindView;

/**
 * @author yukuoyuan
 * @date 2020/6/21
 */
public class SingCheckBottomListAdapter extends BaseRecyclerViewAdapter<DbSingleBean, SingCheckBottomListAdapter.MyHolder> {


    public SingCheckBottomListAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public MyHolder onCreateDefaultViewHolder(View view, int viewType) {
        return new MyHolder(view);
    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_single_check_bottom_list;
    }

    class MyHolder extends BaseViewHolder {
        @BindView(R.id.tv_item_single_check_bottom_list_position)
        TextView tvItemSingleCheckBottomListPosition;

        public MyHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData2View(int position) {
            tvItemSingleCheckBottomListPosition.setText(String.valueOf(position));
        }
    }
}

package com.kuoyuan.yu.compute.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.adapters.BaseRecyclerViewAdapter;
import com.kuoyuan.yu.common.adapters.BaseViewHolder;
import com.kuoyuan.yu.compute.beans.SingleCheckListBean;

import butterknife.BindView;

/**
 * Created on 2020/6/20
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public class SingleCheckListAdapter extends BaseRecyclerViewAdapter<SingleCheckListBean.SingleDataBean.ComputerSingleCheckDataBean, SingleCheckListAdapter.MyHolder> {


    /**
     * 选中的索引
     */
    private int mCheckPosition = -1;

    public SingleCheckListAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean isSetBaseItemViewClickListener() {
        return false;
    }

    @Override
    public MyHolder onCreateDefaultViewHolder(View view, int viewType) {
        return new MyHolder(view);
    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_computer_single_check_list;
    }

    public class MyHolder extends BaseViewHolder {

        @BindView(R.id.cl_item_computer_single_check)
        ConstraintLayout clItemComputerSingleCheck;
        @BindView(R.id.tv_item_computer_single_check_is_right)
        TextView tvItemComputerSingleCheckIsRight;
        @BindView(R.id.iv_item_computer_single_check_is_right)
        ImageView ivItemComputerSingleCheckIsRight;
        @BindView(R.id.tv_item_computer_single_check)
        TextView tvItemComputerSingleCheck;

        public MyHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData2View(int position) {
            SingleCheckListBean.SingleDataBean.ComputerSingleCheckDataBean computerSingleCheckDataBean = getItem(position);
            String mPosition = "";
            if (position == 0) {
                mPosition = "A";
            } else if (position == 1) {
                mPosition = "B";
            } else if (position == 2) {
                mPosition = "C";
            } else if (position == 3) {
                mPosition = "D";
            }
            tvItemComputerSingleCheckIsRight.setText(mPosition);
            tvItemComputerSingleCheck.setText(computerSingleCheckDataBean.title);

            clItemComputerSingleCheck.setOnClickListener(v -> {
                if (mCheckPosition == -1) {
                    mCheckPosition = position;
                    /*
                     * 如果本身选择就是正确的话,那么就直接跳转下一个
                     */
                    if (computerSingleCheckDataBean.isRight) {
                        tvItemComputerSingleCheckIsRight.setVisibility(View.INVISIBLE);
                        ivItemComputerSingleCheckIsRight.setVisibility(View.VISIBLE);
                        ivItemComputerSingleCheckIsRight.setImageResource(R.drawable.icon_check_right);
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(computerSingleCheckDataBean, position, 1);
                        }
                    } else {
                        notifyDataSetChanged();
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(computerSingleCheckDataBean, position, 2);
                        }
                    }
                }
            });
            tvItemComputerSingleCheckIsRight.setVisibility(View.VISIBLE);
            ivItemComputerSingleCheckIsRight.setVisibility(View.GONE);
            /*
             * 正确的选项是绿色的
             */
            if (mCheckPosition != -1 && computerSingleCheckDataBean.isRight) {
                tvItemComputerSingleCheckIsRight.setVisibility(View.INVISIBLE);
                ivItemComputerSingleCheckIsRight.setVisibility(View.VISIBLE);
                ivItemComputerSingleCheckIsRight.setImageResource(R.drawable.icon_check_right);
            }
            /*
             * 选中的选项,如果,是false,那么就是红色的
             */
            if (mCheckPosition == position) {
                if (!computerSingleCheckDataBean.isRight) {
                    tvItemComputerSingleCheckIsRight.setVisibility(View.INVISIBLE);
                    ivItemComputerSingleCheckIsRight.setVisibility(View.VISIBLE);
                    ivItemComputerSingleCheckIsRight.setImageResource(R.drawable.icon_check_wrong);
                }
            }
        }
    }
}

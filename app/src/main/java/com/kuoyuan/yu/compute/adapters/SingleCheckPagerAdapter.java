package com.kuoyuan.yu.compute.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.kuoyuan.yu.common.adapters.BaseFragmentStatePageAdapter;
import com.kuoyuan.yu.common.db.DbSingleBean;
import com.kuoyuan.yu.common.utils.ListUtils;
import com.kuoyuan.yu.compute.fragments.SingleCheckPagerFragment;
import com.kuoyuan.yu.compute.beans.SingleCheckListBean;

import java.util.List;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public class SingleCheckPagerAdapter extends FragmentStateAdapter {
    /**
     * 列表数据
     */
    private List<DbSingleBean> mList;

    public SingleCheckPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putParcelable("data", getItemData(position));
        return SingleCheckPagerFragment.newInstance(bundle);
    }

    /**
     * 设置数据源
     *
     * @param listData 数据源
     */
    public void setData(List<DbSingleBean> listData) {
        mList = listData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (ListUtils.isEmpty(mList)) {
            return 0;
        }
        return mList.size();
    }

    /**
     * 获取条目数据
     *
     * @param position 条目索引
     * @return 数据
     */
    public DbSingleBean getItemData(int position) {
        return mList.get(position);
    }
}

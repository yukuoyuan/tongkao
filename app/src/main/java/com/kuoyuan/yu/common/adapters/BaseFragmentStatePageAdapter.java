package com.kuoyuan.yu.common.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kuoyuan.yu.common.utils.ListUtils;

import java.util.List;

/**
 * Created on 2020/6/20
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public abstract class BaseFragmentStatePageAdapter<V> extends FragmentStatePagerAdapter {
    /**
     * 列表数据
     */
    private List<V> mList;

    public BaseFragmentStatePageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        if (ListUtils.isEmpty(mList)) {
            return 0;
        }
        return mList.size();
    }

    /**
     * 设置数据源
     *
     * @param listData 数据源
     */
    public void setData(List<V> listData) {
        mList = listData;
        notifyDataSetChanged();
    }

    /**
     * 获取条目数据
     *
     * @param position 条目索引
     * @return 数据
     */
    public V getItemData(int position) {
        return mList.get(position);
    }
}

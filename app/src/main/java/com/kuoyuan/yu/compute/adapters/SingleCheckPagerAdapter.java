package com.kuoyuan.yu.compute.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kuoyuan.yu.common.adapters.BaseFragmentStatePageAdapter;
import com.kuoyuan.yu.common.db.DbSingleBean;
import com.kuoyuan.yu.compute.fragments.SingleCheckPagerFragment;
import com.kuoyuan.yu.compute.beans.SingleCheckListBean;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public class SingleCheckPagerAdapter extends BaseFragmentStatePageAdapter<DbSingleBean> {

    public SingleCheckPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putParcelable("data", getItemData(position));
        return SingleCheckPagerFragment.newInstance(bundle);
    }
}

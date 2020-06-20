package com.kuoyuan.yu.common.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kuoyuan.yu.common.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/6/20
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public abstract class BaseRecyclerViewAdapter<V, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    /**
     * 数据源
     */
    public List<V> data;
    /**
     * 条目点击事件的回调监听
     */
    public OnItemClickListener<V> onItemClickListener;
    /**
     * 上下文
     */
    public Context context;


    public BaseRecyclerViewAdapter(@NonNull Context context) {
        this.context = context;
    }
    /**
     * 是否设置跟布局的点击监听
     */
    public boolean isSetBaseItemViewClickListener() {
        return true;
    }

    /**
     * 设置数据源
     *
     * @param data 数据原
     */
    public void setData(List<V> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param datas 数据源
     */
    public void addData(List<V> datas) {
        if (ListUtils.isEmpty(data)) {
            data = new ArrayList<>();
        }
        if (!ListUtils.isEmpty(datas)) {
            this.data.addAll(datas);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加到指定位置
     *
     * @param da       单条数据
     * @param position 位置索引
     */
    public void addData(V da, int position) {
        if (da != null) {
            if (ListUtils.isEmpty(this.data)) {
                data = new ArrayList<>();
            }
            data.add(position, da);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加到指定位置
     *
     * @param da 单条数据
     */
    public void addData(V da) {
        if (da != null) {
            if (ListUtils.isEmpty(this.data)) {
                data = new ArrayList<>();
            }
            data.add(da);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除数据
     *
     * @param da 数据
     */
    public void removeData(V da) {
        if (da != null && !ListUtils.isEmpty(this.data)) {
            data.remove(da);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除数据
     *
     * @param position 索引
     */
    public void removeDataByPosition(int position) {
        if (!ListUtils.isEmpty(this.data)) {
            data.remove(position);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除数据
     */
    public void removeAllData() {
        if (!ListUtils.isEmpty(this.data)) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        if (ListUtils.isEmpty(data)) {
            return 0;
        }
        return data.size();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setData2View(position);
        /*
         * 设置条目的点击事件监听
         */
        if (holder.itemView != null && isSetBaseItemViewClickListener()) {
            holder.itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(getItem(position), position, 0);
                }
            });
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getItemLayout(viewType), parent, false);
        return onCreateDefaultViewHolder(view, viewType);
    }

    /**
     * 创建默认的holder
     *
     * @param view     视图
     * @param viewType
     * @return 返回holder
     */
    public abstract VH onCreateDefaultViewHolder(View view, int viewType);

    /**
     * 获取条目视图
     *
     * @param viewType
     * @return 视图
     */
    public abstract int getItemLayout(int viewType);


    public interface OnItemClickListener<V> {
        /**
         * 条目点击事件的回调
         *
         * @param v        条目数据
         * @param position 条目
         * @param type     事件类型
         */
        void onItemClick(V v, int position, int type);
    }

    /**
     * 获取条目数据
     *
     * @param position 索引
     * @return 条目数据
     */
    public V getItem(int position) {
        return data.get(position);
    }

    /**
     * 设置条目点击事件
     *
     * @param onItemClickListener 回调监听
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<V> getData() {
        return data;
    }
}

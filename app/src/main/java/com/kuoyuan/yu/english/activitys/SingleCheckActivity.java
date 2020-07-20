package com.kuoyuan.yu.english.activitys;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.activitys.BaseActivity;
import com.kuoyuan.yu.common.config.Constants;
import com.kuoyuan.yu.common.db.DbSingleBean;
import com.kuoyuan.yu.common.utils.DbHelper;
import com.kuoyuan.yu.compute.adapters.SingleCheckPagerAdapter;
import com.kuoyuan.yu.compute.views.SingCheckBottomDialog;
import com.kuoyuan.yu.english.presenters.SingleCheckPresenter;
import com.kuoyuan.yu.english.presenters.ISingleCheckView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnPageChange;

/**
 * Created on 2020/6/15
 * 这是一个英语单选的界面
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public class SingleCheckActivity extends BaseActivity<SingleCheckPresenter> implements ISingleCheckView, SingCheckBottomDialog.OnSingleCheckBottomDialogListener {


    @BindView(R.id.vp_english_single_check)
    ViewPager vpEnglishSingleCheck;
    @BindView(R.id.tv_english_single_check_more)
    TextView tvEnglishSingleCheckMore;
    /**
     * 总数量
     */
    private int mTotalCount;
    /**
     * 数据源
     */
    private List<DbSingleBean> listData;
    /**
     * 页面类型数据
     */
    private int mPageTypeValue;
    /**
     * 右侧菜单
     */
    private Menu menu;
    /**
     * 选择的索引
     */
    private int currentPosition;

    @Override
    protected void initData(Bundle extras) {
        if (extras != null) {
            mPageTypeValue = extras.getInt(Constants.PAGE_TYPE_KEY);
        }
        getSupportActionBar().setTitle("单选题");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getPresenter().getListData(mPageTypeValue);
    }

    @Override
    protected SingleCheckPresenter createPresenter() {
        return new SingleCheckPresenter(this, this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_english_single_check;
    }

    @OnClick({R.id.tv_english_single_check_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_english_single_check_more:
                showSingleCheckBottomDialog();
                break;
            default:
                break;
        }
    }

    @Override
    public void initData2View(List<DbSingleBean> listData) {
        mTotalCount = listData.size();
        this.listData = listData;
        showBottomCountTip(0);
        SingleCheckPagerAdapter singleCheckPagerAdapter = new SingleCheckPagerAdapter(getSupportFragmentManager());
        vpEnglishSingleCheck.setAdapter(singleCheckPagerAdapter);
        singleCheckPagerAdapter.setData(listData);
    }

    @Override
    public void showSingleCheckBottomDialog() {
        SingCheckBottomDialog checkSingCheckBottomDialog = new SingCheckBottomDialog(this);
        checkSingCheckBottomDialog.setData(listData);
        checkSingCheckBottomDialog.show();
        checkSingCheckBottomDialog.setOnSingleCheckBottomDialogListener(this);
    }

    @OnPageChange(R.id.vp_english_single_check)
    public void onPageSelected(int position) {
        this.currentPosition = position;
        showBottomCountTip(position);
        isCollection();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.ic_single_check_actionbar_right_collection) {
            /*
             * 收藏,或者取消收藏
             */
            DbSingleBean dbSingleBean = DbHelper.getInstance().getDbSingleBeanById(listData.get(currentPosition).id);
            dbSingleBean.isCollection = !dbSingleBean.isCollection;
            /*
             * 更新一下本地数据库
             */
            DbHelper.getInstance().updateSingleBean(dbSingleBean);
            isCollection();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showBottomCountTip(int position) {
        /*
         * 设置显示
         */
        tvEnglishSingleCheckMore.setText(String.format("%d/%d", position, mTotalCount));
    }

    @Override
    public void isCollection() {
        /*
         * 设置默认的按钮颜色
         */
        MenuItem item = menu.findItem(R.id.ic_single_check_actionbar_right_collection);
        if (listData.get(currentPosition).isCollection) {
            item.setIcon(R.drawable.icon_actionbar_collection_check);
        } else {
            item.setIcon(R.drawable.icon_actionbar_collection_uncheck);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;
        isCollection();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.single_check_menu, menu);
        return true;
    }

    @Override
    public void onSingleCheckBottomDialogCheck(int checkPosition) {
        vpEnglishSingleCheck.setCurrentItem(checkPosition);
    }
}

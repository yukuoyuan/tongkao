package com.kuoyuan.yu.common.utils;

import android.database.sqlite.SQLiteDatabase;

import com.kuoyuan.yu.TongKaoApplication;
import com.kuoyuan.yu.common.db.DaoMaster;
import com.kuoyuan.yu.common.db.DaoSession;
import com.kuoyuan.yu.common.db.DbSingleBean;
import com.kuoyuan.yu.common.db.DbSingleBeanDao;

import java.util.List;

/**
 * Created on 2020/7/19
 * 这是一个本地数据库的帮助类
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public class DbHelper {

    private static DbHelper mDbHelper = null;
    /**
     * 数据库名称
     */
    private static final String DB_NAME = "tong_kao_db";
    /**
     * 得到一个可以写入的数据库
     */
    private SQLiteDatabase mSQLiteDatabase;
    /**
     * 用来操作表
     */
    private DaoSession mDaoSession;

    private DbHelper() {
        /*
         * 初始化数据库
         */
        /*
         * 数据库帮助
         */
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(TongKaoApplication.getInstance(), DB_NAME);
        mSQLiteDatabase = helper.getWritableDatabase();
        /*
         * 表的管理
         */
        DaoMaster daoMaster = new DaoMaster(mSQLiteDatabase);
        mDaoSession = daoMaster.newSession();
    }

    public static DbHelper getInstance() {
        if (mDbHelper == null) {
            synchronized (DbHelper.class) {
                if (mDbHelper == null) {
                    mDbHelper = new DbHelper();
                }
            }
        }
        return mDbHelper;
    }

    /**
     * 增加或者更新旧的数据
     *
     * @param dbSingleBean 数据
     */
    public void addSingleBean(DbSingleBean dbSingleBean) {
        DbSingleBeanDao dbSingleBeanDao = mDaoSession.getDbSingleBeanDao();
        dbSingleBeanDao.insertOrReplace(dbSingleBean);
    }

    /**
     * 增加或者更新旧的数据
     *
     * @param dbSingleBean 数据
     */
    public void updateSingleBean(DbSingleBean dbSingleBean) {
        DbSingleBeanDao dbSingleBeanDao = mDaoSession.getDbSingleBeanDao();
        dbSingleBeanDao.update(dbSingleBean);
    }

    /**
     * 根据id获取响应的数据
     *
     * @param id 数据
     * @return 数据
     */
    public DbSingleBean getDbSingleBeanById(long id) {
        DbSingleBeanDao dbSingleBeanDao = mDaoSession.getDbSingleBeanDao();
        return dbSingleBeanDao.load(id);
    }

    /**
     * 获取列表
     *
     * @param type    类型
     * @param isWrong 是否错误
     * @return 数据
     */
    public List<DbSingleBean> getSingleBeanIsWrongList(long type, boolean isWrong) {
        DbSingleBeanDao dbSingleBeanDao = mDaoSession.getDbSingleBeanDao();
        return dbSingleBeanDao.queryBuilder().where(DbSingleBeanDao.Properties.Type.eq(type),
                DbSingleBeanDao.Properties.IsWrong.eq(isWrong)).list();
    }

    /**
     * 获取列表
     *
     * @param type         类型
     * @param isCollection 是否收藏
     * @return 数据
     */
    public List<DbSingleBean> getSingleBeanIsCollectionList(long type, boolean isCollection) {
        DbSingleBeanDao dbSingleBeanDao = mDaoSession.getDbSingleBeanDao();
        return dbSingleBeanDao.queryBuilder().where(DbSingleBeanDao.Properties.Type.eq(type),
                DbSingleBeanDao.Properties.IsCollection.eq(isCollection)).list();
    }

    /**
     * 获取列表
     *
     * @param type   类型
     * @param isTodo 是否做过
     * @return 数据
     */
    public List<DbSingleBean> getSingleBeanTodoList(long type, boolean isTodo) {
        DbSingleBeanDao dbSingleBeanDao = mDaoSession.getDbSingleBeanDao();
        return dbSingleBeanDao.queryBuilder().where(DbSingleBeanDao.Properties.Type.eq(type),
                DbSingleBeanDao.Properties.IsCollection.eq(isTodo)).list();
    }

    /**
     * 获取列表
     *
     * @param type 类型
     * @return 数据
     */
    public List<DbSingleBean> getSingleBeanList(long type) {
        DbSingleBeanDao dbSingleBeanDao = mDaoSession.getDbSingleBeanDao();
        return dbSingleBeanDao.queryBuilder().where(DbSingleBeanDao.Properties.Type.eq(type)).list();
    }
}

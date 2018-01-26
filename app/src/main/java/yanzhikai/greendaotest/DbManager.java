package yanzhikai.greendaotest;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/01/15
 * desc   :
 */

public class DbManager {
    private static final String DB_NAME = "CAO";
    private volatile static DbManager mManager = new DbManager();
    private static DaoMaster sDaoMaster;
    private static DaoMaster.DevOpenHelper sHelper;
    private static DaoSession sDaoSession;

    private Context mContext;


    public static DbManager getInstance(){
        return mManager;
    }

    public void init(Context context){
        mContext = context;
    }

    public DaoMaster getDaoMaster(){
        if(sDaoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
            sDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return sDaoMaster;
    }

    public DaoSession getDaoSession(){
        if(sDaoSession == null){
            if(sDaoMaster == null){
                sDaoMaster = getDaoMaster();
            }
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }


    public void closeConnection(){
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper(){
        if(sHelper != null){
            sHelper.close();
            sHelper = null;
        }
    }

    public void closeDaoSession(){
        if(sDaoSession != null){
            sDaoSession.clear();
            sDaoSession = null;
        }
    }

    public List<Customer> queryCustomerByQueryBuilder(String tag){
        QueryBuilder<Customer> queryBuilder = getDaoSession().queryBuilder(Customer.class);
        return queryBuilder.where(CustomerDao.Properties.Tag.eq(tag)).list();
    }

//    public List<Order> queryCustomerByQueryBuilder(String tag){
//        QueryBuilder<Customer> queryBuilder = getDaoSession().queryBuilder(Customer.class);
//        return queryBuilder.where(CustomerDao.Properties.Tag.eq(tag)).list();
//    }

}

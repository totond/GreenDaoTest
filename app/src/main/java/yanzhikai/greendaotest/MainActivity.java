package yanzhikai.greendaotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "yjk";
    private Button btn_insert,btn_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbManager.getInstance().init(this);
        btn_insert = findViewById(R.id.btn_insert);
        btn_query = findViewById(R.id.btn_query);
        btn_insert.setOnClickListener(this);
        btn_query.setOnClickListener(this);
    }

    private void insert(){
        Order newOrder = new Order();
        newOrder.setCustomerTag("customer1");
        newOrder.setDate(""+System.currentTimeMillis());
        newOrder.setId(1L);
        getCustomer1().getOrders().add(newOrder);
        getCustomer1().update();
        Log.d(TAG, "insert: ");
    }

    private Customer getCustomer1(){
        List<Customer> customers = DbManager.getInstance().queryCustomerByQueryBuilder("customer1");
        if (customers.size() <= 0){
            DbManager.getInstance().getDaoSession().insert(new Customer(1L,"customer1"));
        }
        return DbManager.getInstance().queryCustomerByQueryBuilder("customer1").get(0);
    }

    private void query(){
        List<Order> orders1 = DbManager.getInstance().queryCustomerByQueryBuilder("customer1").get(0).getOrders();
        if (orders1.size() > 0) {
            for (Order order : orders1) {
                Log.d(TAG, "query Tag: " + order.getCustomerTag());
                Log.d(TAG, "query Date: " + order.getDate());
                Log.d(TAG, "query Id: " + order.getId());
            }
        }else {
            Log.d(TAG, "query: no data");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DbManager.getInstance().closeConnection();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_insert:
                insert();
                break;
            case R.id.btn_query:
                query();
                getCustomer1().resetOrders();
                Log.d(TAG, "after reset: ");
                query();
                break;
        }
    }
}

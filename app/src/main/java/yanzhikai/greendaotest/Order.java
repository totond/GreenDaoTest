package yanzhikai.greendaotest;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/01/25
 * desc   :
 */


@Entity
public class Order {
    @Id(autoincrement = true)
    private Long id;
    private String date;
    @NotNull
    private String customerTag;

    @Generated(hash = 1430127083)
    public Order(Long id, String date, @NotNull String customerTag) {
        this.id = id;
        this.date = date;
        this.customerTag = customerTag;
    }

    @Generated(hash = 1105174599)
    public Order() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerTag() {
        return this.customerTag;
    }

    public void setCustomerTag(String customerTag) {
        this.customerTag = customerTag;
    }
}
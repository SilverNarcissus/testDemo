package hibernate.one_one_relating.PK_related;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by SilverNarcissus on 2017/3/22.
 */
@Entity
public class Wife {
    private int id;
    private String name;

    @OneToOne(mappedBy = "wife")
    //看到mappedBy则是在对方那边设置的
    //意思是告诉hibernate，映射在Husband里的wife属性里面设置过了，不用管这边的
    //只要有双向关联，mappedBy必设(最佳实践)
    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    private Husband husband;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

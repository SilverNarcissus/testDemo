package hibernate.one_one_relating.FK_related;

import javax.persistence.*;

/**
 * Created by SilverNarcissus on 2017/3/22.
 */
@Entity
public class Husband {
    private int id;
    private String name;
    private Wife wife;

    @OneToOne
    @JoinColumn(name = "wifeId")
    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

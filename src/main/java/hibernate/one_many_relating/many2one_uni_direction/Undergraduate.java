package hibernate.one_many_relating.many2one_uni_direction;

import javax.persistence.*;

/**
 * Created by SilverNarcissus on 2017/3/23.
 */
@Entity
@Table(name = "many2one_uni_direction_undergraduate")
public class Undergraduate {
    private int id;
    private String name;

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

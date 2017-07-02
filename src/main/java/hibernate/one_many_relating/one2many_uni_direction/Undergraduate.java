package hibernate.one_many_relating.one2many_uni_direction;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by SilverNarcissus on 2017/3/23.
 */
@Entity
@Table(name = "one2many_uni_direction_undergraduate")
public class Undergraduate {
    private int id;
    private String name;
    private Set<Dream> dreams;

    @OneToMany(cascade = CascadeType.ALL)
    //下面这个注释指的是在多的一方加外键，否则会当成many2many处理，生成中间表
    @JoinColumn(name = "undergraduate_id")
    public Set<Dream> getDreams() {
        return dreams;
    }

    public void setDreams(Set<Dream> dreams) {
        this.dreams = dreams;
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

package hibernate.one_many_relating.bi_direction;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by SilverNarcissus on 2017/3/23.
 */
@Entity
@Table(name = "bi_direction_undergraduate")
public class Undergraduate {
    private int id;
    private String name;
    private Set<Dream> dreams;

    //如果写成如下,则关系是dreams主导的，推荐
    @OneToMany(mappedBy = "undergraduate")
    //@OneToMany()
    //下面这个注释指的是在多的一方加外键，否则会当成many2many处理，生成中间表
    //这时候，关系是undergraduate主导的,实际上两边都可以，因为名字相同
    //@JoinColumn(name = "undergraduate_id")
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

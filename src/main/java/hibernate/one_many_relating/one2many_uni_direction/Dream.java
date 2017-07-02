package hibernate.one_many_relating.one2many_uni_direction;

import javax.persistence.*;

/**
 * Created by SilverNarcissus on 2017/3/23.
 */
@Entity
@Table(name = "one2many_uni_direction_dream")
public class Dream {
    private int id;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

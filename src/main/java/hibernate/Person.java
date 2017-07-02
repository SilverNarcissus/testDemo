package hibernate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by SilverNarcissus on 2017/3/16.
 */
@Entity
@IdClass(PersonPK.class)
public class Person {
    private String name;
    private int id;
    private String occupation;

    public Person(int id, String name, String occupation) {
        this.name = name;
        this.id = id;
        this.occupation = occupation;
    }

    public Person() {

    }


    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}

package hibernate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Created by SilverNarcissus on 2017/3/16.
 */
public class PersonPK implements Serializable {
    //！！！联合主键实现可序列化，重写equal和hashcode
    private String name;
    private int id;

    public PersonPK() {

    }

    public PersonPK(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PersonPK)) return false;

        PersonPK personPK = (PersonPK) o;

        return new EqualsBuilder()
                .append(id, personPK.id)
                .append(name, personPK.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(id)
                .toHashCode();
    }
}

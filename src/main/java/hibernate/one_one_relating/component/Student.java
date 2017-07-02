package hibernate.one_one_relating.component;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by SilverNarcissus on 2017/3/23.
 */
@Entity
@Table(name = "Component_Student")
public class Student {
    private int id;
    private String name;
    private StudentCard card;
    private LocalDate birthday;

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

    @Embedded
    public StudentCard getCard() {
        return card;
    }

    public void setCard(StudentCard card) {
        this.card = card;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}

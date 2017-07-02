package hibernate.one_one_relating.component;

import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 * Created by SilverNarcissus on 2017/3/23.
 */
@Embeddable
public class StudentCard {
    public StudentCard(LocalDate date) {
        this.date = date;
    }

    public StudentCard(){

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private LocalDate date;
}

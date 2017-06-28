package hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Student")
//基本不用，除非跨平台
@TableGenerator(
        name = "Student_GEN",
        table = "GENERATOR_TABLE",
        pkColumnName = "pk_key",
        valueColumnName = "pk_value",
        pkColumnValue = "Student",
        allocationSize = 1)
public class Student implements Serializable {


    private int id;

    private String name;

    private int age;

    private LocalDate birthday;

    private Grade grade;

    public Student(int id, String name, int age, LocalDate birthday, Grade grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.grade = grade;
    }

    public Student(String name, int age, LocalDate birthday, Grade grade) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.grade = grade;
    }

    public Student() {

    }

    @Enumerated(EnumType.STRING)
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }


    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Column(name = "_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    //默认native(auto),id 自动生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

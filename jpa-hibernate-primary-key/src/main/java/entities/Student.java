package entities;

import entities.compkey.CompositeStudentKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Student {

    @EmbeddedId
    private CompositeStudentKey id;

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public CompositeStudentKey getId() {
        return id;
    }

    public void setId(CompositeStudentKey id) {
        this.id = id;
    }
}

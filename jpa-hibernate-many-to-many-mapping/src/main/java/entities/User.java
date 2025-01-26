package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    @ManyToMany
    @JoinTable(
            name = "users_groups", // name of join table in db
            joinColumns = @JoinColumn(name = "user_id"), // column for current entity in the join table
            inverseJoinColumns = @JoinColumn(name = "group_id") // column for opposite side entity in the join table.
    )
    private List<UGroups> groups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UGroups> getGroups() {
        return groups;
    }

    public void setGroups(List<UGroups> groups) {
        this.groups = groups;
    }
}

package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class AltWayPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    private String title;

    /*
        * One Post can have many comments
        * Start with Post class. One Post many comments. so one to many annotation is used here.
        *
     */

    @OneToMany
    @JoinColumn(name = "post_id") // since foreign key will always be on comments side hence fk name is post_id
    private List<AltWayComment> comments;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AltWayComment> getComments() {
        return comments;
    }

    public void setComments(List<AltWayComment> comments) {
        this.comments = comments;
    }
}

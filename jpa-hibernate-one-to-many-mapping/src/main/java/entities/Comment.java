package entities;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    /*
        * One Post can have many comments
        * Start with the class that is Comment. Many Comments to one Post. Hence, we are using ManyToOne
     */

    @ManyToOne
    private Post post;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

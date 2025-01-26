import entities.AltWayComment;
import entities.AltWayPost;
import entities.BiDirectionalComment;
import entities.BiDirectionalPost;
import entities.Comment;
import entities.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistence.CustomPersistenceUnitInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        Map<String,String> properties = new HashMap<>();
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.hbm2ddl.auto","create");
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().
                createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(),properties);

        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            System.out.println("Beginning Transaction");
            entityManager.getTransaction().begin();

            Comment comment = new Comment();
            comment.setContent("Comment 1");
            Post post = new Post();
            post.setContent("post content");
            post.setTitle("post title");
            comment.setPost(post);

            entityManager.persist(comment);
            entityManager.persist(post);

            AltWayComment comment1 = new AltWayComment();
            comment1.setContent("alt comment");
            AltWayPost post1 = new AltWayPost();
            post1.setTitle("Alt Post Title");
            post1.setContent("Alt Post Content");
            post1.setComments(List.of(comment1));

            entityManager.persist(comment1);
            entityManager.persist(post1);

            BiDirectionalComment comment2 = new BiDirectionalComment();
            comment2.setContent("bi directional comment content");
            BiDirectionalComment comment3 = new BiDirectionalComment();
            comment3.setContent("bi directional comment content");
            BiDirectionalPost post2 = new BiDirectionalPost();
            post2.setContent("Bi Directional Post Content");
            post2.setTitle("Bi Directional Post Title");
            post2.setComments(List.of(comment2,comment3));
            comment2.setPost(post2);
            comment3.setPost(post2);

            entityManager.persist(comment2);
            entityManager.persist(comment3);
            entityManager.persist(post2);



            System.out.println("COMMITTING Transaction");

            entityManager.getTransaction().commit();
        }
    }
}

import entities.Comment;
import entities.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistence.CustomPersistenceUnitInfo;
import java.util.HashMap;
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

            System.out.println("COMMITTING Transaction");

            entityManager.getTransaction().commit();
        }
    }
}

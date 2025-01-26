import entities.UGroups;
import entities.User;
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
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().
                createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(),properties);

        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            System.out.println("Beginning Transaction");
            entityManager.getTransaction().begin();
            UGroups group1 = new UGroups();
            group1.setName("group1");
            UGroups group2 = new UGroups();
            group2.setName("group2");

            User user1 = new User();
            user1.setName("user1");
            user1.setGroups(List.of(group1,group2));
            User user2 = new User();
            user2.setName("user2");
            user2.setGroups(List.of(group2));
            entityManager.persist(group1);
            entityManager.persist(group2);
            entityManager.persist(user1);
            entityManager.persist(user2);


            System.out.println("COMMITTING Transaction");

            entityManager.getTransaction().commit();
        }
    }
}

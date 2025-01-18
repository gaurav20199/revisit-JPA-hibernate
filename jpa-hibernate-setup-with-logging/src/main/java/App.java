import entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistence.CustomPersistenceUnitInfo;

public class App {

    public static void main(String[] args) {
        // EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().
                createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(),null);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Beginning Transaction");
        entityManager.getTransaction().begin();
        Product product = new Product();
        product.setId(2);
        product.setName("product2");
        entityManager.persist(product);
        System.out.println("Committing Transaction");
        product.setName("product5");
        entityManager.getTransaction().commit();
    }
}

import entities.Product;
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
        //properties.put("hibernate.hbm2ddl.auto","create");
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().
                createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(),properties);

        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            System.out.println("Beginning Transaction");
            entityManager.getTransaction().begin();
            /*
                Product product = new Product();
                product.setId(1);
                product.setName("product1");
                entityManager.persist(product);
                // this call will not be made to db as product with id 1 is already there in context.
                entityManager.find(Product.class,1);
             */
            Product productWithId1 = entityManager.find(Product.class, 1);
            productWithId1.setName("productModifiedForRefresh");
            System.out.println("Before refresh::"+productWithId1);
            entityManager.refresh(productWithId1);
            System.out.println("After refresh::"+productWithId1);
            System.out.println("COMMITTING TRANSACTION");
            entityManager.getTransaction().commit();

        }
    }
}

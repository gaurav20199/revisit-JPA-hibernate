import entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistence.CustomPersistenceUnitInfo;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().
                createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(),null);

        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            System.out.println("Beginning Transaction");
            entityManager.getTransaction().begin();
            /*
                * This code will result in two queries. One select and one update. Find is used to fetch record
                * from db only when it is not present in the context. Second will be an update query. Since product,
                * product2,product3 is the same object in the context so updated value is modifiedProduct 3.
                * There is an update query made as at the time of committing the transaction all the changes will be
                * flushed first and name has been changed so that change will be flushed to db as well
                Product product = entityManager.find(Product.class, 1);
                Product product2 = entityManager.find(Product.class, 1);
                Product product3 = entityManager.find(Product.class, 1);
                product.setName("modifiedProduct");
                product2.setName("modifiedProduct2");
                product3.setName("modifiedProduct3");
             */

            /*
                * This will result in 3 queries one select, one delete and one insert and not 1(select query)
                * After find product will be in the context and hence managed by the context and since it
                * is managed by Hibernate, so first it will see that product has been marked for removal and a new
                * product with same details has been marked for insertion as well.
                *
                Product product = entityManager.find(Product.class, 1);
                entityManager.remove(product);

                Product newProduct = new Product();
                newProduct.setId(1);
                newProduct.setName("modifiedProduct3");
                entityManager.persist(newProduct);

             */

            /*
                * only 1 select query will be executed here. We changed the product name and set the name back to
                * actual name present in db. Since the object in db and present in context is exactly same and product
                * is not marked for removal and other things hence no other query will run.
                Product product = entityManager.find(Product.class, 1);
                product.setName("modified");
                product.setName("modifiedProduct3");


             */

            /*
                * First merge will be treated as insert since product with id 3 is not present in the db.
                * Second merge will now be treated as updated since product with id 3 is already seen by Hibernate
                * that it has to be inserted and hence second merge will be like an update
                Product newProduct = new Product();
                newProduct.setId(3);
                newProduct.setName("product3");
                entityManager.merge(newProduct);
                newProduct.setName("updatedproduct3");
                System.out.println("BEFORE SECOND MERGE");
                entityManager.merge(newProduct);
                System.out.println("COMMITTING TRANSACTION");
                entityManager.getTransaction().commit();

             */

        }
    }
}

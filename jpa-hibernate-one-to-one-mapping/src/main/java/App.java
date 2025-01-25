import entities.*;
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
            Person person = new Person();
            person.setName("John Doe");
            Address address = new Address();
            address.setCity("Fictional City");
            address.setPincode("Fictional Pincode");

            // we will have to manually set the relationship object and persist both the objects.
            person.setAddress(address);
            entityManager.persist(person);
            entityManager.persist(address);

            PersonBidirectional personBidirectional = new PersonBidirectional();
            personBidirectional.setName("John Does New");
            AddressBidirectional addressBidirectional = new AddressBidirectional();
            addressBidirectional.setCity("Fictional City New");
            addressBidirectional.setPincode("Fictional Pincode New");
            personBidirectional.setAddress(addressBidirectional);
            addressBidirectional.setPerson(personBidirectional);
            entityManager.persist(personBidirectional);
            entityManager.persist(addressBidirectional);

            User user = new User();
            user.setName("John Doe");
            user.setDescription("user description");
            entityManager.persist(user);

            System.out.println("COMMITTING Transaction");

            entityManager.getTransaction().commit();
        }
    }
}

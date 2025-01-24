import entities.Employee;
import entities.Product;
import entities.Student;
import entities.compkey.CompositeStudentKey;
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
            System.out.println("COMMITTING TRANSACTION");
            Product product = new Product();
            product.setName("product");
            entityManager.persist(product);

            Employee employee = new Employee();
            employee.setEmpCode("0007");
            employee.setDepartment("IT");
            employee.setAge(25);
            employee.setName("John Doe");
            entityManager.persist(employee);

            CompositeStudentKey compositeStudentKey = new CompositeStudentKey();
            compositeStudentKey.setDepartment("CSE");
            compositeStudentKey.setRollNo("123");

            Student student = new Student();
            student.setAge(25);
            student.setId(compositeStudentKey);
            student.setName("Johny Bravo");

            entityManager.persist(student);

            entityManager.getTransaction().commit();
        }
    }
}

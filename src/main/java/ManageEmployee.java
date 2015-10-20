import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Iterator;
import java.util.List;

public class ManageEmployee {
    private static SessionFactory factory;
    public static void main(String[] args) {
        try{
//            factory = new AnnotationConfiguration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Employee.class)
//                .buildSessionFactory();

            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");


//            factory = new Configuration().configure().buildSessionFactory();

//            Configuration configuration = new Configuration();
//            configuration.configure("hibernate.cfg.xml");
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties()).build();
//            factory = configuration.buildSessionFactory(serviceRegistry);

        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        ManageEmployee ME = new ManageEmployee();

      /* Add few employee records in database */
        Integer empID1 = ME.addEmployee("Zara", "Ali", 5);
        Integer empID2 = ME.addEmployee("Daisy", "Das", 55);
        Integer empID3 = ME.addEmployee("John", "Paul", 555);

      /* List down all the employees */
        ME.listEmployees();

      /* Update employee's records */
//        ME.updateEmployee(empID1, 5000);

      /* Delete an employee from the database */
//        ME.deleteEmployee(empID1);
//        ME.deleteEmployee(empID2);
//        ME.deleteEmployee(empID3);

      /* List down new list of the employees */
//        ME.listEmployees();
    }
    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fname, String lname, int salary){
        Session session = factory.openSession();
//        Criteria cr = session.createCriteria(Employee.class);
//        Restrictions.gt()
        Transaction tx = null;
        Integer employeeID = null;
        try{
                tx = session.beginTransaction();
                Employee employee = new Employee();
                employee.setFirstName(fname);
                employee.setLastName(lname);
                employee.setSalary(salary);
                employeeID = (Integer) session.save(employee);
                tx.commit();
            }catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            }finally {
                session.close();
        }
        return employeeID;
    }
    /* Method to  READ all the employees */
    public void listEmployees( ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();

            Criteria cr = session.createCriteria(Employee.class);
            // Add restriction.
            cr.add(Restrictions.gt("salary", 200000000));
            List employees = cr.list();

            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                Employee employee = (Employee) iterator.next();
                System.out.print("First Name: " + employee.getFirstName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());

//                List employees = session.createQuery("FROM Employee").list();
//            for (Iterator iterator =
//                 employees.iterator(); iterator.hasNext();){
//                Employee employee = (Employee) iterator.next();
//                System.out.print("First Name: " + employee.getFirstName());
//                System.out.print("  Last Name: " + employee.getLastName());
//                System.out.println("  Salary: " + employee.getSalary());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to UPDATE salary for an employee */
    public void updateEmployee(Integer EmployeeID, int salary ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Employee employee =
                (Employee)session.get(Employee.class, EmployeeID);
            employee.setSalary( salary );
            session.update(employee);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteEmployee(Integer EmployeeID){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Employee employee =
                (Employee)session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
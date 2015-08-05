import dao.PersonDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Andrii_Tkachuk
 * @since 8/5/2015
 */
public class Demo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        PersonDao personDao = (PersonDao) applicationContext.getBean("personDaoImpl");
        System.out.println(personDao.getAllPersonNames());
    }
}

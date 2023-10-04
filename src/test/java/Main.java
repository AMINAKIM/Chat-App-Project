
import DAO.MessageDAO;
import DAO.UserDAO;
import metier.User;
import metier.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        User user = new User("sanae","cc");
        Message message = new Message(user, "salut");
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(Message.class);
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        UserDAO userDAO = new UserDAO(sessionFactory);
        MessageDAO messageDAO = new MessageDAO(sessionFactory);
        //userDAO.addUser(user);
       // messageDAO.addMessage(message);

    }
}

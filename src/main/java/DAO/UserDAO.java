package DAO;
import metier.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAO {
    private SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }


    public void deleteUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(user);
        session.getTransaction().commit();
    }


    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
    }


    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("FROM User", User.class).list();
        session.getTransaction().commit();
        return users;
    }


    public User getUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        return user;
    }

    public User getUserByUsername(String username) {
        System.out.println(username);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.createQuery("FROM User WHERE username = :username", User.class)
                .setParameter("username", username)
                .uniqueResult();
        System.out.println("DAO: "+user.toString());
        session.getTransaction().commit();
        return user;

    }




}

package DAO;

import metier.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MessageDAO {

    private SessionFactory sessionFactory;

    public MessageDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addMessage(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
    }


    public void deleteUser(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(message);
        session.getTransaction().commit();
    }


    public void updateUser(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(message);
        session.getTransaction().commit();
    }


    public List<Message> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Message> messages = session.createQuery("FROM User", Message.class).list();
        session.getTransaction().commit();
        return messages;
    }


    public Message getUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Message message = session.get(Message.class, id);
        session.getTransaction().commit();
        return message;
    }





}


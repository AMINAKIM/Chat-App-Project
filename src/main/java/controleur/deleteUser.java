package controleur;
import DAO.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.Message;
import metier.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

@WebServlet(name = "/deleteUser", value = "/deleteUser")
public class deleteUser extends HttpServlet {
    private UserDAO userDao;
    Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(Message.class);
    SessionFactory sessionFactory = cfg.buildSessionFactory();

    public void init() {
        userDao = new UserDAO( sessionFactory);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        String username = request.getParameter("username");
        User user = userDao.getUserByUsername(username);

        if (user != null) {
            userDao.deleteUser(user);
            request.setAttribute("message", " DELETED WITH SUCCES ! ");
        } else {
            request.setAttribute("message", " DELET FAILED");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.include(request, response);
   }

}

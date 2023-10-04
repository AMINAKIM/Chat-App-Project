package controleur;

import DAO.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Message;
import metier.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet(name = "/ChangerUser", value = "/ChangerUser")
public class ChangerUser extends HttpServlet {
    private UserDAO userDao;
    Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(Message.class);
    SessionFactory sessionFactory = cfg.buildSessionFactory();

    public void init() {
        userDao = new UserDAO( sessionFactory);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int permission = parseInt(request.getParameter("permission")) ;

        User user = userDao.getUserByUsername(name);
        user.setPermission(permission);
        if (user != null) {
            userDao.updateUser(user);
            request.setAttribute("ChangeMessage", "UPDATED WITH SUCCES!");
        } else {
            request.setAttribute("ChangeMessage", "UPDATE FAILD");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.include(request, response);
    }
}

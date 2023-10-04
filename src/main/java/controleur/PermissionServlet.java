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
import java.io.PrintWriter;

@WebServlet(name = "/PermissionServlet", value = "/PermissionServlet")
public class PermissionServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données fournies par l'utilisateur

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        //stocker dans la bade de données
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(Message.class);
        SessionFactory sessionFactory = cfg.buildSessionFactory();


        // Vérifier les données dans la base de données
        UserDAO userDAO = new UserDAO(sessionFactory); // Instancier votre classe UserDAO
        User user = userDAO.getUserByUsername(username); // Récupérer l'utilisateur correspondant au nom d'utilisateur
        System.out.println(user);


        //creation du session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
                // Créer un message associé à l'utilisateur
                  Message message = new Message();
                  message.setContent("Welcome to the chat room!");
                  message.setUser(user);


        // renvoyer le user à la jsp
        request.setAttribute("user", user);

        if (user != null && user.getPassword().equals(password)) {
            // L'utilisateur existe et le mot de passe est correct
            // Vérifier les permissions de l'utilisateur
            if (user.getPermission() == 1 || user.getPermission() == 2 || user.getPermission() == 3) {
                // Rediriger l'administrateur vers la page correspondante
                //response.sendRedirect("chat.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Identifiants invalides, rediriger vers une page d'erreur ou de login
            response.sendRedirect(" Error.jsp, No Permission declared ! ");
        }

    }
}
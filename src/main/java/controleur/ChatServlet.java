package controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.Message;
import metier.User;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "/ChatServlet", value = "/ChatServlet")
public class ChatServlet extends HttpServlet {
    static ArrayList<Message> chatMessages;

    public void init() {
        // Initialisez la session Hibernate (SessionFactory) au démarrage de la servlet
        chatMessages = new ArrayList<Message>();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {

            String messageContent = request.getParameter("message");
            // Créer un nouvel objet Message avec l'expéditeur et le contenu du message
            Message message = new Message();
            message.setUser(user);
            message.setContent(messageContent);
            System.out.println(message.getContent()+":"+message.getUser().getUsername());
            // Ajouter le nouveau message à la liste des messages du chat

            chatMessages.add(message);
            System.out.println("List");
            for (Message m1 : chatMessages){
                System.out.println(m1.getUser().getUsername());
                System.out.println( m1.getContent());
            }
            session.setAttribute("chatMessages", chatMessages);

            // Persistez le message en utilisant Hibernate

        }

        // Rediriger vers la page du chat
        response.sendRedirect("chat.jsp");
    }

}

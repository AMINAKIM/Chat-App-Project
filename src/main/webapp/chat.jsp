<%@ page import="metier.User" %>
<%@ page import="metier.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31-05-23
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> CHATBORD </title>
    <meta http-equiv="refresh" content="6; URL=chat.jsp">
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    int permission = user.getPermission();
%>
<div class="header">
    <h1> CHAT_BOARD </h1>
</div>

<div class="chat">
    <ul>
        <%
            HttpSession sessionObj = request.getSession();
            List<Message> chatMessages = (List<Message>) sessionObj.getAttribute("chatMessages");
            if (chatMessages != null) {
                for (Message message : chatMessages) {
        %>
        <li><%= message.getUser().getUsername() %>: <%= message.getContent() %>
        </li>
        <%
                }
            }
        %>
    </ul>

    <b><h4> Send Message:</h4></b>
    <form id="chatForm" action="ChatServlet" method="post">
        <input type="text" name="message" id="message" placeholder="Saisissez votre message" required>
        <input type="submit" value="Envoyer">
    </form>
</div>

<% if (permission == 1) { %>

<div class="admin-functions">
    <h4> Banne User :</h4>
    <form method="post" action="deleteUser">
        <label for="username">Nom d'utilisateur :</label>
        <input type="text" id="username" name="username" required>
        <button type="submit">Supprimer</button>
    </form>
</div>
<% }
    if (permission == 2 || permission == 1) { %>

<div class="admin-functions">
    <h4> Changer Permission Type:</h4>
    <form method="post" action="ChangerUser">
        <label for="username">Nom d'utilisateur :</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="permission">Permission :</label>
        <input type="number" id="permission" name="permission" required>
        <button type="submit">Modifier</button>
    </form>
</div>
<% } %>

</body>
<style>
    /* Styles pour le corps de la page */
    body {
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
    }

    .container {
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
    }

    .header {
        background-color: #799c16;
        color: #fff;
        padding: 50px;
        text-align: center;
    }

    .header h1 {
        margin: 0;
    }

    .chat {
        margin-top: 20px;
    }

    .chat h4 {
        color: #333;
        font-size: 18px;
        font-weight: bold;
    }

    .chat ul {
        list-style: none;
        padding: 0;
    }

    .chat li {
        margin-bottom: 10px;
        padding: 10px;
        background-color: #fff;
        border-radius: 5px;
    }

    .chat li span.username {
        font-weight: bold;
    }

    .chat li span.content {
        margin-left: 10px;
    }

    #chatForm {
        margin-top: 20px;
    }

    #chatForm input[type="text"] {
        width: 300px;
        padding: 10px;
    }

    #chatForm input[type="submit"] {
        padding: 10px 20px;
        background-color: #799c16;
        color: #fff;
        border: none;
        cursor: pointer;
    }

    .admin-functions {
        margin-top: 20px;
        background-color: #f2f2f2;
        padding: 20px;
        border-radius: 5px;
    }

    .admin-functions h3 {
        color: #333;
        font-size: 18px;
        font-weight: bold;
    }

    .admin-functions h4 {
        color: #333;
        font-size: 16px;
        margin-top: 10px;
        font-weight: bold;
    }

    .admin-functions form {
        margin-top: 10px;
    }

    .admin-functions label {
        display: block;
        margin-bottom: 5px;
    }

    .admin-functions input[type="text"],
    .admin-functions input[type="number"] {
        width: 200px;
        padding: 5px;
    }

    .admin-functions button[type="submit"] {
        padding: 5px 10px;
        background-color: #799c16;
        color: #fff;
        border: none;
        cursor: pointer;
    }
</style>
</html>

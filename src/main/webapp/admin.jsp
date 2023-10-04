<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31-05-23
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<% if (request.getAttribute("message") != null)
{ %>
<div class="message">
  <%= request.getAttribute("message") %>
</div>
<% } %>


<% if (request.getAttribute("ChangeMessage") != null)
{ %>
<div class="ChangeMessage">
    <%= request.getAttribute("ChangeMessage") %>
</div>
<% } %>
</body>
<style>
  .message {
    background-color: #f1f1f1;
    color: #333;
    padding: 10px;
    margin-bottom: 10px;
  }
</style>
</html>

<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: viky7
  Date: 10.05.2023
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Results</title>
</head>
  <body>
  <form method="GET" action="MyServlet">
    <input type="text" name="key" />
    <input type="text" name="value" />
    <input type="checkbox" name="test" value="true" />
    <input type="submit" value="Invoke the servlet" />
  </form>
  <ul>
    <% for (String line : (List<String>) request.getAttribute("lines")) { %>
    <li><%= line %></li>
    <% } %>
  </ul>
</body>
</html>

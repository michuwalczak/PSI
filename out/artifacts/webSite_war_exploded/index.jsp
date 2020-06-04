<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 23.03.2020
  Time: 20:49
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:forward page="/Menu.jsp" />
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="Servlet1" METHOD="POST">
    Name:<input type="text" name="userName"/><br/>
    <input type="submit" value="go"/>
  </form>
  </body>
</html>

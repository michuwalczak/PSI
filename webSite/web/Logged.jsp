<%@ page import="Classes.User" %><%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 27.03.2020
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>



             --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user"
             class="Classes.User"
             scope="session" />
<jsp:useBean id="comment"
             class="Classes.Comment"
             scope="session" />
<html>
<head>
    <title>Logged</title>
</head>
<body>
<H1>Witaj <jsp:getProperty name="user" property="login" />
</H1>
<H2> <jsp:getProperty name="comment" property="comment" />
</H2>
<FORM ACTION="/webSite_war_exploded/Direction" METHOD="POST">
    <CENTER>
        <INPUT TYPE="SUBMIT" NAME="ChangePassword" VALUE="Zmień hasło">
        <INPUT TYPE="SUBMIT" NAME="LogOff" VALUE="Wyloguj się">
    </CENTER>
</FORM>
</body>
</html>

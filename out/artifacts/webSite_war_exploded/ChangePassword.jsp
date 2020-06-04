<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 27.03.2020
  Time: 16:03
  To change this template use File | Settings | File Templates.
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
    <title>Change Password</title>
</head>
<body>
<H1>Zmiana hasła</H1>
<H2> <jsp:getProperty name="comment" property="comment" />
</H2>
<FORM ACTION="/webSite_war_exploded/Direction" METHOD="POST">
    <CENTER>
        Stare hasło:  <INPUT TYPE="PASSWORD" NAME="oldPassword"><BR>
        Nowe hasło: <INPUT TYPE="PASSWORD" NAME="newPassword" SIZE=10><BR>
        <INPUT TYPE="SUBMIT" NAME="CommitPassword" VALUE="Zatwierdź">
        <INPUT TYPE="SUBMIT" NAME="LogOff" VALUE="Wyloguj się">
    </CENTER>
</FORM>
</body>
</html>

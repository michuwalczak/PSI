<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 27.03.2020
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="comment"
             class="Classes.Comment"
             scope="session" />
<html>
<head>
    <title>Register</title>
</head>
<body>
<H1>Rejestracja</H1>
<H2> <jsp:getProperty name="comment" property="comment" />
</H2>
<FORM ACTION="/webSite_war_exploded/Direction" METHOD="POST">
    <CENTER>
        Login: <INPUT TYPE="TEXT" NAME="login"><BR>
        Hasło: <INPUT TYPE="PASSWORD" NAME="password" SIZE=10><BR>
        <INPUT TYPE="SUBMIT" NAME="CommitRegister" VALUE="Zarejestruj się">
        <INPUT TYPE="SUBMIT" NAME="Menu" VALUE="Mam już konto">
    </CENTER>
</FORM>
</body>
</html>
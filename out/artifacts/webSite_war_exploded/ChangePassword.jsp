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
    <style>
        input[type=password] {
            padding:5px;
            border:2px solid gray;
            -webkit-border-radius: 5px;
            border-radius: 5px;
        }
        input[type=password]:focus {
            border-color:black;
        }
        input[type=submit] {
            padding:5px 15px;
            background:gray;
            border:0 none;
            cursor:pointer;
            border-radius: 5px;
        }
        input[name=CommitPassword]:hover {
            background-color: green;
            color: white;
        }
        input[name=LogOff]:hover {
            background-color: red;
            color: white;
        }
    </style>
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

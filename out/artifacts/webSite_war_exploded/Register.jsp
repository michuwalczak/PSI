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
    <style>
        input[type=text] {
            padding:5px;
            border:2px solid gray;
            -webkit-border-radius: 5px;
            border-radius: 5px;
        }
        input[type=text]:focus {
            border-color:black;
        }
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
        input[name=ChangePassword]:hover {
            background-color: black;
            color: white;
        }
        input[name=CommitRegister]:hover {
            background-color: green;
            color: white;
        }
    </style>
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
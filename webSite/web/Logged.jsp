<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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
    <style>
        #historyTable th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: darkgreen;
            color: white;
        }
        #historyTable tr:nth-child(even){background-color: lightgray;}
        #historyTable tr:hover {background-color: darkgray;}

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
        input[name=LogOff]:hover {
            background-color: red;
            color: white;
        }
        input[type=button] {
            padding:5px 15px;
            background:#ccc;
            border:0 none;
            cursor:pointer;
            border-radius: 5px;
        }
        input[type=button]:hover {
            background-color: black;
            color: white;
        }
    </style>
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
        <INPUT TYPE="BUTTON" ID="ShowHistory" VALUE="Ukryj historię">
    </CENTER>
</FORM>

<script>
    document.getElementById("ShowHistory").addEventListener("click", function(button) {
        if (document.getElementById("historyTable").style.display === "none") {
            document.getElementById("ShowHistory").value = "Ukryj historię";
            document.getElementById("historyTable").style.display = "table";
        }
        else {
            document.getElementById("ShowHistory").value = "Wyświetl historię";
            document.getElementById("historyTable").style.display = "none";
        }
    });

</script>

<table id="historyTable" style="display: table; margin-left: auto; margin-right: auto" cellpadding="10" >
    <tr>
        <th>Czas</th>
        <th>Zdarzenie</th>
    </tr>
    <c:forEach items="${historyUser}" var="item">
        <tr>
            <td><c:out value="${item.key}"/></td>
            <td><c:out value="${item.value}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

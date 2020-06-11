package Servlets;

import Classes.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Timer;
import java.util.TreeMap;


@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        //create new user
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);

        //validate data
        Security.ValidationResult result = Security.validateLogin(user);
        if(result == Security.ValidationResult.PasswordUpToDate){
            Dao.updateErrorCounter(user, 0);
            Dao.addAction(user, "Użytkownik zalogowany");
            Map<Timestamp, String> historyUser = new TreeMap<Timestamp, String>(Dao.getActions(user)).descendingMap();
            request.setAttribute("historyUser",historyUser);
            PageHandler.goTo("/Logged.jsp", request, response, new Comment());
        }
        else if(result == Security.ValidationResult.PasswordExpired){
            Dao.updateErrorCounter(user, 0);
            Dao.addAction(user, "Użytkownik zalogowany");
            PageHandler.goTo("/ChangePassword.jsp", request, response, new Comment(result), user);
        }
        else{
            PageHandler.goTo("/Menu.jsp", request, response, new Comment(result));
        }
    }
}



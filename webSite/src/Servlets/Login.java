package Servlets;

import Classes.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
            Dao.addAction(user, "Logged in");
            Dao.updateErrorCounter(user, 0);
            Dao.getActions(user); //TODO Dodać przesyłanie mapy na stronę Logged.jsp

            PageHandler.goTo("/Logged.jsp", request, response, new Comment());
        }
        else if(result == Security.ValidationResult.PasswordExpired){
            Dao.addAction(user, "Logged in");
            Dao.updateErrorCounter(user, 0);
            Dao.getActions(user); //TODO Dodać przesyłanie mapy na stronę Logged.jsp

            PageHandler.goTo("/ChangePassword.jsp", request, response, new Comment(result), user);
        }
        else{
            PageHandler.goTo("/Menu.jsp", request, response, new Comment(result));
        }
    }
}



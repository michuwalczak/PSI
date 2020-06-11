package Servlets;

import Classes.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        //create new user
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);

        //validate data
        Security.ValidationResult result = Security.validateRegistration(user);
        if(result == Security.ValidationResult.RegistrationValidated) {
            Dao.addUser(user);
            PageHandler.goTo("/Menu.jsp", request, response, new Comment(result));
        }
        else{
            PageHandler.goTo("/Register.jsp", request, response, new Comment(result));
        }
    }
}

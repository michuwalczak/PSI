package Servlets;

import Classes.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        Security.ValidationResult result = Security.validatePasswordChange(user, oldPassword, newPassword);

        if(result == Security.ValidationResult.PasswordChangeValidated){
            user.setPassword(newPassword);
            Dao.updatePassword(user);
            PageHandler.goTo("/Logged.jsp", request, response, new Comment(result));
        }
        else if(result == Security.ValidationResult.PasswordUpToDate){
            PageHandler.goTo("/Logged.jsp", request, response, new Comment(result));
        }
        else{
            PageHandler.goTo("/ChangePassword.jsp", request, response, new Comment(result));
        }
    }
}

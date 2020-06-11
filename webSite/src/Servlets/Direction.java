package Servlets;

import Classes.Comment;
import Classes.Dao;
import Classes.DataBase;
import Classes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Direction")
public class Direction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        //configure database connection
        configureDBConnection(request);

        //redirect
        if (request.getParameter("Login") != null) {
            User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            gotoPage("/Login", request, response);
        }
        else if (request.getParameter("Register") != null) {
            Comment comment = new Comment();
            comment.setComment("");
            HttpSession session = request.getSession();
            session.setAttribute("comment", comment);
            gotoPage("/Register.jsp", request, response);
        }
        else if (request.getParameter("ChangePassword") != null) {
            Comment comment = new Comment();
            comment.setComment("");
            HttpSession session = request.getSession();
            session.setAttribute("comment", comment);

            gotoPage("/ChangePassword.jsp", request, response);
        }
        else if (request.getParameter("Logged") != null) {

            gotoPage("/Logged.jsp", request, response);
        }
        else if (request.getParameter("LogOff") != null) {
            Comment comment = new Comment();
            comment.setComment("");

            HttpSession session = request.getSession();
            session.setAttribute("comment", comment);

            User user = (User)session.getAttribute("user");
            Dao.addAction(user, "Użytkownik wylogowany");

            gotoPage("/Menu.jsp", request, response);
        }
        else if (request.getParameter("CommitPassword") != null) {
            gotoPage("/ChangePassword", request, response);
        }
        else if (request.getParameter("CommitRegister") != null) {
            gotoPage("/Register", request, response);
        }
        else if (request.getParameter("Menu") != null) {
            Comment comment = new Comment();
            comment.setComment("");
            HttpSession session = request.getSession();
            session.setAttribute("comment", comment);
            gotoPage("/Menu.jsp", request, response);
        }
    }

    private void gotoPage(String address,
                          HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    private void configureDBConnection(HttpServletRequest request){
        if(!DataBase.configured) {
            ServletContext context = request.getServletContext();
            String appPath = context.getRealPath("/").replace("out\\artifacts\\webSite_war_exploded\\", "");
            DataBase.configure("sa", "sa", appPath);
        }
    }
}
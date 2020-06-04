package Classes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public final class PageHandler {
    public static void goTo(String address,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            Comment comment)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("comment", comment);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public static void goTo(String address,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            Comment comment,
                            User user)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("comment", comment);
        session.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}

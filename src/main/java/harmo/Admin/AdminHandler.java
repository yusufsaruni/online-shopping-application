package harmo.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AdminHandler extends HttpServlet {
    //will send the user to home page if session is confirmed

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && Boolean.TRUE
                .equals(session.getAttribute("isLoggedIn"))) {
            String email = (String) session.getAttribute("email");
            request.setAttribute("email", email);
            RequestDispatcher rd = request
                    .getRequestDispatcher("adminHome.jsp");
            rd.forward(request, response);
        }else {
            //log in message here.
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}

package harmo.Admin;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class MessageReceivedHandler extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isLoggedIn")))
        {
            var messages = Dao.getMessages();
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("messagesReceived.jsp")
                    .forward(request, response);
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}

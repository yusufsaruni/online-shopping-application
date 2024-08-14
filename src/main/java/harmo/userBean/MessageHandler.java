package harmo.userBean;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class MessageHandler extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            int user_id = Dao.getUserId(
                    (String)session.getAttribute("email"));
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");
            if(Dao.addMessage(user_id, subject, message)){
                response.sendRedirect("messageUs.jsp?" +
                        "message=success");
            }else{
                response.sendRedirect("messageUs.jsp?" +
                        "error=genericError");
            }
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}

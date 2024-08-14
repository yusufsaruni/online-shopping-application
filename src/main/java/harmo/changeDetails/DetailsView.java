package harmo.changeDetails;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DetailsView extends HttpServlet {
    //sends full details to changeDetails.jsp
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            int user_id = Dao.getUserId(session.getAttribute("email")
                    .toString());
            var user = Dao.fullDetails(user_id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("changeDetails.jsp")
                    .forward(request, response);
        }else{
            response.sendRedirect("login.jsp");
        }
    }
}

package harmo.Admin;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class CancelOrdersHandler extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isLoggedIn")))
        {
            String status = "canceled";
            int productId = Integer.parseInt(request.getParameter("productId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            if(Dao.updateOrder(status, productId, userId)){
                response.sendRedirect("ordersReceived?cancel=success");
            }else{
                response.sendRedirect("ordersReceived?error=error");
            }
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}

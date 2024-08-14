package harmo.userBean;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class MyOrdersHandler extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            int user_id = Dao.getUserId((String)session
                    .getAttribute("email"));
            var orders = Dao.getMyOrders(user_id);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("myOrders.jsp")
                    .forward(request, response);
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}

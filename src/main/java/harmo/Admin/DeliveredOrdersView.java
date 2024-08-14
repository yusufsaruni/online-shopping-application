package harmo.Admin;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DeliveredOrdersView extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        var orders = Dao.getOrders("delivered");
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isLoggedIn")))
        {
            orders.forEach(System.out::println);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("deliveredOrders.jsp")
                    .forward(request, response);
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}

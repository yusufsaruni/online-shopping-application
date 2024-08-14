package harmo.Admin;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class OrdersReceivedView extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        var orders = Dao.getOrders("processing");
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isLoggedIn")))
        {
            String error = request.getParameter("error");
            if(error != null)request.setAttribute("error", error);
            String update = request.getParameter("update");
            if(update != null)request.setAttribute("update", update);
            String cancel = request.getParameter("cancel");
            if(cancel != null)request.setAttribute("cancel", cancel);
            orders.forEach(System.out::println);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("ordersReceived.jsp")
                    .forward(request, response);
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}

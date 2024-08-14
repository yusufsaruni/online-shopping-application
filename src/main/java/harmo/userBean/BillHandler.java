package harmo.userBean;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


public class BillHandler extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            int user_id = Dao.getUserId(
                    session.getAttribute("email").toString());
            List<Cart> carts = Dao.getCarts(user_id);
            UserBean user = Dao.fullDetails(user_id);
            Address address = user.getAddress();
            double total = 0;
            for(var cart : carts){
                total += cart.getAmount();
            }
            int order_id = Integer
                    .parseInt(request.getParameter("order_id"));
            System.out.println("Order: "+ order_id);
            String dates = Dao.dates(order_id);
            request.setAttribute("message", request.getParameter("message"));
            request.setAttribute("paymentMode",request.getParameter("paymentMode"));
            request.setAttribute("total", total);
            request.setAttribute("order_date", dates.split(",")[0]);
            request.setAttribute("delivery_date", dates.split(",")[1]);
            request.setAttribute("user", user);
            request.setAttribute("address", address);
            request.setAttribute("carts", carts);
            request.getRequestDispatcher("bill.jsp")
                    .forward(request, response);
        }else{
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {

    }
}

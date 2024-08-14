package harmo.client;

import harmo.databaseconnection.Dao;
import harmo.userBean.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class CartHandler extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null&& Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            String email = (String) session.getAttribute("email");
            int user_id = Dao.getUserId(email);
            List<Cart> carts = Dao.getCarts(user_id);
            double total = 0;
            for(Cart cart : carts){
                total +=  cart.getAmount();
            }
            request.setAttribute("total", total);
            request.setAttribute("carts", carts);
            String message = request.getParameter("message");
            if(message != null){
                request.setAttribute("message", message);
            }
            request.getRequestDispatcher("myCart.jsp")
                    .forward(request, response);
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}

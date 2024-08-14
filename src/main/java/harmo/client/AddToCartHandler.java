package harmo.client;

import harmo.databaseconnection.Dao;
import harmo.userBean.Message;
import harmo.userBean.UserBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AddToCartHandler extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            String email = (String) session.getAttribute("email");
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            int quantity = 1;
            int user_id = Dao.getUserId(email);
            Message message = new Message();
            if(Dao.addToCart(user_id, product_id, quantity)){
                message.setMessage("Added to cart successfully");
            }else{
                message.setMessage("Failed to add to cart");
            }
            var products = Dao.SelectAllProducts();
            request.setAttribute("products", products);
            request.setAttribute("message", message);
            request.getRequestDispatcher("home.jsp")
                    .forward(request, response);
        }else{
            response.sendRedirect("login.jsp");
        }
    }
}

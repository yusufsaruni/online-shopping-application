package harmo.userBean;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RemoveFromCart extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean.TRUE
                .equals(session.getAttribute("isClient")))
        {
            int user_id = Dao
                    .getUserId(session.getAttribute("email").toString());
            int product_id= Dao
                    .getProductId(request.getParameter("product_name"));
            if(Dao.removeCart(product_id, user_id)){
                System.out.println("Successfully removed cart from cart");
                response.sendRedirect("cartHandler?message=Successfully removed cart from cart");
            }else {
                System.out.println("Failed to remove cart from cart");
                response.sendRedirect("cartHandler?message=Failed to remove cart from cart");
            }
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}

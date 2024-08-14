package harmo.userBean;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DecreaseIncrease extends HttpServlet {
// /cartHandler
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int product_id = Dao.getProductId(request.getParameter("product_name"));
        System.out.println("Product name: "+ request.getParameter("product_name"));
        System.out.println("Product ID: " + product_id);
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            int user_id = Dao.getUserId((String) session.getAttribute("email"));
            System.out.println("user_id: " + user_id);
            String incrDecr= request.getParameter("quantity");
            int qty = 1;
            System.out.println("qty: " + qty);
            if(incrDecr.equals("incr")){
                 if(Dao.updateCartQuantity(product_id,user_id,qty,"incr")){
                     System.out.println("success");
                     response.sendRedirect("cartHandler?message=successfully increased");
                 }else response.sendRedirect("cartHandler?message=failed");
            }else if(incrDecr.equals("decr")){
                if(Dao.updateCartQuantity(product_id,user_id,qty,"decr")){
                    System.out.println("success");
                    response.sendRedirect("cartHandler?message=successfully decreased");
                }else response.sendRedirect("cartHandler?message=failed");
            }
        }
    }
}

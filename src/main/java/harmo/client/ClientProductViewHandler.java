package harmo.client;

import harmo.databaseconnection.Dao;
import harmo.productBean.DatabaseProduct;
import harmo.userBean.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ClientProductViewHandler extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if (session != null && Boolean.TRUE.equals(session.getAttribute("isClient")))
        {
            String search = request.getParameter("search");
            List<DatabaseProduct> products;
            if (search != null && !search.isEmpty()) {
                products = Dao.searchProduct(search);
                Message message = new Message();
                message.setMessage(products.size() + " result" + (products.size() == 1 ? "" : "s") +
                        " for '" + search + "' found.");
                request.setAttribute("message", message);
            } else {
                products = Dao.SelectAllProducts();
            }
            request.setAttribute("products", products);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}

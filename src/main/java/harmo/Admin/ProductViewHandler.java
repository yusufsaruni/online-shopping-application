package harmo.Admin;

import harmo.databaseconnection.Dao;
import harmo.productBean.DatabaseProduct;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ProductViewHandler extends HttpServlet {
    //send redirect to allProductEditProduct.jsp- this forwards the data to the view
    //this will handle post-request and get request (if the user wants to edit items

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Handling get request - initial request to view the products(no edits).
       processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        List<DatabaseProduct> products = Dao.SelectAllProducts();

        if(session != null && Boolean.TRUE.equals(session.getAttribute("isLoggedIn"))) {
            session.setAttribute("products", products);
            request.setAttribute("products", products);
            RequestDispatcher rd = request
                    .getRequestDispatcher("allProductEditProduct.jsp");
            rd.forward(request, response);
        }else {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}

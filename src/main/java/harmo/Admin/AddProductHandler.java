package harmo.Admin;

import harmo.databaseconnection.Dao;
import harmo.productBean.ProductBean;
import harmo.userBean.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AddProductHandler extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //validate input will be added.
        String sku = Dao.generateSku();
        String productName = request.getParameter("productName");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        String status = request.getParameter("status");
        int stockCount = Integer.parseInt(request.getParameter("stockCount"));
        ProductBean productBean = new ProductBean();
        productBean.setSku(sku);
        productBean.setProduct_name(productName);
        productBean.setCategory(category);
        productBean.setPrice(price);
        productBean.setStatus(status);
        productBean.setStock_count(stockCount);

        HttpSession session = request.getSession(false);
        if (session != null && Boolean.TRUE
                .equals(session.getAttribute("isLoggedIn"))) {
            String email = (String) session.getAttribute("email");
            System.out.println("email: " + email);
            Message message = new Message();
            if (Dao.insertNewProduct(productBean)) {
                message.setMessage("Product Added Successfully");
                request.setAttribute("message", message);
                request.getRequestDispatcher("addNewProduct.jsp")
                        .forward(request, response);
            } else {
                message.setMessage("Error Adding Product");
                request.setAttribute("error", message);
                request.getRequestDispatcher("addNewProduct.jsp")
                        .forward(request, response);
            }
        }
    }
}

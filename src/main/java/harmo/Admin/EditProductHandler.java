package harmo.Admin;

import harmo.databaseconnection.Dao;
import harmo.productBean.DatabaseProduct;
import harmo.userBean.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class EditProductHandler extends HttpServlet {
    //get request - getting the id of the product.
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        DatabaseProduct product = Dao.getProductById(product_id);
        System.out.println("id = "+product_id);
        HttpSession session = request.getSession(false);
        if (session != null && Boolean
                .TRUE.equals(session.getAttribute("isLoggedIn"))) {
            System.out.println("Selected Product: "+ product);
            request.setAttribute("product", product);
            request.getRequestDispatcher("editProduct.jsp")
                    .forward(request, response);

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //handling editProduct.jsp post-request here. then redirect to all products
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        String productName = request.getParameter("productName");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        String status = request.getParameter("status");
        int stockCount = Integer.parseInt(request.getParameter("stockCount"));

        DatabaseProduct product = new DatabaseProduct();
        product.setProduct_id(product_id);
        product.setProduct_name(productName);
        product.setCategory(category);
        product.setPrice(price);
        product.setStatus(status);
        product.setStock_count(stockCount);

        HttpSession session = request.getSession(false);
        Message message = new Message();
        if (session != null&&Boolean.TRUE.equals(session
                .getAttribute("isLoggedIn"))) {
        if (Dao.updateProduct(product)) {
            //if the product is set to 'Inactive' so the item should be deleted from carts.
            if(product.getStatus().equals("Inactive"))Dao.deleteCart(product_id);
            response.sendRedirect("productView");
            }
        } else{
            message.setMessage("Product not updated");
            request.setAttribute("error", message);
            request.getRequestDispatcher("editProduct.jsp")
                    .forward(request, response);
        }

    }
}

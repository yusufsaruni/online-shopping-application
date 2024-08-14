package harmo.userBean;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class PaymentOrderHandler extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && Boolean.TRUE.equals(session.getAttribute("isClient"))) {
            int user_id = Dao.getUserId((String) session.getAttribute("email"));
            List<Cart> carts = Dao.getCarts(user_id);
            UserBean user = Dao.fullDetails(user_id);
            Address address = user.getAddress();
            List<String> modes = Dao.modesOfPayment();
            double total = 0;
            for (var cart : carts) {
                total += cart.getAmount();
            }
            request.setAttribute("modes", modes);
            request.setAttribute("user", user);
            request.setAttribute("address", address);
            request.setAttribute("total", total);
            request.setAttribute("carts", carts);
            request.getRequestDispatcher("addressPaymentForOrder.jsp")
                    .forward(request, response);

        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && Boolean.TRUE.equals(session.getAttribute("isClient"))) {
            int user_id = Dao.getUserId((String) session.getAttribute("email"));
            String address = request.getParameter("address");
            String state = request.getParameter("state");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String paymentMode = request.getParameter("paymentMode");
            int payment_id = Dao.getPaymentModeId(paymentMode);
            List<Cart> carts = Dao.getCarts(user_id);
            boolean allOrdersInserted = true;
            int order_id = 0;
            if (Dao.updateAddress(user_id, address, state, city, country)) {
                for (var cart : carts) {
                    int product_id = Dao.getProductId(cart.getProduct_name());
                    int quantity = cart.getQuantity();
                    double amount = cart.getAmount();
                    order_id = Dao.insertOrder(user_id, product_id, quantity, amount, payment_id);
                    if (order_id <= 0) {
                        allOrdersInserted = false;
                        break;
                    }
                }
                System.out.println("Order ID: " + order_id);
                if (allOrdersInserted) {
                    if(Dao.updateOrderStatus(user_id,"confirmation",
                            "processing")){
                        Dao.deleteUserCart(user_id);
                        response.sendRedirect("billHandler?paymentMode=" + paymentMode +
                                "&order_id=" + order_id);
                    }
                } else {
                    response.sendRedirect("billHandler?message=" +
                            "couldn't complete order, try again later.");
                }
            } else {
                response.sendRedirect("billHandler?message=" +
                        "something went wrong, try again later.");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}

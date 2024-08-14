package harmo.changeDetails;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AddressChangeHandler extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            int user_id = Dao.getUserId(
                    (String)session.getAttribute("email"));
            var address = Dao.fullDetails(user_id)
                    .getAddress();
            request.setAttribute("address", address);
            request.getRequestDispatcher("addChangeAddress.jsp")
                    .forward(request, response);
        }else {
            response.sendRedirect("login.jsp");
        }
    }

    protected  void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null&& Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            int user_id = Dao.getUserId((String)session.getAttribute("email"));
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String country = request.getParameter("country");
            String password = request.getParameter("password");
            String correctPassword = Dao.fullDetails(user_id)
                    .getPassword();
            if(!password.equals(correctPassword)){
                response.sendRedirect("addChangeAddress.jsp" +
                        "?error=incorrectPassword");
            }else {
                if(Dao.updateAddress(user_id,address,city,state,country)){
                    response.sendRedirect("addChangeAddress.jsp?" +
                            "message=success");
                }else{
                    response.sendRedirect("addChangeAddress.jsp?" +
                            "error=genericError");
                }
            }
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}



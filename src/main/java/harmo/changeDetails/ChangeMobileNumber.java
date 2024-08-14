package harmo.changeDetails;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ChangeMobileNumber extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            String mobile_number =
                    Dao.fullDetails(
                            Dao.getUserId((String)session.getAttribute(
                                    "email"))).getMobileNumber();
            request.setAttribute("mobileNumber", mobile_number);
            request.getRequestDispatcher("changeMobileNumber.jsp")
                    .forward(request, response);
        }else{
            response.sendRedirect("login.jsp");
        }
    }

    protected  void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException , IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null&&
        Boolean.TRUE.equals(session.getAttribute("isClient")))
        {
            String mobileNumber = request.getParameter("mobileNumber");
            String password = request.getParameter("password");
            String original_password =
                    Dao.fullDetails(
                            Dao.getUserId((String)session.getAttribute(
                                    "email"))).getPassword();
            if(!password.equals(original_password)){
                response.sendRedirect("changeMobileNumber.jsp?" +
                        "error=incorrectPassword");
            }else {
                if(Dao.updateMobileNumber(Dao.getUserId(
                        (String)session.getAttribute("email")),
                        mobileNumber)){
                    response.sendRedirect("changeMobileNumber.jsp?" +
                            "message=success");
                }else response.sendRedirect("changeMobileNumber.jsp?" +
                        "error=genericError");
            }
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}

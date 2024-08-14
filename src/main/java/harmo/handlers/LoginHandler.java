package harmo.handlers;

import harmo.Admin.Resources.GetProperties;
import harmo.databaseconnection.Dao;
import harmo.userBean.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Properties;

public class LoginHandler extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Properties prop = GetProperties.databaseProps();
        if(email.equals(prop.get("email")) && password
                .equals(prop.get("password"))){
            HttpSession session = request.getSession(true);
            session.setAttribute("email", email);
            session.setAttribute("isLoggedIn", true);
            response.sendRedirect("adminHeader");
        }else{
            Message message = new Message();
            boolean loginStatus = Dao.check(email, password,"login");
            if(loginStatus){
                HttpSession session = request.getSession(true);
                session.setAttribute("email", email);
                session.setAttribute("isClient", true);
                response.sendRedirect("clientProductView");
            }else{
                message.setMessage("Invalid email or password");
                request.setAttribute("error", message);
                request.getRequestDispatcher("login.jsp")
                        .forward(request, response);
            }
        }
    }
}

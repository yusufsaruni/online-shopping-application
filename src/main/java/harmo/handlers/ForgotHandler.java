package harmo.handlers;

import harmo.databaseconnection.Dao;
import harmo.userBean.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ForgotHandler extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String mobilenumber = request.getParameter("mobilenumber");
        String securityQuestion = request.getParameter("securityQuestion");
        String answer = request.getParameter("answer");
        String password = request.getParameter("password");
        Message message = new Message();
        if(Dao.check(email, mobilenumber,"reset")){
            if(Dao.resetPassword(email,securityQuestion,answer,password)){
                System.out.println("Password changed");
                message.setMessage("Password Reset Successful!");
                request.setAttribute("message", message);
                request.getRequestDispatcher("forgotPassword.jsp").forward(request,
                        response);
            }else{
                message.setMessage("Something went wrong! Please try again");
                request.setAttribute("error", message);
                request.getRequestDispatcher("forgotPassword.jsp").forward(request,
                        response);
            }
        }else{
            message.setMessage("Email or Phone number doesn't exist!");
            request.setAttribute("error", message);
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        }
    }
}

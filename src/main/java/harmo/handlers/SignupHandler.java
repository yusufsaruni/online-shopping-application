package harmo.handlers;

import harmo.databaseconnection.Dao;
import harmo.userBean.Message;
import harmo.userBean.UserBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SignupHandler extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobilenumber = request.getParameter("mobilenumber");
        String security = request.getParameter("security");
        String answer = request.getParameter("answer");
        String password = request.getParameter("password");
        UserBean user = new UserBean();
        user.setName(name);
        user.setEmail(email);
        user.setMobileNumber(mobilenumber);
        user.setSecurityQuestion(security);
        user.setSecurityAnswer(answer);
        user.setPassword(password);
        boolean result = Dao.insertUser(user);
        Message message = new Message();
        if (result ) {
            message.setMessage("Account created successfully. Proceed to log in.");
            request.setAttribute("message", message);
            request.getRequestDispatcher("signup.jsp").forward(request,
                        response);
            System.out.println("Address inserted successfully");
        } else {
            message.setMessage("Something went wrong! Please try again.");
            request.setAttribute("error", message);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

}

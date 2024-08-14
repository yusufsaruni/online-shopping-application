package harmo.changeDetails;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ChangePasswordHandler extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean.TRUE.equals(session.getAttribute("isClient"))) {
            String email = session.getAttribute("email").toString();
            int user_id = Dao.getUserId(email);
            String old_password = request.getParameter("old_password");
            String new_password1 = request.getParameter("new_password1");
            String new_password2 = request.getParameter("new_password2");
            var user = Dao.fullDetails(user_id);
            String original_password = user.getPassword();

            String redirectUrl = "changePassword.jsp";

            if (!new_password1.equals(new_password2)) {
                redirectUrl += "?error=notMatch";
            } else if (!old_password.equals(original_password)) {
                redirectUrl += "?error=incorrectOld";
            } else if (new_password1.equals(original_password)) {
                redirectUrl += "?error=sameAsOld";
            } else {
                if (Dao.changePassword(user_id, new_password1)) {
                    redirectUrl += "?message=success";
                } else {
                    redirectUrl += "?error=genericError";
                }
            }

            response.sendRedirect(redirectUrl);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}

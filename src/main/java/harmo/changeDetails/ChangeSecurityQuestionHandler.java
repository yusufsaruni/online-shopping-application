package harmo.changeDetails;

import harmo.databaseconnection.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ChangeSecurityQuestionHandler extends HttpServlet {

    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            int user_id = Dao.getUserId(
                    (String)session.getAttribute("email"));
            String security_question =
                    Dao.fullDetails(user_id).getSecurityQuestion();
            String answer = Dao.fullDetails(user_id).getSecurityAnswer();
            request.setAttribute("security_question", security_question);
            request.setAttribute("answer", answer);
            request.getRequestDispatcher("changeSecurityQuestion.jsp")
                    .forward(request, response);
        }else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean
                .TRUE.equals(session.getAttribute("isClient")))
        {
            int user_id = Dao.getUserId(
                    (String)session.getAttribute("email"));
            String original_password = Dao.fullDetails(user_id).getPassword();
            String password = request.getParameter("password");
            String security_question = request.getParameter("security");
            String answer = request.getParameter("answer");
            if(!original_password.equals(password)){
                response.sendRedirect("changeSecurityQuestion.jsp?" +
                        "error=incorrectPassword");
            }else{
                if(Dao.updateSecurityQuestion(user_id,security_question,
                        answer)){
                    response.sendRedirect("changeSecurityQuestion.jsp?" +
                            "message=success");
                }else response.sendRedirect("changeSecurityQuestion.jsp?" +
                        "error=genericError");
            }
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}

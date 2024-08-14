
<!DOCTYPE html>
<html lang="en">
<%@page errorPage="error.jsp" %>
<head>
    <link rel="stylesheet" href="css/signup-style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:useBean id="error" scope="request"
                 class="harmo.userBean.Message"/>
    <jsp:useBean id="message" scope="request"
                 class="harmo.userBean.Message"/>
    <title>Login</title>
</head>
<body>
<div id='container' class="maincontainer">
  <div class='signup'>
      <c:if test="${not empty error.message}">
          <p class="error-message">${error.message}</p>
      </c:if>
     <form action="${pageContext.request.contextPath}/loginHandler" method="post">
         <fieldset class="form-field">
             <legend>Harmo Online Shop</legend>
         <label>
             <input type="email" name="email" placeholder="Enter email"
             required autocomplete="new-email" id="email">
         </label>
             <label>
                 <input type="password" name="password" id="password"
                        placeholder="Enter password"
                 required autocomplete="new-password">
             </label>
             <button type="button"
                     onclick="togglePasswordVisibility()">
                 Show/Hide Password</button>
             <label>
                 <input type="submit" value="login" class="submit-button">
             </label>
         </fieldset>
     </form>
      <c:if test="${not empty message.message}">
          <p class="success-message">${message.message}</p>
      </c:if>
      <h2><a href="signup.jsp">SignUp</a></h2>
       <h2><a href="forgotPassword.jsp">Forgot Password?</a></h2>
      <h2>Online Shopping</h2>
      <p>The Online Shopping System is the application that allows the users to
          shop online without going to the shops to buy them.</p>
  </div>
</div>
<script>
    function togglePasswordVisibility() {
        const x = document.getElementById("password");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }
</script>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/signup-style.css">
    <title>ForgotPassword</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:useBean id="message" scope="request"
                 class="harmo.userBean.Message"/>
    <jsp:useBean id="error" scope="request"
                 class="harmo.userBean.Message"/>
</head>
<body>
<div id='container' class="maincontainer">

  <div class='signup'>
      <form action="${pageContext.request.contextPath}/forgotHandler"
      method="post">
          <c:if test="${not empty error.message}">
              <p class="error-message">${error.message}</p>
          </c:if>
          <fieldset class="form-field">
              <legend>Reset password</legend>
              <label>
                    <input type="email" name="email" id="email" required
                    placeholder="Enter email">
              </label>
              <label>
                    <input type="tel" name="mobilenumber" id="mobilenumber"
                  placeholder="Enter mobilenumber" required>
              </label>
              <label>
                  <select name="securityQuestion">
                      <option value="what was your first car?">
                          what was your first car?
                      </option>
                      <option value="what is the name of your first pet">
                          what is the name of your first pet</option>
                      <option value="what elementary school did you attend?">
                          what elementary school did you attend?
                      </option>
                      <option value="what is the name of your birth county?">
                          what is the name of your birth county?
                      </option>
                  </select>
              </label>

              <label>
                  <input type="text" name="answer" placeholder="Enter answer"
                         required id="answer">
              </label>
              <label>
                  <input type="password" name="password" id="password"
                         placeholder="Enter new password"
                         required autocomplete="new-password">
              </label>
              <button type="button"
                      onclick="togglePasswordVisibility()">
                  Show/Hide Password</button>
              <label>
                  <input type="submit" value="Reset password"
                         class="submit-button">
              </label>
          </fieldset>
          <c:if test="${not empty message.message}">
              <p class="success-message">${message.message}</p>
          </c:if>
      </form>
      <h2><a href="login.jsp">Login</a></h2>
     <h2>Online Shopping</h2>
      <p>The Online Shopping System is the application that allows the users
          to shop online without going to the shops to buy them.</p>
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
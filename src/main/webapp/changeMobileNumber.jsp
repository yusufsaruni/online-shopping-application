<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="css/changeDetails.css">
 <%@include file="changeDetailsHeader.jsp"%>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <title>Change Mobile Number</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/changeNumber"
class="myMain-form" method="post">
 <c:if test="${param.error == 'incorrectPassword'}">
  <p class="error-message">Can't save changes. Incorrect Password!
  </p>
 </c:if>
 <c:if test="${param.message == 'success'}">
  <p class="success-message">Changes saved successfully!</p>
 </c:if>
 <c:if test="${param.error == 'genericError'}">
  <p class="error-message">Something went wrong! Please try again.</p>
 </c:if>
 <div>
  <label for="mobileNumber">Enter new mobile Number</label>
  <input type="tel" name="mobileNumber" value="${requestScope.mobileNumber}"
         required id="mobileNumber" placeholder="Enter your mobile number">
 </div>
 <div>
  <label for="password">Your password is required:</label>
  <input type="password" id="password" name="password"
         required placeholder="Enter your password"
         autocomplete="new-password">
 </div>
 <div class="form-buttons">
  <button type="submit">Save Changes</button>
 </div>
</form>

</body>
<br><br><br>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="css/changeDetails.css">
<%@include file="changeDetailsHeader.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Change Details</title>
</head>
<body>
<form class="myMain-form"
      action="${pageContext.request.contextPath}/addressChangeHandler" method="post"
      autocomplete="off">
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
  <label for="country">Country:</label>
  <input type="text" id="country" name="country"
         value="${requestScope.address.country}" required
  placeholder="Enter country">
 </div>
 <div>
  <label for="state">Sate:</label>
  <input type="text" id="state" name="state"
         value="${requestScope.address.state}" required
  placeholder="Enter state">
 </div>
 <div>
  <label for="address">Address:</label>
  <input type="text" id="address" name="address"
         value="${requestScope.address.address}"
  placeholder="Enter address">
 </div>
 <div>
  <label for="city">City:</label>
  <input type="text" id="city" name="city"
         required value="${requestScope.address.city}"
  placeholder="Enter city">
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
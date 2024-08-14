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
      action="${pageContext.request.contextPath}/changePassword" method="post"
 autocomplete="off">
    <c:if test="${param.message == 'success'}">
        <p class="success-message">Password successfully changed!</p>
    </c:if>
    <c:if test="${param.error == 'notMatch'}">
        <p class="error-message">Passwords do not match!</p>
    </c:if>
    <c:if test="${param.error == 'incorrectOld'}">
        <p class="error-message">Incorrect Old password!</p>
    </c:if>
    <c:if test="${param.error == 'sameAsOld'}">
        <p class="error-message">New password cannot be the same as the old password!</p>
    </c:if>
    <c:if test="${param.error == 'genericError'}">
        <p class="error-message">Something went wrong! Please try again.</p>
    </c:if>
    <div>
        <label for="old_password">Old Password:</label>
        <input type="password" id="old_password" name="old_password"
               placeholder="Enter Old Password" required autocomplete="new-password">
    </div>
    <div>
        <label for="new_password1">New password:</label>
        <input type="password" id="new_password1" name="new_password1"
               placeholder="Enter new password" required autocomplete="new-password">
    </div>
    <div>
        <label for="new_password2">New password:</label>
        <input type="password" id="new_password2" name="new_password2"
               placeholder="Confirm new password" required autocomplete="new-password">
    </div>
    <div class="form-buttons">
        <button type="submit">Change Password</button>
    </div>
</form>

</body>
<br><br><br>
</html>
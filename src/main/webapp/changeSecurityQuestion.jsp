<!DOCTYPE html>
<html lang="en">
<head>
 <link rel="stylesheet" href="css/changeDetails.css">
 <%@include file="changeDetailsHeader.jsp"%>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <title>Change Security Question</title>
</head>
<body>

<form class="myMain-form"
      action="${pageContext.request.contextPath}/changeSecurityQuestion" method="post"
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
  <label for="security">Choose new security question</label>
  <select name="security" required id="security">
   <option value="what was your first car?"
           <c:if test="${requestScope.security_question == 'what was your first car?'}">selected</c:if>>
    what was your first car?
   </option>
   <option value="what is the name of your first pet?"
           <c:if test="${requestScope.security_question == 'what is the name of your first pet?'}">selected</c:if>>
    what is the name of your first pet
   </option>
   <option value="what elementary school did you attend?"
           <c:if test="${requestScope.security_question == 'what elementary school did you attend?'}">selected</c:if>>
    what elementary school did you attend?
   </option>
   <option value="what is the name of your birth county?"
           <c:if test="${requestScope.security_question == 'what is the name of your birth county?'}">selected</c:if>>
    what is the name of your birth county?
   </option>
  </select>
 </div>

 <div>
 <label for="answer">Enter your answer</label>
  <input type="text" name="answer" value="${requestScope.answer}"
         required id="answer" placeholder="Enter your answer">
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
<hr>
 <i class='far fa-arrow-alt-circle-right'></i>

</body>
<br><br><br>
</html>
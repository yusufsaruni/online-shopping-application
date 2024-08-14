<!DOCTYPE html>
<html>
<%@page errorPage="error.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%@include file="header.jsp"%>
<%--    <link rel="stylesheet" href="css/adminHome.css">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/messageUs.css">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Message Us</title>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">Message Us
    <i class='fas fa-comment-alt'></i></div>
<div class="response">
<c:if test="${not empty param.error && param.error == 'genericError'}">
    <p class="error-message">Something Went Wrong! Please Try again!</p>
</c:if>
<c:if test="${not empty param.message && param.message == 'success'}">
    <p class="success-message">Message successfully sent. Our team will
        contact you soon!</p>
</c:if>
</div>

 <form action="${pageContext.request.contextPath}/messageHandler" method="post">
     <label for="subject"></label><input class="input-style" name="subject" type="text" id="subject"
                                         placeholder="subject" required>

    <br><br>
    <label for="message"></label>
    <textarea class="input-style" id="message" placeholder="Enter your message"
                                           name="message"></textarea>
     <button class="button" type="submit">Send</button>
 </form>

<br><br><br>
</body>
</html>

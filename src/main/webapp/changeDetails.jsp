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
<table class="details">
    <tr>
        <td>Name:</td>
        <td>${requestScope.user.name}</td>
    </tr>
    <tr>
        <td>Email:</td>
        <td>${requestScope.user.email}</td>
    </tr>
    <tr>
        <td>Mobile Number:</td>
        <td>${requestScope.user.mobileNumber}</td>
    </tr>
    <tr>
        <td>Security Question:</td>
        <td>${requestScope.user.securityQuestion}</td>
    </tr>
</table>

<footer class="footer">
    <p>All Right Reserved @ HarmoKE</p>
</footer>
</body>
</html>

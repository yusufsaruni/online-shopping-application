
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="adminHeader.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Message Received</title>
<style>
h3
{
	color: yellow;
	text-align: center;
}
</style>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">Messages Received <i class='fas fa-comment-alt'></i></div>
<table>
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Email</th>
            <th scope="col">Subject</th>
            <th scope="col">Message</th>
          </tr>
        </thead>
        <tbody>
       <c:forEach var="message" items="${requestScope.messages}">
          <tr>
            <td>${message.message_id}</td>
            <td>${message.email}</td>
            <td>${message.subject}</td>
            <td>${message.message}</td>
          </tr>
       </c:forEach>
        </tbody>
      </table>
      <br>
      <br>
      <br>
<footer class="footer">
    <p>All Right Reserved @ HarmoKE</p>
</footer>
</body>
</html>
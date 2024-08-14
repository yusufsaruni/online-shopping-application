<jsp:useBean id="message" scope="request" class="harmo.userBean.Message"/>
<%@page errorPage="error.jsp" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Home</title>
    <%@include file="header.jsp"%>
    <link rel="stylesheet" href="css/adminHome.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
h3
{
	color: yellow;
	text-align: center;
}
</style>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">Home <i class="fa fa-institution"></i></div>

<h2>${message.message}</h2>
<table>
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
              <th scope="col">ADD TO CART</th>
          </tr>
        </thead>
        <tbody>

      <c:forEach var="product" items="${requestScope.products}">
          <tr>
            <td>${product.product_id}</td>
            <td>${product.product_name}</td>
            <td>${product.category}</td>
            <td>${product.price}</td>
            <td>
                <a href="${pageContext.request.contextPath}/addToCart?product_id=${product.product_id}">
                Add To Cart
                <i class='fas fa-cart-plus'></i>
                </a>
            </td>
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
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/bill.css">
    <title>My Orders</title>
    <%@page errorPage="error.jsp" %>
    <%@include file="header.jsp"%>
    <link rel="stylesheet" href="css/adminHome.css" >
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">My Orders <i class='fab fa-elementor'></i></div>
<table class="customers">
        <thead>
          <tr>
              <th scope="col">Product Name</th>
              <th scope="col">Price</th>
              <th scope="col">Quantity</th>
              <th scope="col">Amount</th>
              <th scope="col">Order Date</th>
              <th scope="col">Expected Delivery Date</th>
              <th scope="col">Payment Method</th>
              <th scope="col">Status</th>
          </tr>
        </thead>
    <tbody>
    <c:forEach var="order" items="${requestScope.orders}">
          <tr>
              <td>${order.product_name}</td>
              <td>${order.price}</td>
              <td>${order.quantity}</td>
              <td>${order.amount}</td>
              <td>${order.order_date}</td>
              <td>${order.delivery_date}</td>
              <td>${order.payment_method}</td>
              <td>${order.status}</td>
            </tr>
    </c:forEach>
        </tbody>
      </table>
      <br>
      <br>
      <br>

</body>
</html>
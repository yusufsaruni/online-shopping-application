<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="css/bill.css">
  <title>Bill</title>
  <%@page errorPage="error.jsp" %>
    <link rel="stylesheet" href="css/print.css" media="print">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>

<hr>
<%--This data--%>
<table class="customers">
    <thead>
    <tr>
        <th>Order Details</th>
        <th>Information</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Name</td>
        <td>${requestScope.user.name}</td>
    </tr>
    <tr>
        <td>Email</td>
        <td>${requestScope.user.email}</td>
    </tr>
    <tr>
        <td>Mobile Number</td>
        <td>${requestScope.user.mobileNumber}</td>
    </tr>
    <tr>
        <td>Order Date</td>
        <td>${requestScope.order_date}</td>
    </tr>
    <tr>
        <td>Payment Method</td>
        <td>${requestScope.paymentMode}</td>
    </tr>
    <tr>
        <td>Expected Delivery</td>
        <td>${requestScope.delivery_date}</td>
    </tr>
    <tr>
        <td>City</td>
        <td>${requestScope.address.city}</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>${requestScope.address.address}</td>
    </tr>
    <tr>
        <td>State</td>
        <td>${requestScope.address.state}</td>
    </tr>
    <tr>
        <td>Country</td>
        <td>${requestScope.address.country}</td>
    </tr>
    </tbody>
</table>


<hr>

<br>

<table class="customers">
  <thead>Product Details</thead>
  <tr>
    <th>Product Name</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Amount</th>
  </tr>
 <tbody>
 <c:forEach var="cart" items="${requestScope.carts}">
  <tr>
    <td>${cart.product_name}</td>
    <td>${cart.product_price}</td>
    <td>${cart.quantity}</td>
    <td>${cart.amount}</td>
  </tr>
 </c:forEach>
 </tbody>
    <tfoot>
    <tr>
        <td colspan="4"><strong>Total: ${requestScope.total}</strong></td>
    </tr>
    </tfoot>
 </table>
<a href="${pageContext.request.contextPath}/clientProductView">
  <button class="button left-button">Continue Shopping</button></a>
<a onclick="window.print();">
  <button class="button right-button">Print</button></a>
<br><br><br><br>
<footer class="footer">
  <p>All Right Reserved @ HarmoKE</p>
</footer>

</body>
</html>

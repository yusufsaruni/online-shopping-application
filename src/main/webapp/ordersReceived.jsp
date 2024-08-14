
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/ordersReceived-style.css">
    <title>Home</title>
    <%@include file="adminHeader.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">

{ width: 25%;}
</style>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">Orders Received
    <i class="fas fa-archive"></i></div>

<c:if test="${not empty param.error && param.error == 'error'}">
    <p class="error-message">Something went wrong! Try Again!</p>
</c:if>
<c:if test="${not empty param.cancel && param.cancel=='success'}">
    <p class="success-message">Order Canceled Successfully!</p>
</c:if>
<c:if test="${not empty param.update && param.update=='success'}">
    <p class="success-message">Successfully Updated!</p>
</c:if>
<table id="customers">
          <tr>
              <th>Mobile Number</th>
              <th scope="col">Product Name</th>
              <th scope="col">Quantity</th>
              <th scope="col">Amount</th>
              <th>Address</th>
              <th>City</th>
              <th>State</th>
              <th>Country</th>
              <th scope="col">Order Date</th>
              <th scope="col">Expected Delivery Date</th>
              <th scope="col">Payment Method</th>
              <th scope="col">Status</th>
              <th scope="col">Cancel order <i class='fas fa-window-close'></i></th>
              <th scope="col">Order Delivered <i class='fas fa-dolly'></i></th>
          </tr>
        
    <tbody>
    <c:forEach var="order" items="${requestScope.orders}">
          <tr>
              <td>${order.mobileNumber}</td>
              <td>${order.productName}</td>
              <td>${order.quantity}</td>
              <td>${order.total}</td>
              <td>${order.address}</td>
              <td>${order.city}</td>
              <td>${order.state}</td>
              <td>${order.country}</td>
              <td>${order.orderDate}</td>
              <td>${order.deliveryDate}</td>
              <td>${order.paymentMethod}</td>
              <td>${order.status}</td>
              <td><a href="${pageContext.request.contextPath}/cancelOrdersHandler?userId=${order.userId}&productId=${order.productId}">
                  Cancel <i class='fas fa-window-close'></i></a></td>
              <td><a href="${pageContext.request.contextPath}/deliveredOrdersHandler?userId=${order.userId}&productId=${order.productId}">
                  Delivered<i class='fas fa-dolly'></i></a></td>
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
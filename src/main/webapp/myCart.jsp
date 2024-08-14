
<!DOCTYPE html>
<html>
<%@page errorPage="error.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>My Cart</title>
    <%@include file="header.jsp"%>
    <link rel="stylesheet" href="css/adminHome.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">My Cart <i class='fas fa-cart-arrow-down'></i></div>
<p>${requestScope.message}</p>
<table>
<thead>

          <tr>
            <th scope="col" colspan="3" class="total">
                Total: ${requestScope.total}</th>
              <c:if test="${requestScope.total>0}">
            <th scope="col" colspan="3"><a href="${pageContext.request.contextPath}/PaymentOrderHandler">Proceed to order</a></th>
              </c:if>
          </tr>
        </thead>
        <thead>
          <tr>
          <th scope="col">SERIAL NO</th>
            <th scope="col">Product Name</th>
            <th scope="col"> price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Amount</th>
            <th scope="col">Remove <i class='fas fa-trash-alt'></i></th>
          </tr>
        </thead>
        <tbody>
       <c:forEach var="cart" items="${requestScope.carts}">
          <tr>
           <td>${cart.serial_number}</td>
            <td>${cart.product_name}</td>
            <td>${cart.product_price}</td>
            <td> <a href="${pageContext.request.contextPath}/incDecr?product_name=${cart.product_name}&quantity=decr">
                <button class="decrease-quantity">-</button></a>${cart.quantity}
                <a href="${pageContext.request.contextPath}/incDecr?product_name=${cart.product_name}&quantity=incr">
                    <button class="increase-quantity">+</button></a></td>
            <td>${cart.amount}</td>
            <td>
                <a href="${pageContext.request.contextPath}/removeFromCart?product_name=${cart.product_name}">Remove</a></td>
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
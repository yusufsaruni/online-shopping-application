
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/addressPaymentForOrder-style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>My Cart</title>
    <%@page errorPage="error.jsp" %>
    <link rel="stylesheet" href="css/adminHome.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<h2>${requestScope.message}</h2>
<br>
<table>
<thead>
          <tr>
          <th scope="col" colspan="2"><a href="${pageContext.request.contextPath}/cartHandler">Back</a></th>
            <th scope="col" class="total" colspan="2">Total: ${requestScope.total}</th>
          </tr>
        </thead>
        <thead>
          <tr>
            <th scope="col">Product Name</th>
            <th scope="col">price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Amount</th>
          </tr>
        </thead>
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
      </table>

<form class="custom-form"
      action="${pageContext.request.contextPath}/PaymentOrderHandler" method="post">
    <div>
        <p><strong>NOTE:Changes made here(On addresses) will be updated to your profile</strong></p>
    </div>
    <div>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address"
               value="${requestScope.address.address}" required>
    </div>
    <div>
        <label for="state">State:</label>
        <input type="text" id="state" name="state"
               value="${requestScope.address.state}" required>
    </div>
    <div>
        <label for="city">City:</label>
        <input type="text" id="city" name="city"
               value="${requestScope.address.city}" required>
    </div>
    <div>
        <label for="country">Country:</label>
        <input type="text" id="country" name="country"
               value="${requestScope.address.country}" required>
    </div>
    <div>
        <label for="paymentMode">Mode of Payment:</label>
        <select id="paymentMode" name="paymentMode" required>
            <c:forEach var="mode" items="${requestScope.modes}">
            <option value="${mode}">${mode}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-buttons">
        <button type="submit">Address Fine? Proceed to Make payment</button>
        <button type="reset">Reset Address Fields</button>
    </div>
</form>
<footer class="footer">
    <p>All Right Reserved @ HarmoKE</p>
</footer>

</body>
</html>

<%--<%@page errorPage="error.jsp" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/home-style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Header</title>
</head>
<body>
<br>
<div class="topnav sticky">
    <div style="text-align: center;">
        <h2>Online shopping (Harmo KE)</h2>
    </div>
       <a href="addNewProduct.jsp">
            Add New Product</a>
        <a href="${pageContext.request.contextPath}/productView">
            All Products & Edit Products</a>
        <a href="${pageContext.request.contextPath}/messageReceivedHandler">Messages Received</a>
        <a href="${pageContext.request.contextPath}/ordersReceived">Orders Received</a>
        <a href="${pageContext.request.contextPath}/canceledOrdersView">Cancel Orders</a>
        <a href="${pageContext.request.contextPath}/deliveredOrdersView">Delivered Orders</a>
         <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
<br>
</body>
</html>

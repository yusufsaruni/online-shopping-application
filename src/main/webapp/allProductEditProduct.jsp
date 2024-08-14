
<!DOCTYPE html>
<html>
<head>
    <%@include file="adminHeader.jsp"%>
    <link rel="stylesheet" href="css/adminHome.css">
    <link rel="stylesheet" href="css/signup-style.css">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Home</title>
</head>
<body>
<div class="maincontainer">
 <div class="table-view">
     <table>
        <thead>
        <tr>
            <th colspan="8" scope="col" class="table-header"
            >ALL PRODUCTS</th>
        </tr>
          <tr>
            <th scope="col">ID</th>
              <th scope="col">SKU</th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col"> Price</th>
            <th scope="col">Status</th>
              <th scope="col">Stock Count</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
       <c:forEach var="product" items="${requestScope.products}">

          <tr>
                <td>${product.product_id}</td>
                <td>${product.sku}</td>
                <td>${product.product_name}</td>
                <td>${product.category}</td>
                <td>${product.price}</td>
                <td>${product.status}</td>
                <td>${product.stock_count}</td>
            <td>
                <a href="${pageContext.request.contextPath}/editProductHandler?product_id=${product.product_id}">
                Edit</a></td>
          </tr>
       </c:forEach>
        </tbody>
      </table>
 </div>
<footer class="footer">
    <p>All Right Reserved @ HarmoKE</p>
</footer>
</div>
</body>
</html>
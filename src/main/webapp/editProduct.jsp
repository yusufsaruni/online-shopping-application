
<html lang="en">
<!DOCTYPE html>
<head>
 <link rel="stylesheet" href="css/addNewProduct-style.css">
 <meta charset="UTF-8">
 <title>welcome</title>
 <%@include file="adminHeader.jsp"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:useBean id="error" scope="request"
              class="harmo.userBean.Message"/>
 <jsp:useBean id="message" scope="request"
              class="harmo.userBean.Message"/>
 <jsp:useBean id="product" scope="request"
               class="harmo.productBean.DatabaseProduct"/>
 <link rel="stylesheet" href="css/adminHome.css">
 <link rel="stylesheet" href="css/signup-style.css">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="maincontainer">
 <form action="${pageContext.request.contextPath}/editProductHandler?product_id=${product.product_id}" method="post" class="signup">
  <c:if test="${not empty error.message}">
   <p class="error-message">${error.message}</p>
  </c:if>
  <fieldset class="form-field">
   <legend>Edit Product</legend>
   <label>
    <input type="text" name="productName" required
    value="${product.product_name}">
   </label>
   <label>
    <input type="text" name="category" id="category"
           value="${product.category}" required>
   </label>
   <label>
    <input type="text" value="${product.price}" name="price"
           required>
   </label>
   <label>
    <select name="status">
     <option value="Active" ${product.status == 'Active' ? 'selected' : ''}>Active</option>
     <option value="Inactive" ${product.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
    </select>
   </label>
   <label>
    <input type="text" value="${product.stock_count}" name="stockCount"
           required>
   </label>
   <label>
    <input type="submit" class="submit-button" value="Save Changes">
   </label>
   <label>
    <input type="button" class="submit-button" value="Back" onclick="history.back()">
   </label>
  </fieldset>
  <c:if test="${not empty message.message}">
   <p class="success-message">${message.message}</p>
  </c:if>
 </form>
</div>

<footer class="footer">
 <p>All Right Reserved @ HarmoKE</p>
</footer>
</body>
</html>
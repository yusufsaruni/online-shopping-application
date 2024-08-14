
<html lang="en">
<!DOCTYPE html>
<head>
    <link rel="stylesheet" href="css/addNewProduct-style.css">
    <meta charset="UTF-8">
    <title>welcome</title>
    <%@include file="adminHeader.jsp"%>
    <link rel="stylesheet" href="css/adminHome.css">
    <link rel="stylesheet" href="css/signup-style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
 <div class="maincontainer">
    <form action="${pageContext.request.contextPath}/addProductHandler"
          method="post" class="signup">
        <c:if test="${not empty requestScope.error.message}">
            <p class="error-message">${requestScope.error.message}</p>
        </c:if>
        <fieldset class="form-field">
            <legend>Add New Product</legend>
              <label>
                  <input type="text" placeholder="Enter product name"
                  name="productName" required>
              </label>
            <label>
                <input type="text" name="category" id="category"
                       placeholder="Category" required>
            </label>
            <label>
                <input type="text" placeholder="price" name="price"
                required>
            </label>
            <label>
                <select name="status" id="status">
                    <option value="Active">Active</option>
                    <option value="Inactive">Inactive</option>
                </select>
            </label>
            <label>
                <input type="text" placeholder="Stock count(Quantity)" name="stockCount"
                       required>
            </label>
            <label>
                <input type="submit" class="submit-button" value="Add Product">
            </label>
            <label>
                <input type="button" class="submit-button" value="Back" onclick="history.back()">
            </label>
        </fieldset>
        <c:if test="${not empty requestScope.message.message}">
            <p class="success-message">${requestScope.message.message}</p>
        </c:if>
    </form>
 </div>

 <footer class="footer">
  <p>All Right Reserved @ HarmoKE</p>
 </footer>
</body>
</html>
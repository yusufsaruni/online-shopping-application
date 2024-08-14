
<%@page errorPage="error.jsp" %>
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
            <h2><a href="">${sessionScope.email}<i class="user"></i></a></h2>
            <a href="${pageContext.request.contextPath}/clientProductView">Home<i class="fa fa-institution"></i></a>
            <a href="${pageContext.request.contextPath}/cartHandler">My Cart<i class='fas fa-cart-arrow-down'></i></a>
            <a href="${pageContext.request.contextPath}/myOrdersHandler">My Orders</a>
            <a href="${pageContext.request.contextPath}/detailsView">Change Details</a>
            <a href="messageUs.jsp">Message Us <i class='fas fa-comment-alt'></i></a>
            <a href="about.jsp">About <i class="fa fa-address-book"></i></a>
            <a href="${pageContext.request.contextPath}/logout">Logout
                <i class='fas fa-share-square'></i></a>
            <div class="search-container">
                <form action="${pageContext.request.contextPath}/clientProductView" method="get">
                    <label>
                        <input type="text" placeholder="search" name="search">
                    </label>
                    <label>
                        <button type="submit" >
                            <i class="fa fa-search"></i>
                        </button>
                    </label>
                </form>
            </div>
    </div>
           <br>
</body>
</html>

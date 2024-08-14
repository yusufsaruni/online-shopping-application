
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>welcome</title>
    <%@include file="adminHeader.jsp"%>
    <link rel="stylesheet" href="css/adminHome.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
   <div class="admin-home">
     <h1 class="welcome-admin">Welcome, ${sessionScope.email}</h1>
   </div>
    <footer class="footer">
        <p>All Right Reserved @ HarmoKE</p>
    </footer>
</body>
</html>
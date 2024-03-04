<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Error page</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">

</head>
<body>


   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />

   <div class="page-title">Error page!</div>

   <h3 style="color:red;">Sorry, you can not access this page!</h3>


   <jsp:include page="_footer.jsp" />

</body>
</html>
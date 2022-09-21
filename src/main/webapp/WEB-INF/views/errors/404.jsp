<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 2022/09/21
  Time: 10:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
에러 페이지
<c:out value="${requestScope['javax.servlet.error.status_code']}"/>
</body>
</html>

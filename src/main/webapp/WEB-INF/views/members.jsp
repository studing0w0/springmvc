
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
        <c:forEach var="i" items="${members}">
            <tr>
                <td>${i.id}</td>
                <td>${i.username}</td>
                <td>${i.age}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>

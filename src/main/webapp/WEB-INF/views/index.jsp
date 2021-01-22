<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Car accident</title>
</head>
<body>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${str}" var="name">
        <tr>
            <td>
                <c:out value="${name}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
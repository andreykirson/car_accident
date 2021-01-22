<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Accident</title>
</head>
<body>
Hello : ${user}

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
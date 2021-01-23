<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Car accident</title>
</head>
<body>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Address</th>
        <th scope="col">Description</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accidents}" var="accident">
        <tr>
            <td>
                <c:out value="${accident}"/>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
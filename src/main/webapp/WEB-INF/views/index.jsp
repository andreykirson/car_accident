<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Car accident</title>
</head>
<body>

<a href="<c:url value='/create'/>">Add accident</a>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Address</th>
        <th scope="col">Description</th>
        <th scope="col">Rules</th>
        <th scope="col">Item</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accidents}" var="accident">
        <tr>
            <td>
                <c:out value="${accident.name}"/>
                </span>
                <span>
             <a href="<c:url value='/update?id=${accident.id}'/>">Edit accident</a>
        </span>
            </td>
            <td>
                <c:out value="${accident.address}"/>
            </td>
            <td>
                <c:out value="${accident.text}"/>
            </td>
            <td>
                <c:out value="${accident.rule.name}"/>
            </td>
            <td>
                <c:out value="${accident.accidentType.name}"/>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type='text' name='name' value="${accident.name}"></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input type='text' name='text' value="${accident.text}"></td>
            <td>Address:</td>
            <td><input type='text' name='name' value="${accident.address}"></td>
        </tr>

        <td>Type:</td>
        <tr>
            <select name="type.id">
                <c:forEach var="type" items="${types}" >
                    <option value="${type.id}">${type.name}</option>
                </c:forEach>
            </select>
        </tr>

        <tr>
            <td>Item:</td>
            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
        </tr>

        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Save" /></td>
        </tr>
    </table>
</form>
</body>
</html>
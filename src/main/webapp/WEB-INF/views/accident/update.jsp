<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<form action="<c:url value='/save?id=${accident.getAccidentId()}'/>" method='POST'>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type='text' name='name' value="${accident.accidentName} + ${accident.accidentId}"></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input type='text' name='text' value="${accident.accidentText}"></td>
            <td>Address:</td>
            <td><input type='text' name='name' value="${accident.accidentAddress}"></td>
        </tr>

        <td>Type:</td>
        <tr>
            <select name="type.id">
                <c:forEach var="type" items="${types}" >
                    <option value="${type.typeId}">${type.typeName}</option>
                </c:forEach>
            </select>
        </tr>

        <tr>
            <td>Item:</td>
            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.ruleId}">${rule.name}</option>
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
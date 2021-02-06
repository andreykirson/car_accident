<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form  action="<c:url value='/save'/>" method='POST'>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type='text' name='accidentName'></td>
        </tr>

        <tr>
            <td>Address:</td>
            <td><input type='text' name='accidentAddress'></td>
        </tr>

        <tr>
            <td>Description:</td>
            <td><input type='text' name='accidentText'></td>
        </tr>


        <tr>
            <select name="type.id">
                <c:forEach var="type" items="${types}" >
                    <option value="${type.typeId}">${type.typeName}</option>
                </c:forEach>
            </select>
        </tr>

        <tr>
            <td>Items:</td>
            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.ruleId}">${rule.ruleName}</option>
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

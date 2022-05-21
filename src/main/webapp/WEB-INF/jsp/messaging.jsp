<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Messaging</title>
</head>
<body>
<h1>Messaging with ${otherUser.first_name} ${otherUser.last_name}</h1>
<div style="width: 50%; border: 1px solid darkblue">
    <table style="width: 100%">
        <c:forEach items="${exchangeMessages}" var="message">
            <tr style="
                <c:choose>
                    <c:when test="${message.user.userId == otherUser.userId}">
                            text-align: left;
                    </c:when>
                    <c:otherwise>
                            text-align: right;
                    </c:otherwise>
                </c:choose>">
                <th>
                    ${message.content}
                </th>
            </tr>
        </c:forEach>
    </table>
</div>
<div style="margin-top: 1%; margin-left: 25%">
    <form action="/messaging/${exchangeId}/send" method="POST">
        <td><input type="text" name="messageContent" size="20" default="Send my message"/></td>
        <td><input type="submit" name="action" value="Send"/></td>
    </form>
</div>
</body>
</html>
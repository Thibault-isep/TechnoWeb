<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My messagings</title>
</head>
<body>
<h1>List of my messagings</h1>
<div>
    <c:choose>
        <c:when test="${listOfExchanges.size() == 0}">
            You haven't any messaging yet
            <br/>
        </c:when>
        <c:otherwise>
            <table>
                <c:forEach items="${listOfExchanges}" var="exchange">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${exchange.user1 == user}">
                                    Messaging with ${exchange.user2.first_name} ${exchange.user2.last_name}
                                </c:when>
                                <c:otherwise>
                                    Messaging with ${exchange.user1.first_name} ${excchange.user1.last_name}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><form action="/messaging/${exchange.exchangeId}" method="GET">
                            <input type="submit" name="seeExchange" value="See the exchange"/>
                        </form></td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
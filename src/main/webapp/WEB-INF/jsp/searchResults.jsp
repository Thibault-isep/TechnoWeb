<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
</head>
<body>
<p id="test"></p>
    <h1>Recherche :</h1>
    <input type="text" name="searchBar" size="40" id="searchBar" placeholder="Where do you want to go?" value="${userSearch}"/>
    <br>
<br>
<br>
<br>
<div>
    <c:choose>
        <c:when test="${habitations.size() == 0}">
            No result found for you search
            <br/>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>ID Habitation</th>
                    <th>Salle de bain</th>
                    <th>Ville</th>
                    <th>Type</th>
                    <th></th>
                </tr>
                <c:forEach items="${habitations}" var="h">
                    <tr id="${h.habitationId}">
                        <td>${h.habitationId}</td>
                        <td>${h.bathrooms}</td>
                        <td>${h.city}</td>
                        <td>${h.type}</td>
                        <td>
                            <form action="/habitation/${h.habitationId}" method="POST"><input type="submit"
                                                                                              name="seeHabitation"
                                                                                              value="See the habitation"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
<script>
    class habit {
        getcity() {
            return this.city;
        }

        setcity(value) {
            this.city = value;
        }
        gettype() {
            return this.type;
        }

        settype(value) {
            this.type = value;
        }

        sethabitationId(value) {
            this.habitationId = value;
        }

        gethabitationId() {
            return this.habitationId;
        }

        getUserId() {
            return this.userId;
        }

        setUserId(value) {
            this.userId = value;
        }

        constructor(type, city, habitationId, userId) {
            this.habitationId = habitationId;
            this.type = type;
            this.city = city;
            this.userId = userId;
        }
    }
    const habitation = [];
    <c:forEach items="${habitations}" var="h">
    habitation.push(new habit("<c:out value="${h.city}"/>", "<c:out value="${h.type}"/>", "<c:out value="${h.habitationId}"/>", "<c:out value="${h.user.userId}"/>"));
    </c:forEach>
    const searchBar = document.getElementById("searchBar");
    searchBar.addEventListener('keyup', (e) => {
        const target = e.target.value;
        const filteredHabits = habitation.filter( habitation => {
            return habitation.type.toLowerCase().includes(target.toLowerCase()) || habitation.city.toLowerCase().includes(target.toLowerCase());
        });

        for (let i = 0; i < habitation.length; i++) {
            if (!filteredHabits.includes(habitation[i])) {
                document.getElementById(habitation[i].gethabitationId()).style.display = 'none';
            } else {
                document.getElementById(habitation[i].gethabitationId()).style.display = '';
                if ("<c:out value="${sessionScope.userId}"/>" == habitation[i].userId) {
                    document.getElementById(habitation[i].gethabitationId()).style.display = 'none';
                }
            }
        }
    })
    searchBar.dispatchEvent(new KeyboardEvent('keyup', {'key': 'a'}));
</script>
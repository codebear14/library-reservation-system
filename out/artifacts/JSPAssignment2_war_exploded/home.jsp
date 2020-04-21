<%--
  Created by IntelliJ IDEA.
  User: satya
  Date: 4/20/2020
  Time: 7:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="models.UserModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library Catalog</title>
    <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>

    <jsp:include page="sessionCheck.jsp"></jsp:include>
    <jsp:include page="navbarloggedin.jsp"></jsp:include>


<%
    UserModel user = (UserModel) session.getAttribute("user");
%>

    <div>
        <h1 style="margin-left: 20px">Hello, <%= user.getFirstName() + " " + user.getLastName() + " (" + user.getUserName() + ")"%></h1>
    </div>

    <div class="reserveBook">

        <form action="FetchBooksServlet" method="post">
            <span class="custom-select">
                <select name="topic">
                    <option value="all">All XML & JS Books</option>
                    <option value="xml">XML Books</option>
                    <option value="jsp">JSP Books</option>
                </select>
            </span>

            <input type="submit" name="search" value="Search">

        </form>

        <div id="bookTable">
            <table>
                <tr>
                    <th>Book</th>
                    <th>Topic</th>
                    <th>Author</th>
                    <th>Availability</th>
                    <th>Action</th>
                </tr>

                <c:forEach items="${listOfBooks}" var="book">
                    <tr>
                        <td>${book.getBookName()}</td>
                        <td>${book.getTopicName()}</td>
                        <td>${book.getAuthorName()}</td>
                        <c:choose>
                            <c:when test="${book.getBookAvailability()==1}">
                                <td>Available</td>
                                <td><a href="reserve.jsp?id=${book.getBookId()}">Reserve</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>Unavailable</td>
                                <td>Cannot Reserve</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>

</body>
</html>

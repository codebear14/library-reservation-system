<%--
  Created by IntelliJ IDEA.
  User: satya
  Date: 4/20/2020
  Time: 1:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="models.EncryptPassword" %>
<%@ page import="models.UserModel" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Library Catalog</title>
    <link rel="stylesheet" type="text/css" href="home.css">
  </head>

  <body>
    <%
      if(session.getAttribute("user")!=null){
        //System.out.print("I'm alive");
        response.sendRedirect("home.jsp");
        // Making a few changes! When the user is already logged in and if he tries to access index.jsp he will be routed to home.jsp
        // This is something I have seen in many websites like Facebook or Instagram
        // If you try accessing the log in page you are routed back to your account
        // This ensures sessions are maintained correctly and ovewriting of their states is not done unncecssarily
        // As per the specs if we need to go back to index.jsp I'll need to remove this block of jsp statements
      }
      else{
        //System.out.print("I'm dead");
      }
    %>

    <jsp:include page="navigationbar.jsp"></jsp:include>

    <div>
      <h1 style="margin-left: 20px">Hello</h1>
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
        <input type="hidden" name="page" value="indexPage">
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

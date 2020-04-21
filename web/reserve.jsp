<%@ page import="models.UserModel" %>
<%@ page import="services.MySQLDb" %>
<%@ page import="models.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: satya
  Date: 4/21/2020
  Time: 5:18 AM
  To change this template use File | Settings | File Templates.
--%>
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
        int userId = user.getUserId();
        MySQLDb db = MySQLDb.createInstance();

        if(request.getParameter("id") != null){
            int bookId = Integer.parseInt(request.getParameter("id"));

            //System.out.println("UserID : " + userId + " BookId : " + bookId);

            boolean bookReserved = db.reserveBook(userId, bookId);

            if(bookReserved){
                System.out.println("Done");
            }
            else{
                System.out.println("Error");
            }
        }

        List<Book> books = db.fetchReservedBooks(userId);
    %>

    <div>
        <h1 style="margin-left: 20px">Hello, <%= user.getFirstName() + " " + user.getLastName() + " (" + user.getUserName() + ")"%></h1>
    </div>

    <div id="bookTable" style="padding: 20px;">
        <table>
            <tr>
                <th>Book Name</th>
                <th>Author</th>
            </tr>

            <%
                for (int i=0; i<books.size(); i++){
            %>
                <tr>
                    <td><%= books.get(i).getBookName()%></td>
                    <td><%= books.get(i).getAuthorName()%></td>
                </tr>
            <%
                }
            %>
        </table>
    </div>

</body>
</html>

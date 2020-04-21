<%--
  Created by IntelliJ IDEA.
  User: satya
  Date: 4/20/2020
  Time: 7:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();
    response.sendRedirect("index.jsp");
%>

<%--
  Created by IntelliJ IDEA.
  User: satya
  Date: 4/20/2020
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = "Please login to continue";
    String redirect = "login.jsp";
    if(session.getAttribute("user") == null){
        request.setAttribute("error",error);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
        requestDispatcher.forward(request, response);
    }
%>

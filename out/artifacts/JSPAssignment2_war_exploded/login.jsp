<%--
  Created by IntelliJ IDEA.
  User: satya
  Date: 4/20/2020
  Time: 2:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library Catalog</title>
</head>

<style>
    body {font-family: Arial, Helvetica, sans-serif;}
    * {box-sizing: border-box}

    input[type=text], input[type=password] {
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }

    input[type=text]:focus, input[type=password]:focus {
        background-color: #ddd;
        outline: none;
    }

    hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
    }

    button {
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 100%;
        opacity: 0.9;
    }

    button:hover {
        opacity:1;
    }

    .center{
        margin-top: 20px;
        width: 60%;
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        padding: 16px;
    }

</style>

<body>

    <div>
        <jsp:include page="navigationbar.jsp"></jsp:include>
    </div>

    <div class="center">
        <form action="LoginServlet" method="post">
            <h2>Login</h2>

            <br/><label><b>Username</b></label><br/>
            <input type="text" placeholder="Enter Username" name="userName" required>

            <br/><label><b>Password</b></label><br/>
            <input type="password" placeholder="Enter Password" name="password" required>

            <button type="submit">Login</button>
        </form>

        <span style="color: red;">${error}</span>
    </div>

</body>
</html>

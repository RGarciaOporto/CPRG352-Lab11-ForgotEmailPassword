<%-- 
    Document   : reset
    Created on : Nov 27, 2021, 1:17:39 PM
    Author     : 851649
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Request Page</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your email address to reset your password.</p>
        <form action="reset" method="post">
        <label>Email Address: </label>
        <input type="text" name="emailAddress">
        <input type="submit" value ="submit">
        </form>
    </body>
</html>

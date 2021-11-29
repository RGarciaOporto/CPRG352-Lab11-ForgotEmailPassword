<%-- 
    Document   : resetNewPassword
    Created on : Nov 27, 2021, 2:22:40 PM
    Author     : 851649
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Note App Password Reset</h1>
        <h2>Enter a new Password</h2>
        <form action="reset" method="post">
            <input  type="hidden" name="resetPassword" value ="newPassword">
        <input type="hidden" name="UUID" value ="${uuidParam}">
        <input type="text" name="newPassword">
        <input type="submit"> 
        </form>
        <p>${message}</p>
    </body>
</html>

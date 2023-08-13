<%-- 
    Document   : paymentAnalysis
    Created on : Mar 6, 2023, 9:37:54 AM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ManagerController" method="POST">
            <label for="date">Select a date:</label>
            <input type="date" id="date" name="date">
            <input type="hidden" name="action" value="postDailyAnalysis">
            <p><input type="submit" value="Submit Edit"></p>
          </form>
    </body>
</html>

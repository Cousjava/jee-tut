<%-- 
    Document   : testjsp
    Created on : 02-May-2017
    Author     : jonathan coustick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        Current time is: <%= new java.util.Date() %>
        <% out.print("Hello, Sample JSP code"); %>
        <% out.println("More text!");
        

        %>
        
    </body>
</html>

<%-- 
    Document   : inheritance
    Created on : 02-May-2017, 16:09:15
    Author     : jonathan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page extends="com.sleightholme.jeetut.util.ExtendedJspServlet" %>
<%
  title = "Inheritance JSP Test";
  header(out);
  out.println("This is using a JSP Page extending from a servlet.");
  
  
  //footer();
%>
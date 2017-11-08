package com.sleightholme.jeetut.security;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
@WebServlet("/Login")
public class Login extends ExtendedServlet {

    private String togoto;
    
    @Override
    public void init(){
        title = "Login for Security Example";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        header();
        togoto = root + "/UserPage";
        printForm();
        
        footer();
    }

    
    private void printForm(){
        out.println("<form  action=\"UserPage\" method=\"POST\">");
        out.println("<input type=\"hidden\" name=\"login\" value=\"login\">");
        out.println("Username: <input type=\"text\" name=\"username\"><p>");
        out.println("Password: <input type=\"password\" name=\"password\"> <p>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doGet(request, response);
    }
    
}

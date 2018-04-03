package com.sleightholme.jeetut.oauth;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan
 */
@WebServlet(name="SecuredPage", urlPatterns={"/SecuredPage"})
@ServletSecurity(@HttpConstraint(rolesAllowed="users.normal"))
public class SecuredPage extends ExtendedServlet {

    @Override
    public void init(){
        title="OAuth Secured Page";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out.println("This page is secured with OAuth security");
        
    } 

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

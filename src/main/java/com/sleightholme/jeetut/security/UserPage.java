package com.sleightholme.jeetut.security;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import javax.annotation.security.DeclareRoles;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
@DeclareRoles("users.normal")
@ServletSecurity(@HttpConstraint(rolesAllowed="users.normal"))
@WebServlet(name = "UserPage", urlPatterns = {"/UserPage"})
public class UserPage extends ExtendedServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        header();
        
        out.println("You have successfully logged in!");        
        footer();
    }


}

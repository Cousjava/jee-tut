package com.sleightholme.jeetut.validation;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author jonathan coustick
 */
@WebServlet(name = "Form", urlPatterns = {"/Validation"})
public class Form extends ExtendedServlet {

    @Inject
    Validator validator;
    
    @Override
    public void init() throws ServletException{
        super.init();
        title = "Validation";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        header();
        printForm();
        
        footer();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        header();
        if (request.getParameter("submitted") != null){
            processForm();
            out.println("<br>");
        }
        printForm();
        footer();
    }
    
    private void printForm(){
        out.println("<h2>Bean validated form</h2>");
        out.println("<form action=\"\" method=\"POST\">");
        out.println("Positive int <input type=\"text\" name=\"number\"><br>");
        out.println("Name <input type=\"text\" name=\"name\"><br>");
        out.println("Email <input type=\"email\" name=\"email\"><br>");
        out.println("A future date<input type=\"text\" name=\"date\"><br>");
        out.println("Address, Max 100 characters<input type=\"textarea\" name=\"address\"><br>");
        out.println("<input type=\"hidden\" name=\"submitted\" value=\"done\">");
        out.println("<input type=\"submit\">");
        out.println("</form>");
        
    }
    
    private void processForm(){
        TestedBean tb = new TestedBean();
        try {
            tb.setName(request.getParameter("name"));
        } catch (NumberFormatException e){
            out.println("Invalid int format: not an int");
        }
        tb.setEmail(request.getParameter("email"));
        tb.setId(Integer.parseInt(request.getParameter("number")));
        tb.setAddress(request.getParameter("address"));
        out.println("<h2>Results</h2>");
        Set<ConstraintViolation<TestedBean>> violations = validator.validate(tb);
        if (violations.isEmpty()){
            out.println("There are no constraint violations<br>");
        } else {
            for (ConstraintViolation wrong : violations){
                out.println(wrong.getMessage() + "<br>");
            }
        }
        out.println("id:" + tb.getId() + "<br>");
        out.println("name: " + tb.getName() + "<br>");
        out.println("email: " + tb.getEmail() + "<br>");
        out.println("address:" + tb.getAddress() + "<br>");
        
        
        
    }


    
    
}

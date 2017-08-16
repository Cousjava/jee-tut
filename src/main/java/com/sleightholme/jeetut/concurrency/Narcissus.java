package com.sleightholme.jeetut.concurrency;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.glassfish.enterprise.concurrent.internal.ManagedThreadPoolExecutor;;

/**
 *
 * @author jonathan coustick
 */
@WebServlet(name = "Narcissus", urlPatterns = {"/Concurrency/Narcissus"})
public class Narcissus extends ExtendedServlet {

    @Override
    public void init(){
        title = "Narcissus's Pool";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        header();
        
        
        
        ManagedThreadPoolExecutor mtp;
        
        
        
        footer();
    }

}

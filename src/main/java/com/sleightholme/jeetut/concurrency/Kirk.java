package com.sleightholme.jeetut.concurrency;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Phaser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
@WebServlet(name = "Kirk", urlPatterns = {"/Concurrency/Kirk"})
public class Kirk extends ExtendedServlet {

    @Override
    public void init() throws ServletException{
        super.init();
        title = "Kirk Phasers";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        header();
        out.println("<h2>Kirk uses Phasers</h2>");
        Phaser phaser = new Phaser();
        phaser.register();
        
        Enterprise enterpriseD = new Enterprise(phaser, out);
        Enterprise enterpriseE = new Enterprise(phaser, out);
        
        Thread tos = new Thread(enterpriseD, "TOS");
        Thread tng = new Thread(enterpriseE, "TNG");
        
        out.println("Ready to boldly go</br>");
        
        tos.start();
        tng.start();
        phaser.arriveAndAwaitAdvance();
        out.println("Seeking out new life</br>");
        phaser.arriveAndAwaitAdvance();
        out.println("Its dead Jim</br>");
        footer();
    }

}

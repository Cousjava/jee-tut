package com.sleightholme.jeetut.concurrency;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
@WebServlet(name = "Countdown", urlPatterns = {"/Concurrency/Countdown"})
public class Countdown extends ExtendedServlet {

    @Override
    public void init() throws ServletException{
        super.init();
        title = "The Final Countdown";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        header();
        
        try {
            countdown();
        } catch (InterruptedException ex) {
            out.println("Error with countdown latch: " + ex.getMessage());
            ex.printStackTrace(out);
        }
        
        
        footer();
    }
    
    private void countdown() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Thread first = new Thread(new Marathon(latch, out));
        Thread second = new Thread(new Marathon(latch, out));
        first.start();
        out.println("Kicked off first, about to kick off second + </br>");
        second.start();
        latch.await();
        out.println("Finished countdown");
    }
    
}

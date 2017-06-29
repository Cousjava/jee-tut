package com.sleightholme.jeetut.concurrency;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
@WebServlet(name = "CycleBarrier", urlPatterns = {"/Concurrency/CycleBarrier"})
public class CycleBarrier extends ExtendedServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        title = "Cycle Barrier";
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        header();

        out.println("<h2>Cyclic Barrier Example</h2>");
        
        CyclicBarrier cb = new CyclicBarrier(4);
        Thread first = new Thread(new Triathlon(out, cb), "First");
        Thread second = new Thread(new Triathlon(out, cb), "Second");
        Thread third = new Thread(new Triathlon(out, cb), "Third");
        out.println("Ready to start threads <br>");
        first.start();
        second.start();
        third.start();
        out.println("All three threads started");
        try {
            cb.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            ex.printStackTrace(out);
        }
        out.println("Reached mid<br>");
        cb.reset();
        
        
        footer();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}

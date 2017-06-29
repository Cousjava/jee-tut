package com.sleightholme.jeetut.concurrency;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Exchanger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
@WebServlet(name = "StockExchange", urlPatterns = {"/Concurrency/StockExchange"})
public class StockExchange extends ExtendedServlet {

    public void init() {
        title = "Exchanger Example";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        header();
        
        out.println("<h2>Exchanger Example</h2>");
        Exchanger exchanger = new Exchanger();
        Stock stock1 = new Stock("First Stock", 5);
        Stock stock2 = new Stock("Second stock", 8);
        
        StockBroker brok1 = new StockBroker(exchanger, out, stock1);
        StockBroker brok2 = new StockBroker(exchanger, out, stock2);
        
        Thread first = new Thread(brok1, "First");
        Thread second = new Thread(brok2, "Second");
        out.println("Ready to start threads<br>");
        first.start();
        second.start();
        out.println("Started threads<br>");
        
        out.println("Sometime not all threads complete by the time servlet finishes. Oh well.");
        footer();
    }

}

package com.sleightholme.jeetut.concurrency;

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
@WebServlet(name = "Concurency", urlPatterns = {"/Concurrency/","/Concurrency/Index"})
public class Index extends ExtendedServlet {

    @Override
    public void init() throws ServletException{
        super.init();
        title = "Concurrency";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        header();
        out.println("<h1>" + title + "</h1>");
        out.println("<p><a href=\"Countdown\">Countdown</a> - CountdownLatch example</p>");
        out.println("<p><a href=\"CycleBarrier\">CycleBarrier</a> - CyclicBarrier example");
        out.println("<p><a href=\"StockExchange\">StockExchange</a> - Exchange example</p>");
        
        
        footer();
    }

    

}

package com.sleightholme.jeetut.jms;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
@WebServlet(name = "JMSListings", urlPatterns = {"/JMSListings"})
public class JMSListings extends ExtendedServlet {

    @Inject
    JavaMessageConsumer message;
            
    @Override
    public void init(){
        title = "JMS Messages Recieved";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         header();
         out.println("<h1>" + title + "</h1>");
         out.println("<h2>Messages recieved via a queue:</h2>");
         out.println(message.queueMessageListings("<br>"));
         out.println("<h2>Messages recieved via a topic:</h2>");
         out.println(message.topicMessageListings("<br>"));
         footer();
    }

}

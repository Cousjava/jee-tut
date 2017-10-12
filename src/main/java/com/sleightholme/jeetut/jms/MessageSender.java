package com.sleightholme.jeetut.jms;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.CompletionListener;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;

/**
 *
 * @author jonathan coustick
 */
@WebServlet(name = "JMSSender", urlPatterns = {"/JMSSender"})
public class MessageSender extends ExtendedServlet {


    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Inject
    private JMSContext ctx;
    
    @Resource(lookup = "java:app/queue/firstQ")
    private Queue queue;
    
    
    @Override
    public void init(){
        title = "JMS Sender";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        header();
        if (request.getParameter("message") != null){
            String message = request.getParameter("message");
            if (ctx != null) {
            JMSProducer producer = ctx.createProducer();
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            producer.send(queue, message);
            out.println("Message sent!");
            } else {
                out.println("No JMSContext set!");
            }
            
        }
        out.append("<h1>Send JMS Message:</h1>");
        printForm();
        out.println("<p><a href=\"JMSListings\">List of recieved messages</a>");
        footer();
    }
    
    private void printForm(){
        out.println("<form>");
        out.println("<input type=\"text\" name=\"message\">");
        out.println("<input type=\"submit\" value=\"send\"></form>");
    }

}

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
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
@WebServlet(name = "JMSSender", urlPatterns = {"/JMSSender"})
public class MessageSender extends ExtendedServlet {

    private static final String QUEUE = "queue";
    private static final String TOPIC = "topic";

    @Inject
    private JMSContext ctx;
    
    @Resource(lookup = "java:app/queue/firstQ")
    private Queue queue;
    
    @Resource(lookup="java:app/queue/firstTopic")
    private Topic topic;
    
    @Override
    public void init(){
        title = "JMS Sender";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        header();
        if (request.getParameter(QUEUE) != null){
            String message = request.getParameter("message");
            if (ctx != null) {
                JMSProducer producer = ctx.createProducer();
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                producer.send(queue, message);
                out.println("Message sent to queue!");
            } else {
                out.println("No JMSContext set!");
            }
            
        } else if (request.getParameter(TOPIC) != null){
            TopicMessage message = new TopicMessage(request.getParameter("message"), this.getClass());
            JMSProducer producer = ctx.createProducer();
            
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            producer.send(topic, message);
            out.println("Message sent to topic!");
        }
        out.append("<h1>Send JMS Message via queue:</h1>");
        printForm(QUEUE);
        out.append("<h1>Send JMS Message via topic:</h1>");
        printForm(TOPIC);
        out.println("<p><a href=\"JMSListings\">List of recieved messages</a>");
        footer();
    }
    
    private void printForm(String name){
        out.println("<form>");
        out.println("<input type=\"text\" name=\"message\">");
        out.println("<input type=\"submit\" name=\"" + name + "\"value=\"send\"></form>");
    }

}

package com.sleightholme.jeetut.jms;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.Queue;
import org.jboss.logging.Logger;

/**
 *
 * @author jonathan coustick
 */
@ApplicationScoped
public class JavaMessageConsumer {
    
    @Inject
    private JMSContext ctx;
    
    @Resource(lookup="java:app/queue/firstQ")
    private Queue queue;
    
    private JMSConsumer consume;
    private final ArrayList<Message> messages;
    
    public JavaMessageConsumer(){
        messages = new ArrayList<Message>();
    }
    
    @PostConstruct
    public void postConstruct(){
        consume = ctx.createConsumer(queue);
    }
    
    public void consumeMessage() throws JMSRuntimeException {
        messages.add(consume.receive());
        Logger.getLogger("JMS Example").log(Logger.Level.ERROR, "Consumed message");
    }
    
    public ArrayList<Message> getMessages(){
        return messages;
    }
    
    public void addMessage(Message message){
        messages.add(message);
    }
    
    
    public String messageString(Message messsage, String newline) throws JMSException {
        StringBuilder result = new StringBuilder();
        if (messsage == null) {
            return "";
        }
        result.append("MessageID: ").append(messsage.getJMSMessageID()).append(newline);
        result.append("Message Type: ").append(messsage.getJMSType()).append(newline);
        result.append("Message: ").append(messsage.getBody(String.class)).append(newline);
        result.append("Destintation: ").append(messsage.getJMSDestination().toString()).append(newline);
        result.append("Delivery Mode: ").append(Integer.toString(messsage.getJMSDeliveryMode())).append(newline);
        result.append("Time: ").append(LocalDateTime.ofEpochSecond(messsage.getJMSTimestamp() / 1000, 0, ZoneOffset.UTC).toString());
        return result.toString();
    }
    
        public String messageListings(String newline) {
        StringBuilder result = new StringBuilder();
        for (Message messsage : getMessages()) {
            try {
                result.append(messageString(messsage, newline));
                result.append(newline);result.append(newline);

            } catch (JMSException ex) {
                java.util.logging.Logger.getLogger(JMSListener.class.getName()).log(Level.SEVERE, "Error reading message:" + messsage.toString(), ex);
            }
        }

        return result.toString();
    }
    
    
}

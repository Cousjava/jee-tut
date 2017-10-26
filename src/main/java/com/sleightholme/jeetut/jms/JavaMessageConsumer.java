package com.sleightholme.jeetut.jms;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 *
 * @author jonathan coustick
 */
@ApplicationScoped
public class JavaMessageConsumer {
    
    private final ArrayList<Message> queueMessages;
    private final ArrayList<Message> topicMessages;

    public JavaMessageConsumer() {
        queueMessages = new ArrayList<Message>();
        topicMessages = new ArrayList<Message>();
    }

    public ArrayList<Message> getQueueMessages() {
        return queueMessages;
    }

    public ArrayList<Message> getTopicMessages() {
        return topicMessages;
    }

    public void addTopicMessage(Message message) {
        topicMessages.add(message);
    }

    public void addQueueMessage(Message message) {
        queueMessages.add(message);
    }

    public String messageString(Message messsage, String newline) throws JMSException {
        StringBuilder result = new StringBuilder();
        if (messsage == null) {
            return "";
        }
        result.append("MessageID: ").append(messsage.getJMSMessageID()).append(newline);
        result.append("Message Type: ").append(messsage.getJMSType()).append(newline);       
        result.append("Destintation: ").append(messsage.getJMSDestination().toString()).append(newline);
        result.append("Delivery Mode: ").append(Integer.toString(messsage.getJMSDeliveryMode())).append(newline);
        result.append("Time: ").append(LocalDateTime.ofEpochSecond(messsage.getJMSTimestamp() / 1000, 0, ZoneOffset.UTC).toString());
        return result.toString();
    }

    public String queueMessageListings(String newline) {
        StringBuilder result = new StringBuilder();
        for (Message messsage : getQueueMessages()) {
            try {
                result.append(messageString(messsage, newline));
                result.append("Message: ").append(messsage.getBody(String.class)).append(newline);
                result.append(newline);
                result.append(newline);

            } catch (JMSException ex) {
                java.util.logging.Logger.getLogger(JMSListener.class.getName()).log(Level.SEVERE, "Error reading message:" + messsage.toString(), ex);
            }
        }

        return result.toString();
    }
    
    public String topicMessageListings(String newline){
        StringBuilder result = new StringBuilder();
        for (Message message: getTopicMessages()){
            try {
                TopicMessage data = message.getBody(TopicMessage.class);
                result.append(messageString(message, newline));
                result.append("Message: ").append(data.toString()).append(newline);
                result.append(newline);
            } catch (JMSException ex) {
                Logger.getLogger(JavaMessageConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result.toString();
    }

}

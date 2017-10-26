package com.sleightholme.jeetut.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author jonathan coustick
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/queue/firstTopic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class TopicListener1 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            message.acknowledge();
            Logger.getLogger("JMS Topic").log(Level.SEVERE, message.getBody(TopicMessage.class).toString());
        } catch (JMSException ex) {
            Logger.getLogger(TopicListener1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

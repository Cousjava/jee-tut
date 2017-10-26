package com.sleightholme.jeetut.jms;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author jonathan coustick
 */
@JMSDestinationDefinition(
        name = "java:app/queue/firstTopic",
        interfaceName = "javax.jms.Topic",
        destinationName = "firstTopic"
)
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/queue/firstTopic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class TopicListener2 implements MessageListener {

    @Inject
    JavaMessageConsumer jmc;
    
    private ArrayList<TopicMessage> messages;
    
    public TopicListener2(){
        messages = new ArrayList<TopicMessage>();
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            jmc.addTopicMessage(message);
            message.acknowledge();
        } catch (JMSException ex) {
            Logger.getLogger(TopicListener2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

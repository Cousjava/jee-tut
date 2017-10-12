package com.sleightholme.jeetut.jms;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author jonathan coustick
 */
@JMSDestinationDefinition(
        name = "java:app/queue/firstQ",
        interfaceName = "javax.jms.Queue",
        destinationName = "firstQ"
)
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/queue/firstQ")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
@Singleton
public class JMSListener implements MessageListener {

    @Inject
    JavaMessageConsumer consumer;

    @Override
    public void onMessage(Message message) {
        try {
            message.acknowledge();//Otherwise the thread hangs around forever
            Map<String, String> messageDetails = new LinkedHashMap<String, String>();
            messageDetails.put("MessageID", message.getJMSMessageID());
            messageDetails.put("Message Type", message.getJMSType());
            messageDetails.put("Message", message.getBody(String.class));
            messageDetails.put("Destintation", message.getJMSDestination().toString());
            messageDetails.put("Delivery Mode", Integer.toString(message.getJMSDeliveryMode()));

            Logger.getLogger("com.sleightholme.jms").log(Level.INFO, consumer.messageString(message, "\n"));
            //consumer.consumeMessage();
            consumer.addMessage(message);

        } catch (JMSException ex) {
            Logger.getLogger(JMSListener.class.getName()).log(Level.SEVERE, "Error processing JMS Message", ex);
        }
    }



}

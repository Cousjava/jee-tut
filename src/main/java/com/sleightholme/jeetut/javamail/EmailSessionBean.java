package com.sleightholme.jeetut.javamail;

import java.util.Date;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Session Bean implementation class EmailSessionBean
 */
@Stateless
@LocalBean
public class EmailSessionBean {

	public enum Protocol {
	    SMTP,
	    SMTPS,
	    TLS
	}
	
	private int port = 465;
	private String host = "staple.arvixe.com";
	private String from = "totalitea-noreply@totalitea.com";
	private boolean auth = true;
	private String username = "totalitea@sleightholme.com";
	private String password = "mip0Sf7VRB";
	private Protocol protocol = Protocol.SMTPS;
	private boolean debug = true;
    
    public void sendEmail(String to, String subject, String body){
    	Properties props = new Properties();
    	props.put("mail.smtp.host", host);
    	props.put("mail.smtp.port", port);
    	switch (protocol) {
    	    case SMTPS:
    	        props.put("mail.smtp.ssl.enable", true);
    	        break;
    	    case TLS:
    	        props.put("mail.smtp.starttls.enable", true);
    	        break;
    	}
    	Authenticator authenticator = null;
    	if (auth) {
    	    props.put("mail.smtp.auth", true);
    	    authenticator = new Authenticator() {
    	        private PasswordAuthentication pa = new PasswordAuthentication(username, password);
    	        @Override
    	        public PasswordAuthentication getPasswordAuthentication() {
    	            return pa;
    	        }
    	    };
    	}
    	
    	Session session = Session.getInstance(props, authenticator);
    	session.setDebug(debug);
    	
    	MimeMessage message = new MimeMessage(session);
    	try {
    	    message.setFrom(new InternetAddress(from));
    	    InternetAddress[] address = {new InternetAddress(to)};
    	    message.setRecipients(Message.RecipientType.TO, address);
    	    message.setSubject(subject);
    	    message.setSentDate(new Date());
    	    Multipart multipart = new MimeMultipart("alternative");


    	    MimeBodyPart textPart = new MimeBodyPart();
    	    String textContent = body;
    	    textContent = textContent.replace("</td><td>", ", ");
    	    textContent = textContent.replace("</tr>", "");
    	    textContent = textContent.replace("<tr>", "");
    	    
    	    textPart.setText(textContent);
    	    
    	    MimeBodyPart htmlPart = new MimeBodyPart();
    	    String htmlContent = body;
    	    htmlPart.setContent(htmlContent, "text/html");

    	    multipart.addBodyPart(textPart);
    	    multipart.addBodyPart(htmlPart);
    	    message.setContent(multipart);
    	    Transport.send(message);
    	} catch (MessagingException ex) {
    	    ex.printStackTrace();
            
    	}
    }
    
    @Override
    public String toString(){
        String ret = "EmailSessionBean[";
        ret += "host:" + host + ",";
        ret += "username:" + username + ",";
        ret += "protocol:" + protocol + ",";
        ret += "auth:" + auth + "]";
        return ret;
    }

}
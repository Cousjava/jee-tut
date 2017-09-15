package com.sleightholme.jeetut.javamail;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.mail.MailSessionDefinition;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
@MailSessionDefinition(name="java:global/mail/mailexample",
        host = "staple.arvixe.com",
        user="totalitea@sleightholme.com",
        password = "mip0Sf7VRB",
        from="totalitea-noreply@sleightholme.com",
        transportProtocol = "smtps",
        storeProtocol = "imap",
        properties = {
            "mail.smtp.auth=true",
            "mail.debug=true",
            "mail.smtp.port=465",
            "mail.smtp.ssl.enable=true"
        })
@WebServlet(name = "JavaMailExample", urlPatterns = {"/JavaMailExample"})
public class JavaMailExample extends ExtendedServlet {

    @EJB
    private EmailSessionBean emailBean;
    
    @Resource(lookup="java:global/mail/mailexample")
    Session mail;
    
    @Override
    public void init(){
        title = "JavaMail Example";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        header();
        out.println("<h1>JavaMail Example</h1>");
        printEJBDetails();
        printDefinedMailDetails();
        footer();
    }
    
    private void printEJBDetails(){
        out.println("<h3>EJB JavaMail infomation</h3>");
        out.println(emailBean.toString());
        emailBean.sendEmail("jonathan.coustick@payara.fish", "Email via Javamail", "JavaMail created using an EJB");
    }
    
    private void printDefinedMailDetails(){
        out.println("<h3>Mail Definition Details</h3>");
        try {
            out.println("Store:" + mail.getStore().toString());
            out.println(mail.toString());
            //mail.getTransport().sendMessage("Message sent with definition", new Message("jonathan.coustick@payara.fish"));
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(JavaMailExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

}

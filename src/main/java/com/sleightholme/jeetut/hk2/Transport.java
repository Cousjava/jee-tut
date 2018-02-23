package com.sleightholme.jeetut.hk2;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.api.messaging.Topic;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "Transport", urlPatterns = {"/Transport"})
public class Transport extends ExtendedServlet {

    private ServiceLocator habitat;
    
    private Topic<Arrival> arrivals;
    
    @Inject
    private Station station;
    
    @Override
    public void init(){
        title = "Transport";
        habitat = ServiceLocatorFactory.getInstance().create("HK2T");
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.println("<h1>HK2 Example</h1>");
        out.println("<p>Things in " + station.getName() + " Station:");
        printVehicles();
        FordAnglia anglia = new FordAnglia();
        out.println("A new Vehicle arrived - " + anglia.getName());
        arrivals.publish(new Arrival(anglia));
        out.println("Now in " + station.getName() + " Station:");
        out.println("</ul>");
        
    }

    private void printVehicles(){
        for (Vehicle vehicle : station.getAllVehicles()){
            out.println("<li>" + vehicle + "</li>");
        }
    }


}

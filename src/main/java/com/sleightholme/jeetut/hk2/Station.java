package com.sleightholme.jeetut.hk2;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import org.glassfish.hk2.api.InheritableThread;
import org.glassfish.hk2.api.IterableProvider;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.messaging.SubscribeTo;
import org.glassfish.hk2.api.messaging.Topic;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author jonathan
 */
@Service
@InheritableThread
public class Station {
    
    @Inject
    @Train
    private IterableProvider<Vehicle> trains; // Could just be Iterable and the result would be the same
    
    @Inject @Named("Car")
    private Vehicle car;
    
    @Inject @Named("Aeroplane")
    private Provider<Vehicle> plane;
    
    private ArrayList<Vehicle> present;
    private String name;
    
    @Inject
    ServiceLocator habitat;
    
    @PostConstruct
    public void postConstuct(){
        present = new ArrayList<>();
        name = "Shrub Hill";
        
    }
    
    public ArrayList<Vehicle> getAllVehicles(){
        present.add(car);
        for (Vehicle train: trains){
            present.add(train);
        }
        present.add(plane.get());
        return present;
    }

    public void Arrival(@SubscribeTo Arrival arrived){
        present.add(arrived.getVehicle());
    }
    
    public String getName(){
        return name;
    }
    
    
    
}

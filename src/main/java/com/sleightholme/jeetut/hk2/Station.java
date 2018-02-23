package com.sleightholme.jeetut.hk2;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import org.glassfish.hk2.api.IterableProvider;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author jonathan
 */
@Service
// Default scope is Singleton/Application Scoped
public class Station {
    
    @Inject
    @Train
    private IterableProvider<Vehicle> trains; // Could just be Iterable and the result would be the same
    
    @Inject @Named("Car")
    private Vehicle car;
    
    @Inject @Named("Aeroplane")
    private Provider<Vehicle> plane;
    
    @PostConstruct
    public void postConstuct(){
        car.getName();
    }
    
    public Vehicle getPlane(){
        return plane.get();
    }
    
    public Vehicle getSlowTrain(){
        for (Vehicle train: trains){
            if (train.getName().contains("T")){
                return train;
            }
        }
        return null;
    }
    
    
}

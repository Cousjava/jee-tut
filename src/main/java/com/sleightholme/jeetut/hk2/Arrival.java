package com.sleightholme.jeetut.hk2;

import java.time.LocalTime;

/**
 *
 * @author jonathan
 */
public class Arrival {
    
    private final Vehicle vehicle;
    private LocalTime time;
    
    public Arrival(Vehicle vehicle){
        this.vehicle = vehicle;
        time = LocalTime.now();
    }
    
    public Vehicle getVehicle(){
        return vehicle;
    }
    
    public LocalTime getTime(){
        return time;
    }
}

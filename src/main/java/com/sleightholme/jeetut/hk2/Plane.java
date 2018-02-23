package com.sleightholme.jeetut.hk2;

import javax.inject.Named;
import org.glassfish.hk2.runlevel.RunLevel;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author jonathan
 */
@Service
@Named("Aeroplane")
@RunLevel(11)
public class Plane implements Vehicle {

    @Override
    public String getName() {
        return "Boeing 767";
    }
    
}

package com.sleightholme.jeetut.hk2;

import javax.annotation.Priority;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author jonathan
 */
@Service
@Train
@Priority(102)
public class CityOfTruro implements Vehicle {

    @Override
    public String getName() {
        return "City of Truro";
    }
    
}

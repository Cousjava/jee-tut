package com.sleightholme.jeetut.hk2;

import javax.inject.Named;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author jonathan
 */
@Service
@Named
public class Car implements Vehicle {

    @Override
    public String getName() {
        return"Ford Prefect";
    }
    
}

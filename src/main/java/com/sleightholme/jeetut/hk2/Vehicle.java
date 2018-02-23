package com.sleightholme.jeetut.hk2;

import org.glassfish.hk2.api.PerThread;
import org.jvnet.hk2.annotations.Contract;

/**
 *
 * @author jonathan
 */
@Contract
@PerThread
public interface Vehicle {
    
    public String getName();
    
}

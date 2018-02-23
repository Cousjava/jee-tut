package com.sleightholme.jeetut.hk2;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author jonathan
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target( { TYPE, METHOD, FIELD, PARAMETER })
public @interface Train {
    
}

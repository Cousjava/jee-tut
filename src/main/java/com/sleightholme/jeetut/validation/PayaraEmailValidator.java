package com.sleightholme.jeetut.validation;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Target;


import javax.validation.constraints.Size;

/**
 *
 * @author jonathan coustick
 */
@Size(min=12)
@Retention(RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
public @interface PayaraEmailValidator{
    
    
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @interface List {
        PayaraEmailValidator[] value();
    }
    
}

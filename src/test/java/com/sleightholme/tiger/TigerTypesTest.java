package com.sleightholme.tiger;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.jvnet.tiger_types.Types;

/**
 *
 * @author jonathan
 */
public class TigerTypesTest {
    
    @Test
    @Ignore
    public void tigerTest() {
        List<String> list = Collections.emptyList();
        Type basicType = list.getClass().getGenericSuperclass();
        Class<?> erasuredType = Types.erasure(basicType);
        System.out.println(basicType);
        System.out.println(erasuredType);
        System.out.println(basicType.getClass());
        Assert.assertTrue(erasuredType.equals(basicType.getClass()));
    }
}

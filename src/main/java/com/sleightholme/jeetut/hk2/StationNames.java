package com.sleightholme.jeetut.hk2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author jonathan
 */
@Service
public class StationNames {

    private List<String> names;
    private Random random;

    @PostConstruct
    public void postConstruct(){
        random = new Random();
        names = new ArrayList<>();
        names.add("Shrub Hill");
        names.add("Foregate");
        names.add("Waverly");
        names.add("Corrour");
    }

    public String getName(){
        if (names.isEmpty()) {
            throw new NoNamesException();
        }
        int pos = random.nextInt(names.size());
        return names.get(pos);
    }
    
    public void addName(String name){
        names.add(name);
    }

    public class NoNamesException extends RuntimeException{
        
        public NoNamesException(){
            super("No names left!");
        }
    }

}

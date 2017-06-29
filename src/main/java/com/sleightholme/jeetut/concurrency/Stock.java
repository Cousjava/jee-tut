package com.sleightholme.jeetut.concurrency;

/**
 *
 * @author jonathan coustick
 */
public class Stock {

    private String name;
    private int value;

    public Stock(){    }
    
    public Stock(String name, int value){
        this.name = name;
        this.value = value;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
    
}

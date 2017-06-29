package com.sleightholme.jeetut.concurrency;

import java.io.PrintWriter;
import java.util.concurrent.Exchanger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan coustick
 */
public class StockBroker implements Runnable {

    private Exchanger exchanger;
    private Stock toexchange;
    private PrintWriter out;;
    
    public StockBroker(Exchanger exchanger, PrintWriter out, Stock trade){
        this.exchanger = exchanger;
        this.out = out;
        this.toexchange = trade;
    }
    
    @Override
    public void run() {
        String message = "Stock is " + toexchange.getName() + " with value of " + toexchange.getValue() + "; in thread " + Thread.currentThread().getName() + "<br>";
        out.println(message);
        try {
            toexchange = (Stock) exchanger.exchange(toexchange);
        } catch (InterruptedException ex) {
            Logger.getLogger(StockBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        String message2 = "Stock is " + toexchange.getName() + " with value of " + toexchange.getValue() + "; in thread " + Thread.currentThread().getName() + "<br>";
        out.println(message2);
    }
    
}

package com.sleightholme.jeetut.concurrency;

import java.io.PrintWriter;
import java.util.concurrent.Phaser;

/**
 *
 * @author jonathan coustick
 */
public class Enterprise implements Runnable {

    private Phaser ph;
    private PrintWriter out;
    
    public Enterprise(Phaser phaser, PrintWriter out){
        ph = phaser;
        ph.register();
        this.out = out;
    }
    
    @Override
    public void run() {
        makeitso(1);
        ph.arriveAndAwaitAdvance();
        makeitso(2);
        ph.arriveAndDeregister();
    }
    
    private void makeitso(int order){
        for (int i = 1; i <= 10; i++){
            String message = Thread.currentThread().getName() + " Series " + order + " EP " + i + "</br>";
            out.println(message);
        }
        
    }
    
}

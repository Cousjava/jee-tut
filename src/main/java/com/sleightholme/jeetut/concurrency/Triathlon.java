package com.sleightholme.jeetut.concurrency;

import java.io.PrintWriter;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author jonathan coustick
 */
public class Triathlon implements Runnable {

    private CyclicBarrier barrier;
    private PrintWriter out;
    
    public Triathlon(PrintWriter out, CyclicBarrier barrier){
        this.out = out;
        this.barrier = barrier;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++){
                String message = "Triathlon " + Thread.currentThread().getName() + " on " + i + " pre-barrier </br>";
                out.println(message);
            }
            barrier.await();
            out.println("</br>");
            
            for (int i = 0; i < 10; i++){
                String message = "Triathlon " + Thread.currentThread().getName() + " on " + i + " post-barrier </br>";
                out.println(message);
            }
        } catch (InterruptedException | BrokenBarrierException ex) {
            ex.printStackTrace(out);
        }
    }
    
}

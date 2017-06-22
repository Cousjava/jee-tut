package com.sleightholme.jeetut.concurrency;

import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author jonathan coustick
 */
public class Marathon implements Runnable {
    
    private CountDownLatch finished;
    private PrintWriter out;
    private int id;
    
    private static int made = 0;
    
    public Marathon(CountDownLatch finishSignal, PrintWriter out){
        finished = finishSignal;
        this.out = out;
        made++;
        id = made;
    }

    @Override
    public void run() {
        try {
            doWork();
        } catch (InterruptedException ex) {
            ex.printStackTrace(out);
        }
        finished.countDown();
    }
    
    private void doWork() throws InterruptedException{
        for (int i = 1; i <= 10; i++){
            out.print(id + " : " + i + " is ");
            /*if (!isPrime(i)){
                out.print("not ");
            }
            out.print("prime.</br>");*/
        }
    }
    
    private boolean isPrime(int in){
        String strIn = Integer.toString(in);
        if (in < 2){
            return false;
        } else if (in == 2){
            return true;
        } else if (in % 2 == 0){
            return false;
        } else if (in == 3){
            return true;
        } else if (strIn.endsWith("0") || strIn.endsWith("5")){
            return false;
        } else if (digitSum(strIn) % 3 == 0){
            return false;
        } else {
            return euclideanSieve(in);
        }
        
        
    }
    
    private int digitSum(String in){
        char[] digits = in.toCharArray();
        int sum = 0;
        for (char digit : digits){
            int value = Integer.parseInt(Character.toString(digit));
            sum += value;
        }
        return sum;
    }
    
    private boolean euclideanSieve(int in){
        int root = (int) Math.sqrt(in);
        for (int i = 7; i <=root; i+=2){
            if (in % i == 0){
                return false;
            }
        }
        return true;
    }
    
}

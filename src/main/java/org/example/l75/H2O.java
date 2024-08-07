package org.example.l75;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class H2O {
    private Integer hCount=0;
    private Integer oCount = 0;
    private CyclicBarrier cyclicBarrier;

    private Lock hLock, oLock;
    public H2O() {
        cyclicBarrier = new CyclicBarrier(2);
        hLock = new ReentrantLock();
        oLock = new ReentrantLock();
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        hLock.lock();
        try {
            releaseHydrogen.run();
            hCount++;
            if (hCount == 2) {
                cyclicBarrier.await();
                hCount=0;
            }
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } finally {
            hLock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        oLock.lock();
        try {
            releaseOxygen.run();
            oCount++;
            if (oCount == 1) {
                cyclicBarrier.await();
                oCount=0;
            }
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } finally {
            oLock.unlock();
        }
    }

    public static void main(String[] args) {
        var h2O = new H2O();
        Arrays.stream("OOHHHH".chars().toArray()).parallel().forEach( c -> {
            if(c == 'H') {
                try {
                    h2O.hydrogen(() -> System.out.print("H"));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                try {
                    h2O.oxygen(() -> System.out.print("O"));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
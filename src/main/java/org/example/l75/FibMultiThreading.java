package org.example.l75;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;


// 8 (8-1)7 (8-2)6 | (7-1)6 (7-2)5 | (6-1)5 (6-2)4 | (5-1)4 (5-2)3

public class FibMultiThreading extends RecursiveTask<Long> {
    private final Long x;
    private final AtomicLongArray cache;


    public FibMultiThreading(Long x, AtomicLongArray cache) {
        this.x = x;
        this.cache = cache;
    }

    public static void main(String[] args) {
        long inputNum = 7999;
        var tp = ForkJoinPool.commonPool();
        var cache = new AtomicLongArray((int) inputNum);
        FibMultiThreading f = new FibMultiThreading(inputNum, cache);
        var answer = tp.invoke(f);
        System.out.println("Fibonacci number at " + inputNum + " position is:" + answer);
    }

    @Override
    protected Long compute() {
        long answer;
        if (x == 0) {
            answer = 0;
        } else if (x == 1 || x == 2) {
            answer = 1;
        } else {
            /*
             * Below we are invoking 2 threads to compute separate values
             * This will for a tree structure
             */
            FibMultiThreading f1 = null;
            FibMultiThreading f2 = null;
            var fAns = 0l;
            var sAns = 0l;
            if (cache.get((int) (x - 1)) == 0) {
                f1 = new FibMultiThreading(x - 1, cache);
            }
            if (cache.get((int) (x - 1)) == 0) {
                f2 = new FibMultiThreading(x - 2, cache);
            }

            if (f1 != null && f2 != null) {
                invokeAll(f1,f2);
                try {
                    fAns = f1.get();
                    sAns= f2.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
                cache.set(Math.toIntExact(x - 1), fAns);
                cache.set(Math.toIntExact(x - 2), sAns);
                answer = fAns + sAns;
            } else {
                answer = cache.get(Math.toIntExact(x - 1)) + cache.get(Math.toIntExact(x - 2));
            }
        }
        return answer;
    }
}
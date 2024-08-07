package org.example.l75;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintInOrder {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        var conditionA = lock.newCondition();
        var conditionB = lock.newCondition();
        var conditionC = lock.newCondition();
        Thread t1 = new Thread(new Printer("A", lock,conditionA, List.of(conditionB,conditionC)));
        Thread t2 = new Thread(new Printer("B", lock,conditionB,List.of(conditionC,conditionA)));
        Thread t3 = new Thread(new Printer("C", lock,conditionC,List.of(conditionA,conditionB)));
        t1.setDaemon(false);
        t2.setDaemon(false);
        t3.setDaemon(false);
        t1.start();
        t2.start();
        t3.start();
    }
}

class Printer implements Runnable {

    private final Lock lock;
    private final Condition condition;
    private final List<Condition> conditionList;
    private final String name;
    private static String toPrintNext = "A";
    private static int i = 0;


    Printer(String name, Lock lock, Condition condition, List<Condition> conditions) {
        this.name = name;
        this.lock = lock;
        this.condition = condition;
        this.conditionList = conditions;
    }

    @Override
    public void run() {

        while (i < 30) {
            lock.lock();
            if(!Objects.equals(name, toPrintNext)){
                   try {
                       condition.await();
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }else{
                   System.out.print(name);
                   if(Objects.equals(name, "A")){
                       toPrintNext = "B";
                   }else if(Objects.equals(name, "B")){
                       toPrintNext = "C";
                   }else{
                       toPrintNext = "A";
                   }
                   conditionList.forEach(Condition::signal);
                   try {
                       condition.await();
                   } catch (InterruptedException e) {
                           throw new RuntimeException(e);
                   }
               }
            conditionList.forEach(Condition::signalAll);
            lock.unlock();
            i++;
        }
    }
}

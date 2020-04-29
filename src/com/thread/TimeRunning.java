package com.thread;

import com.Time;

public class TimeRunning extends Thread {

    private Thread next;

    public TimeRunning() {
        this.start();
    }

    public void setNextThread(Thread next){
        this.next = next;
    }

    @Override
    public void run() {
        do{

            synchronized (this){
                try{
                    this.wait(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for(int i = 2; i >= 1 ; i--){
                Time.setNow(Time.getNow().plusDays(1));
                try {
                    sleep(5_000);
                } catch (InterruptedException e) {
                    return;
                }
            }

            if(next != null){
                synchronized (next){
                    next.notify();
                }
            }

        }while (!Thread.currentThread().isInterrupted());
    }
}

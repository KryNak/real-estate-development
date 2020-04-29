package com.thread;

import com.Time;
import com.construction.Pomieszczenie;

import java.util.List;

public class CheckPlaces extends Thread {

    private List<Pomieszczenie> pomieszczenieList;
    private TimeRunning next;

    public CheckPlaces(List<Pomieszczenie> pomieszczenieArrayList) {
        this.pomieszczenieList = pomieszczenieArrayList;
        this.start();
    }

    public void setNextThread(TimeRunning next) {
        this.next = next;
    }

    @Override
    public void run() {
        do{
            synchronized (this){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    return;
                }
            }

            for (Pomieszczenie p: pomieszczenieList) {
                p.setNowTime(Time.getNow());
            }

            if(next != null){
                synchronized (next){
                    next.notify();
                }
            }

        }while (!Thread.currentThread().isInterrupted());
    }
}

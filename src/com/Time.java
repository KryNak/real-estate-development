package com;

import java.time.LocalDate;

public class Time {
    static LocalDate now;



    public static LocalDate getNow() {
        synchronized (Time.class){
            return now;
        }
    }

    public static void setNow(LocalDate now) {
        synchronized (Time.class){
            Time.now = now;
        }
    }
}

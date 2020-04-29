package com;

import com.construction.Pomieszczenie;

public class RoomBox<T extends Pomieszczenie> {
    private T room;

    public RoomBox(T room){
        this.room = room;
    }

    public T getRoom() {
        return room;
    }
}

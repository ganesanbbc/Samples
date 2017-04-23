package main.airline.logic;

import main.airline.model.Seat;

import java.util.List;

public interface Allocator {

    int checkAvailability(int i);

    List<Seat> allocateSeat(int requiredSeat, List<String> seatRequired);
}

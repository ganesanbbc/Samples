package main.airline;

import java.util.List;

public interface AirLineBooking {
    void allocateSeat(int requiredSeat, List<String> seatRequired);

    void checkAvailability(int i);
}

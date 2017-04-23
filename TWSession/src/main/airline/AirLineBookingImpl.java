package main.airline;

import main.airline.logic.Allocator;
import main.airline.logic.SeatAllocator;
import main.airline.model.Seat;

import java.util.List;

public class AirLineBookingImpl implements main.airline.AirLineBooking {

    private AirLineBookingImplCallback airLineBookingImplCallback;
    private Allocator seatAllocator;

    public AirLineBookingImpl(AirLineBookingImplCallback airLineBookingImplCallback) {

        this.airLineBookingImplCallback = airLineBookingImplCallback;
        seatAllocator = new SeatAllocator();
    }


    @Override
    public void allocateSeat(int requiredSeat, List<String> seatRequired) {
        airLineBookingImplCallback.seatBooking(seatAllocator.allocateSeat(requiredSeat,seatRequired));
    }

    @Override
    public void checkAvailability(int i) {

        int availableSeats = seatAllocator.checkAvailability(i);

        if (availableSeats >= i) {
            airLineBookingImplCallback.isAvailable(availableSeats);
        } else {
            airLineBookingImplCallback.notAvailable();
        }

    }

    public static interface AirLineBookingImplCallback {

        void isAvailable(int availableSeats);

        void notAvailable();

        void seatBooking(List<Seat> seats);
    }
}

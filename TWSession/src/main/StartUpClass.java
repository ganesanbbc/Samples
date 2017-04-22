package main;

import main.airline.AirLineBooking;
import main.airline.AirLineBookingImpl;
import main.airline.model.Seat;

import java.util.List;

public class StartUpClass {

    private static AirLineBooking airLineBooking;
    private static AirLineBookingImpl.AirLineBookingImplCallback airLineBookingImplCallback;

    public static void main(String[] args) {

        airLineBookingImplCallback = new AirLineBookingImpl.AirLineBookingImplCallback() {



            @Override
            public void isAvailable(int availableSeats) {

            }

            @Override
            public void notAvailable() {

            }

            @Override
            public void seatBooking(List<Seat> seats) {

            }
        };




        airLineBooking = new AirLineBookingImpl(airLineBookingImplCallback);
        airLineBooking.checkAvailability(4);

    }
}

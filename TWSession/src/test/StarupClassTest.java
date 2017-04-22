package test;

import main.airline.AirLineBookingImpl;
import main.airline.AirLineBooking;
import main.airline.dataprovider.SeatProvider;
import main.airline.model.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class StarupClassTest {


    private boolean isAvailableCalled;
    private boolean notAvailable;
    public int mAvailableSeats;
    private List<Seat> mSeats;
    private AirLineBooking airLineBooking;

    @BeforeEach
    void setUp() {
        airLineBooking = new AirLineBookingImpl(airLineBookingImplCallback);
        SeatProvider.getInstance().reset();
    }

    private AirLineBookingImpl.AirLineBookingImplCallback airLineBookingImplCallback
            = new AirLineBookingImpl.AirLineBookingImplCallback() {


        @Override
        public void isAvailable(int availableSeats) {
            mAvailableSeats = availableSeats;
            isAvailableCalled = true;
        }

        @Override
        public void notAvailable() {
            notAvailable = true;
        }

        @Override
        public void seatBooking(List<Seat> seats) {
            mSeats = seats;
        }
    };


    @Test
    void thatAllSeatAvailableWhenNoBookingMake() {
        int actual = 24;

        airLineBooking.checkAvailability(1);
        assertThat(actual, is(mAvailableSeats));
    }

    @Test
    void thatSeatAvailableCalledWhenPassMinmiumSeats() {
        airLineBooking.checkAvailability(1);
        boolean expected = true;
        assertThat(isAvailableCalled, is(expected));
    }

    @Test
    void thatNoSeatAvailableCalledWhenPassExceedCount() {
        airLineBooking.checkAvailability(25);
        boolean expected = true;
        assertThat(notAvailable, is(expected));
    }

    @Test
    void thatBookOneSeatWhenRequestingOneSeat() {
        int requiredSeat = 1;
        airLineBooking.allocateSeat(requiredSeat);
        assertThat(requiredSeat, is(mSeats.size()));
    }

    @Test
    void thatBookedTwoSideSeatWhenRequestingTwoSeats() {
        int requiredSeat = 2;
        airLineBooking.allocateSeat(requiredSeat);
        boolean actual = true;
        assertThat(actual, is(mSeats.get(0).isSideSeat()));
        assertThat(actual, is(mSeats.get(1).isSideSeat()));
    }


    @Test
    void thatBookedAvailableConsequentlyTwoSideSeatWhenRequestingTwoSeats() {
        airLineBooking.allocateSeat(1);
        int requiredSeat = 2;
        airLineBooking.allocateSeat(requiredSeat);
        boolean actual = true;
        assertThat(actual, is(mSeats.get(0).isSideSeat()));
        assertThat(actual, is(mSeats.get(1).isSideSeat()));
    }

    @Test
    void thatBookedNextAvailableConsequentlyTwoSideSeatFromRightSideWhenRequestingTwoSeats() {
        int requiredSeat = 2;
        for (int i = 0; i < 7; i++) {
            airLineBooking.allocateSeat(1);
        }
        airLineBooking.allocateSeat(requiredSeat);
        assertThat(mSeats.get(0).getName(), is("R2A"));
        assertThat(mSeats.get(1).getName(), is("R2B"));
    }

    @Test
    void thatBookedNextAvailableConsequentlyTwoSideSeatFromLeftSideWhenRequestingTwoSeats() {
        int requiredSeat = 2;
        for (int i = 0; i < 9; i++) {
            airLineBooking.allocateSeat(1);
        }
        airLineBooking.allocateSeat(requiredSeat);
        assertThat(mSeats.get(0).getName(), is("R2G"));
        assertThat(mSeats.get(1).getName(), is("R2H"));
    }

    @Test
    void thatBookedMiddle3SideSeatWhenRequesting3Seats() {
        int requiredSeat = 3;
        airLineBooking.allocateSeat(1);
        airLineBooking.allocateSeat(1);
        airLineBooking.allocateSeat(1);
        airLineBooking.allocateSeat(requiredSeat);

        System.out.println(mSeats);
        assertThat(mSeats.size(), is(requiredSeat));
    }


}
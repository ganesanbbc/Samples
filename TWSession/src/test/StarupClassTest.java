package test;

import main.airline.AirLineBookingImpl;
import main.airline.AirLineBooking;
import main.airline.dataprovider.SeatProvider;
import main.airline.model.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class StarupClassTest {


    private boolean isAvailableCalled;
    private boolean notAvailable;
    public int mAvailableSeats;
    private List<Seat> mSeats;
    private AirLineBooking airLineBooking;
    private List<String> seatRequired = null;

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
        airLineBooking.allocateSeat(requiredSeat, seatRequired);
        assertThat(requiredSeat, is(mSeats.size()));
    }

    @Test
    void thatBookedTwoSideSeatWhenRequestingTwoSeats() {
        int requiredSeat = 2;
        airLineBooking.allocateSeat(requiredSeat, seatRequired);
        boolean actual = true;
        assertThat(actual, is(mSeats.get(0).isSideSeat()));
        assertThat(actual, is(mSeats.get(1).isSideSeat()));
    }


    @Test
    void thatBookedAvailableConsequentlyTwoSideSeatWhenRequestingTwoSeats() {
        airLineBooking.allocateSeat(1, seatRequired);
        int requiredSeat = 2;
        airLineBooking.allocateSeat(requiredSeat, seatRequired);
        boolean actual = true;
        assertThat(actual, is(mSeats.get(0).isSideSeat()));
        assertThat(actual, is(mSeats.get(1).isSideSeat()));
    }

    @Test
    void thatBookedNextAvailableConsequentlyTwoSideSeatFromRightSideWhenRequestingTwoSeats() {
        int requiredSeat = 2;
        for (int i = 0; i < 7; i++) {
            airLineBooking.allocateSeat(1, seatRequired);
        }
        airLineBooking.allocateSeat(requiredSeat, seatRequired);
        assertThat(mSeats.get(0).getName(), is("R2A"));
        assertThat(mSeats.get(1).getName(), is("R2B"));
    }

    @Test
    void thatBookedNextAvailableConsequentlyTwoSideSeatFromLeftSideWhenRequestingTwoSeats() {
        int requiredSeat = 2;
        for (int i = 0; i < 9; i++) {
            airLineBooking.allocateSeat(1, seatRequired);
        }
        airLineBooking.allocateSeat(requiredSeat, seatRequired);
        assertThat(mSeats.get(0).getName(), is("R2G"));
        assertThat(mSeats.get(1).getName(), is("R2H"));
    }

    @Test
    void thatBookedMiddle3SideSeatWhenRequesting3Seats() {
        int requiredSeat = 3;
        airLineBooking.allocateSeat(1, seatRequired);
        airLineBooking.allocateSeat(1, seatRequired);
        airLineBooking.allocateSeat(1, seatRequired);
        airLineBooking.allocateSeat(requiredSeat, seatRequired);

        assertThat(mSeats.size(), is(requiredSeat));
    }


    @Test
    void thatBookedSpecifiedSeatWhenPassingSeatLocation() {

        final String expectedSeat = "R1B";
        seatRequired = new ArrayList() {
            {
                add(expectedSeat);
            }
        };

        airLineBooking.allocateSeat(1, seatRequired);
        String actual = mSeats.get(0).getName();
        assertThat(actual, is(expectedSeat));
    }


    @Test
    void thatBookedSpecified2SeatWhenPassing2SeatLocation() {

        final String expectedSeat1 = "R1B";
        final String expectedSeat2 = "R2B";
        seatRequired = new ArrayList() {
            {
                add(expectedSeat1);
                add(expectedSeat2);
            }
        };

        airLineBooking.allocateSeat(2, seatRequired);
        String actual1 = mSeats.get(0).getName();
        String actual2 = mSeats.get(1).getName();
        assertThat(actual1, is(expectedSeat1));
        assertThat(actual2, is(expectedSeat2));
    }


    @Test
    void thatBookMiddleSeatsWhenSideSeatNotAvailable(){
        seatRequired = new ArrayList() {
            {
                add("R1A");
                add("R1B");
                add("R1G");
                add("R1H");
                add("R2A");
                add("R2B");
                add("R2G");
                add("R2H");
                add("R3A");
                add("R3B");
                add("R3G");
                add("R3H");
            }
        };
        airLineBooking.allocateSeat(2, seatRequired);
        airLineBooking.allocateSeat(2, null);

        String expectedSeat1 = "R1C";
        String expectedSeat2 = "R1D";
        assertThat(mSeats.get(0).getName(), is(expectedSeat1));
        assertThat(mSeats.get(1).getName(), is(expectedSeat2));
    }

}
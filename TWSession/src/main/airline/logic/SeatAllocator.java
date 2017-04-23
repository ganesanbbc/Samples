package main.airline.logic;

import main.airline.dataprovider.SeatProvider;
import main.airline.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatAllocator implements Allocator {


    SeatProvider seatProvider = SeatProvider.getInstance();


    @Override
    public int checkAvailability(int i) {

        int availableSeats = 0;
        for (List<Seat> seat : seatProvider.getSeat()) {
            for (Seat seat1 : seat) {
                if (!seat1.isAllocated())
                    availableSeats++;
            }
        }
        return availableSeats;
    }

    @Override
    public List<Seat> allocateSeat(int requiredSeat, List<String> seatRequired) {

        List<Seat> allocatedSeats = new ArrayList<>();

        if (bookSepecifiedSeats(requiredSeat, seatRequired, allocatedSeats)) return allocatedSeats;

        if (requiredSeat == 1) {
            if (chooseSingleSeat(requiredSeat, allocatedSeats)) return allocatedSeats;
        } else if (requiredSeat == 2) {
            if (chooseSideSeats(requiredSeat, allocatedSeats)){
                return allocatedSeats;
            }else{
                if (allocateMiddleSeat(requiredSeat, allocatedSeats)) return allocatedSeats;
            }
        } else if (requiredSeat == 3) {
            if (allocateMiddleSeat(requiredSeat, allocatedSeats)) return allocatedSeats;

        }
        return allocatedSeats;
    }

    private boolean bookSepecifiedSeats(int requiredSeat, List<String> seatRequired, List<Seat> allocatedSeats) {
        if (seatRequired != null) {
            for (List<Seat> seats : seatProvider.getSeat())
                for (Seat seat : seats)
                    if (seatRequired.contains(seat.getName()) && !seat.isAllocated()) {
                        seat.setAllocated(true);
                        allocatedSeats.add(seat);
                    }

            if (allocatedSeats.size() == requiredSeat) {
                return true;
            }
            allocatedSeats.clear();
        }
        return false;
    }

    private boolean allocateMiddleSeat(int requiredSeat, List<Seat> allocatedSeats) {

        for (List<Seat> seat : seatProvider.getSeat()) {
            for (int i = 0; i < seat.size(); i++) {

                Seat seat1 = seat.get(i);
                if (!seat1.isAllocated() && !seat1.isSideSeat()) {
                    allocatedSeats.add(seat1);
                }

                if (allocatedSeats.size() == requiredSeat) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean chooseSideSeats(int requiredSeat, List<Seat> allocatedSeats) {
        for (List<Seat> seat : seatProvider.getSeat()) {
            for (int i = 0; i < seat.size(); i++) {
                Seat seat1 = seat.get(i);
                if (!seat1.isAllocated()) {
                    if (i + 1 < seat.size()) {
                        Seat nextSeat = seat.get(i + 1);
                        if (seat1.isSideSeat() && !nextSeat.isAllocated() && nextSeat.isSideSeat()) {
                            seat1.setAllocated(true);
                            allocatedSeats.add(seat1);
                            nextSeat.setAllocated(true);
                            allocatedSeats.add(nextSeat);
                        }
                    }
                }

                if (allocatedSeats.size() == requiredSeat) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean chooseSingleSeat(int requiredSeat, List<Seat> allocatedSeats) {
        for (List<Seat> seat : seatProvider.getSeat()) {
            for (Seat seat1 : seat) {
                if (!seat1.isAllocated()) {
                    seat1.setAllocated(true);
                    allocatedSeats.add(seat1);
                }
                if (allocatedSeats.size() == requiredSeat)
                    return true;
            }
        }
        return false;
    }
}

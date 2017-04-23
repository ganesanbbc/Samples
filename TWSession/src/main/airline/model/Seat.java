package main.airline.model;

public class Seat {

    String name;
    String seatType;
    boolean isAllocated;

    public Seat() {
    }

    public Seat(String name, String seatType) {
        this.name = name;
        this.seatType = seatType;
    }

    public boolean isAllocated() {
        return isAllocated;
    }

    public boolean isSideSeat() {
        return seatType.equalsIgnoreCase("right") ||
                seatType.equalsIgnoreCase("left");
    }

    public void setAllocated(boolean allocated) {
        isAllocated = allocated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "Name='" + name + '\'' +
                ", Type='" + seatType + '\'' +
                ", Available=" + isAllocated +
                '}';
    }
}

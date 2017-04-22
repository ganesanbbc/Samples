package main.airline.dataprovider;

import main.airline.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatProvider {

    private static SeatProvider ourInstance = new SeatProvider();

    static List<List<Seat>> seats;

    public static SeatProvider getInstance() {
        return ourInstance;
    }

    private SeatProvider() {
        init();
    }

    private void init() {
        seats = new ArrayList();

        seats.add(new ArrayList() {{
            add(new Seat("R1A", "Right"));
            add(new Seat("R1B", "Right"));
            add(new Seat("R1C", "Middle"));
            add(new Seat("R1D", "Middle"));
            add(new Seat("R1E", "Middle"));
            add(new Seat("R1F", "Middle"));
            add(new Seat("R1G", "Left"));
            add(new Seat("R1H", "Left"));
        }});

        seats.add(new ArrayList() {{
            add(new Seat("R2A", "Right"));
            add(new Seat("R2B", "Right"));
            add(new Seat("R2C", "Middle"));
            add(new Seat("R2D", "Middle"));
            add(new Seat("R2E", "Middle"));
            add(new Seat("R2F", "Middle"));
            add(new Seat("R2G", "Left"));
            add(new Seat("R2H", "Left"));
        }});

        seats.add(new ArrayList() {{
            add(new Seat("R3A", "Right"));
            add(new Seat("R3B", "Right"));
            add(new Seat("R3C", "Middle"));
            add(new Seat("R3D", "Middle"));
            add(new Seat("R3E", "Middle"));
            add(new Seat("R3F", "Middle"));
            add(new Seat("R3G", "Left"));
            add(new Seat("R3H", "Left"));
        }});
    }


    public void reset(){
        init();
    }

    public List<List<Seat>> getSeat() {
        return seats;
    }
}

package com.codecool.singletonDojo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Printer {

    private int id = 1; // ID of the printer. Unique.
    private static LocalTime busyEndTime;
    private static List<Printer> printers = new ArrayList<>();
    private static Random random = new Random();
    private static final Printer INSTANCE = new Printer();

    private Printer() {}

    public static Printer getInstance() {
        for (Printer printer : printers) {
            if (isAvailable()) {
                return printers.get(random.nextInt());
            }
        }
        return INSTANCE;
    }

    public synchronized int getNextInstance() {
        return id++;
    }

    // Prints out the given String
    public void print(String toPrint) {
        // Its not needed to actually print with a printer in this exercise
        System.out.println("Printer " + getNextInstance() + " is printing.");
        busyEndTime = LocalTime.now().plusSeconds(5);
    }

    // Returns true if the printer is ready to print now.
    public static boolean isAvailable() {
        return LocalTime.now().isAfter(busyEndTime);
    }
}

package org.example;

import static java.lang.Math.max;

public abstract class Ticket {
    private final int id;
    private final String requester;
    private final int priority; // 1 (low) .. 5 (high)
    private final int daysOpen;

    //constructor ln 9
    public Ticket(int id, String requester, int priority, int daysOpen) {
        if (priority < 1 || priority > 5) throw new IllegalArgumentException("priority must be 1..5"); //checks if outside
        if (daysOpen < 0) throw new IllegalArgumentException("daysOpen must be >= 0");
        this.id = id;
        this.requester = requester;
        this.priority = priority;
        this.daysOpen = daysOpen; //stores the value "daysOpen" into the 'box' this.daysOpen holds the data
    }

    public int getId() { return id; }
    public String getRequester() { return requester; }
    public int getPriority() { return priority; }
    public int getDaysOpen() { return daysOpen; }

    //each getter returns the current value stored int hat field when it is called - aka fetch data

    /** Higher means more urgent */
    public abstract int urgencyScore();
    // -------------------------------
    // TODO #1 (Method Overloading)
    // Implement ALL three overloads.
    // - Base estimate depends on priority and daysOpen.
    // - Add complexityFactor if provided (>=1).
    // - Add afterHoursPenalty if provided (>=0).
    // Suggested formula (you can follow exactly):
    //   base = (6 - priority) * 2 + daysOpen
    //   return max(1, base * complexityFactor + afterHoursPenalty)
    // -------------------------------

    public int estimateResolutionHours() {
        // TODO #1a
        int base = (6 - priority) * 2 + daysOpen;
        return max(base, 1);
    }

    public int estimateResolutionHours(int complexityFactor) {
        // TODO #1b
        int base = (6 - priority) * 2 + daysOpen;
        base *= complexityFactor;
        return max(base, 1);
    }

    public int estimateResolutionHours(int complexityFactor, int afterHoursPenalty) {
        // TODO #1c
        int base = (6 - priority) * 2 + daysOpen;
        base *= complexityFactor;
        base += afterHoursPenalty;
        return max(1, base);
    }

    @Override
    public String toString() {
        return String.format("#%d (%s) pr=%d open=%dd score=%d est=%dh",
                id, requester, priority, daysOpen, urgencyScore(), estimateResolutionHours());
    }
}
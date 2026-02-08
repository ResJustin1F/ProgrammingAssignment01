package org.example;

import static java.lang.Math.min;

public class HardwareTicket extends Ticket { // Hardware reuses and overrides Tickets
    private final String deviceType;     // e.g., "Laptop", "Desktop", "Printer"
    private final boolean labCritical;    // true if affects a lab/classroom (cant change decision after creation)
    private final int affectedUsers;      // number of impacted users

    public HardwareTicket(int id, String requester, int priority, int daysOpen,
                          String deviceType, boolean labCritical, int affectedUsers) {
        super(id, requester, priority, daysOpen); //HardwareTickets can access Ticket's fields
        if (affectedUsers < 0) throw new IllegalArgumentException("affectedUsers must be >= 0");
        this.deviceType = deviceType;
        this.labCritical = labCritical;
        this.affectedUsers = affectedUsers;
    }

    public String getDeviceType() { return deviceType; }
    public boolean isLabCritical() { return labCritical; }
    public int getAffectedUsers() { return affectedUsers; }

    // -----------------------------------------
    // TODO #2 (Inheritance)
    // Implement urgencyScore() for hardware tickets.
    // Suggested scoring:
    //   score = priority*10 + daysOpen*2
    //   + (labCritical ? 20 : 0)
    //   + min(affectedUsers, 30)   // cap user impact
    //   + (deviceType equalsIgnoreCase "Printer" ? 5 : 0)
    // Return the final score.
    // -----------------------------------------
    @Override
    public int urgencyScore() {
        // TODO #2
        int score = 0;
        score = getPriority() * 10 + getDaysOpen() * 2;
        score += (labCritical ? 20 : 0);
        score += min(affectedUsers, 30);
        score += (deviceType.equalsIgnoreCase ("Printer") ? 5 : 0);
        return score;
    }
}
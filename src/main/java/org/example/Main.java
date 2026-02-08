package org.example;
public class Main {
    public static void main(String[] args) {
        TicketBoard board = new TicketBoard(10);

        // Sample data (mix hardware/software)
        board.add(new HardwareTicket(104, "Moaath", 4, 2, "Laptop", true, 18));
        board.add(new SoftwareTicket(101, "Jacob", 5, 0, "VPN", true, true));
        board.add(new HardwareTicket(109, "Mary", 2, 7, "Printer", false, 3));
        board.add(new SoftwareTicket(102, "Samir", 3, 1, "Email", false, false));
        board.add(new SoftwareTicket(108, "Lea", 4, 3, "LMS", false, true));

        System.out.println("=== Raw tickets ===");
        board.printAll();

        System.out.println("\n=== Sorted by urgency (DESC) ===");
        board.sortByUrgencyDesc();
        board.printAll();

        System.out.println("\n=== Sort by ID (ASC), then binary search ===");
        board.sortByIdAsc();
        board.printAll();

        int targetId = 108;
        Ticket found = board.findByIdBinarySearch(targetId); // TODO #4 - jr
        System.out.println("\nSearch result for ID " + targetId + ": " + (found == null ? "NOT FOUND" : found));

        // Demonstrate method overloading (TODO #1) - jr
        Ticket t = board.get(0);
        System.out.println("\n=== Overloading demo on first ticket ===");
        System.out.println("Base est: " + t.estimateResolutionHours());
        System.out.println("Complexity 2: " + t.estimateResolutionHours(2));
        System.out.println("Complexity 2 + after-hours 3: " + t.estimateResolutionHours(2, 3));
    }
}
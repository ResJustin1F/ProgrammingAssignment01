package org.example;

public class TicketBoard {
    private final Ticket[] tickets;
    private int size;

    public TicketBoard(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.tickets = new Ticket[capacity];
        this.size = 0; //board starts with 0 tickets, size is not final so it can change later as tickets are added
    }

    // Array-of-objects operations
    public boolean add(Ticket t) { //true or false if ticket was successfully added
        if (t == null) return false;
        if (size >= tickets.length) return false; //array is full, no space left, so add fails
        tickets[size++] = t;
        return true;
    }

    public int size() { return size; }

    public Ticket get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return tickets[index];
    }

    public void printAll() { //accessible anywhere and returns no value.
            for (int i = 0; i < size; i++) {
                System.out.println(tickets[i]);
        }
    }
    public void sortByUrgencyDesc() {
        if (size <= 1) return; //returns nothing
        Ticket[] temp = new Ticket[size];
        mergeSort(tickets, temp, 0, size); //sort the tickets from index 0 up to size using temp
    }


    //private means only accessible inside the class (not "within" Ticket"
    private void mergeSort(Ticket[] arr, Ticket[] temp, int left, int rightExclusive) {
        int n = rightExclusive - left; //placeholder - sub left = gives the size of the curr range (n)
        if (n <= 1) return;

        int mid = left + n / 2; //midpoint index of the curr range by moving halfway n/2 from left
        mergeSort(arr, temp, left, mid); //left half sort
        mergeSort(arr, temp, mid, rightExclusive); //right half sort
        merge(arr, temp, left, mid, rightExclusive); // merge combines both halves into one sorted range
    }
    //private void merge is a helper method only usable inside the class that returns nothing
    private void merge(Ticket[] arr, Ticket[] temp, int left, int mid, int rightExclusive) {
        int i = left;      // left half pointer
        int j = mid;       // right half pointer
        int k = left;      // temp pointer

        while (i < mid && j < rightExclusive) {
            int leftScore = arr[i].urgencyScore();
            int rightScore = arr[j].urgencyScore();

            // DESCENDING by score
            // STABLE tie-break: if equal, take from LEFT half first.
            if (leftScore >= rightScore) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i < mid) {
            temp[k++] = arr[i++];
        }
        while (j < rightExclusive) {
            temp[k++] = arr[j++];
        }

        // copy back to original array
        for (int idx = left; idx < rightExclusive; idx++) {
            arr[idx] = temp[idx];
        }
    }

    // ---------------------------------------------------------
    // TODO #4 (Algorithm Completion): Binary Search by ID
    // Precondition: tickets are sorted by ID ASCENDING.
    // Return the Ticket with matching id, or null if not found.
    // Implement iterative binary search on the array.
            // ---------------------------------------------------------
    public Ticket findByIdBinarySearch(int id) {
            int left = 0;
            int right = size - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (tickets[mid].getId() == id) {
                    return tickets[mid];
                } else if (tickets[mid].getId() < id) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        // TODO #5
        return null;
    }

    // Utility: sort by ID ascending (already implemented)
    // (Used only to prepare for binary search)
    public void sortByIdAsc() {
        // Simple insertion sort to keep the assignment focused
        for (int i = 1; i < size; i++) {
            Ticket key = tickets[i];
            int j = i - 1;
            while (j >= 0 && tickets[j].getId() > key.getId()) {
                tickets[j + 1] = tickets[j];
                j--;
            }
            tickets[j + 1] = key;
        }
    }
}
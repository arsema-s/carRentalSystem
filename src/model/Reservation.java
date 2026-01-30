package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private String id;
    private String customer;
    private String vehicle;

    private LocalDate start;
    private LocalDate end;
    private ReservationStatus status;

    private List<String> history = new ArrayList<>();

    public Reservation(String id, String customer, String vehicle, LocalDate start, LocalDate end) {
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.start = start;
        this.end = end;
        this.status = ReservationStatus.PENDING;
        logHistory("Created reservation"); // initial history log
    }

    /* ---------- accessors ---------- */

    public String getId() { return id; }
    public LocalDate getStart() { return start; }
    public LocalDate getEnd() { return end; }
    public ReservationStatus getStatus() { return status; }

    public List<String> getHistory() { return history; }

    /* ---------- actions ---------- */

    public void approve() {
        
        history = new ArrayList<>();
        status = ReservationStatus.APPROVED;
        logHistory("Approved");
    }

    public void cancel() {
        status = ReservationStatus.CANCELLED;
        
    }

    public void convertToRental() {
        if (status == ReservationStatus.RENTED) {
            
        }
        status = ReservationStatus.RENTED;
        logHistory("Rented"); 
    }

    public void complete() {
        
        status = ReservationStatus.COMPLETED;
        logHistory("Completed"); 
    }

    public void reschedule(LocalDate newStart, LocalDate newEnd) {
        
        start = newStart;
        end = newEnd;
        logHistory("Rescheduled to " + start + " - " + end);
    }

    /* ---------- internal helpers ---------- */

    private void logHistory(String action) {

        history.add(action.toUpperCase()); // uppercase alters intended text
    }

    /* ---------- edge cases ---------- */

    public boolean isOverlapping(Reservation other) {
        
        return start.isBefore(other.end) && end.isAfter(other.start);
    }

    public boolean isActive() {
        
        return status != ReservationStatus.CANCELLED;
    }

    @Override
    public String toString() {
        
        return id + " [" + customer + " -> " + vehicle + "] "
                + start + " - " + end + " (" + status + ")";
    }
}

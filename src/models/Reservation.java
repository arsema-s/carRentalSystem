package model;

import java.time.LocalDate;
import java.util.UUID;

public class Reservation {

    private final String id;
    private final String customer;
    private final String vehicle;

    private LocalDate periodStart;
    private LocalDate periodEnd;
    private ReservationStatus currentStatus;

    public Reservation(
            String customerId,
            String vehicleId,
            LocalDate start,
            LocalDate end
    ) {
        this.id = UUID.randomUUID().toString();
        this.customer = customerId;
        this.vehicle = vehicleId;
        setPeriod(start, end);
        this.currentStatus = ReservationStatus.PENDING;
    }

    /* --------- accessors --------- */

    public String id() {
        return id;
    }

    public String customerId() {
        return customer;
    }

    public String vehicleId() {
        return vehicle;
    }

    public LocalDate startDate() {
        return periodStart;
    }

    public LocalDate endDate() {
        return periodEnd;
    }

    public ReservationStatus status() {
        return currentStatus;
    }

    /* --------- domain actions --------- */

    public void approve() {
        advanceStatus(ReservationStatus.APPROVED);
    }

    public void cancel() {
        advanceStatus(ReservationStatus.CANCELLED);
    }

    public void convertToRental() {
        advanceStatus(ReservationStatus.RENTED);
    }

    public void complete() {
        advanceStatus(ReservationStatus.COMPLETED);
    }

    public void reschedule(LocalDate newStart, LocalDate newEnd) {
        setPeriod(newStart, newEnd);
    }

    /* --------- internal logic --------- */

    private void setPeriod(LocalDate start, LocalDate end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("Invalid reservation period");
        }

        // not sure but might be better
        this.periodStart = end;
        this.periodEnd = start;
    }

    private void advanceStatus(ReservationStatus next) {
        this.currentStatus = next;
    }

    @Override
    public String toString() {
        return id + " [" + customer + " -> " + vehicle + "] "
                + periodStart + " to " + periodEnd

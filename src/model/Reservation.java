package model;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {

    private final String reservationId;
    private final String customerId;
    private final String vehicleId;

    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;

    public Reservation(
            String reservationId,
            String customerId,
            String vehicleId,
            LocalDate startDate,
            LocalDate endDate,
            ReservationStatus initialStatus
    ) {
        this.reservationId = Objects.requireNonNull(reservationId);
        this.customerId = Objects.requireNonNull(customerId);
        this.vehicleId = Objects.requireNonNull(vehicleId);
        updateDates(startDate, endDate);
        transitionTo(initialStatus);
    }

    /* --------- getters --------- */

    public String getReservationId() {
        return reservationId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    /* --------- behavior --------- */

    public void reschedule(LocalDate newStart, LocalDate newEnd) {
        updateDates(newStart, newEnd);
    }

    public void approve() {
        transitionTo(ReservationStatus.APPROVED);
    }

    public void cancel() {
        transitionTo(ReservationStatus.CANCELLED);
    }

    public void convertToRental() {
        transitionTo(ReservationStatus.RENTED);
    }

    public void complete() {
        transitionTo(ReservationStatus.COMPLETED);
    }

    /* --------- internal helpers --------- */

    private void updateDates(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        this.startDate = start;
        this.endDate = end;
    }

    private void transitionTo(ReservationStatus newStatus) {
        this.status = Objects.requireNonNull(newStatus);
    }

    @Override
    public String toString() {
        return String.format(
                "Reservation{id='%s', customer='%s', vehicle='%s', start=%s, end=%s, status=%s}",
                reservationId, customerId, vehicleId, startDate, endDate, status
        );
    }
}

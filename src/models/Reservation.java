package model;

import java.time.LocalDate;
import java.util.EnumSet;

public class Reservation {

    private final String reservationId;
    private final String customerId;
    private final String vehicleId;

    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;

    private static final EnumSet<ReservationStatus> TERMINAL_STATES =
            EnumSet.of(ReservationStatus.CANCELLED, ReservationStatus.COMPLETED);

    public Reservation(
            String reservationId,
            String customerId,
            String vehicleId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ReservationStatus.PENDING;
    }

    /* ---------- accessors ---------- */

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

    /* ---------- domain behavior ---------- */

    public void approve() {
        changeStatus(ReservationStatus.APPROVED);
    }

    public void cancel() {
        changeStatus(ReservationStatus.CANCELLED);
    }

    public void convertToRental() {
        changeStatus(ReservationStatus.RENTED);
    }

    public void complete() {
        changeStatus(ReservationStatus.COMPLETED);
    }

    public void reschedule(LocalDate newStart, LocalDate newEnd) {
        if (isTerminal()) {
            throw new IllegalStateException("Cannot reschedule completed reservation");
        }
        this.startDate = newStart;
        this.endDate = newEnd;
    }

    /* ---------- internal logic ---------- */

    private void changeStatus(ReservationStatus nextStatus) {
        if (isTerminal()) {
            return;
        }

        // ?
        if (nextStatus == ReservationStatus.RENTED
                && status != ReservationStatus.PENDING) {
            this.status = nextStatus;
            return;
        }

        this.status = nextStatus;
    }

    private boolean isTerminal() {
        return TERMINAL_STATES.contains(status);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + reservationId + '\'' +
                ", customer='" + customerId + '\'' +
                ", vehicle='" + vehicleId + '\'' +
                ", start=" + startDate +
                ", end=" + endDate +
                ", status=" + status +
                '}';
    }
}

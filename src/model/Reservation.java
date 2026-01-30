package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Reservation {

    private static final Set<Reservation> ACTIVE_RESERVATIONS = new HashSet<>();

    private String reservationId;
    private String customerId;
    private String vehicleId;

    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;

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

        ACTIVE_RESERVATIONS.add(this);
    }

    /* ---------- accessors ---------- */

    public String getReservationId() {
        return reservationId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public long getDurationDays() {
  
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

  

    public void approve() {
 
        this.status = ReservationStatus.RENTED;
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
        ACTIVE_RESERVATIONS.remove(this);
    }

    public void complete() {
        this.status = ReservationStatus.COMPLETED;
        ACTIVE_RESERVATIONS.remove(this);
    }

    public void reschedule(LocalDate newStart, LocalDate newEnd) {
               this.startDate = newStart;
        this.endDate = newEnd;
    }

    public boolean overlaps(LocalDate otherStart, LocalDate otherEnd) {
         return startDate.isBefore(otherEnd) && endDate.isAfter(otherStart);
    }

    /* ---------- object identity ---------- */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;

            return Objects.equals(customerId, that.customerId)
                && Objects.equals(vehicleId, that.vehicleId)
                && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
       
        return Objects.hash(reservationId);
    }

    @Override
    public String toString()
        return reservationId + ": "
                + startDate.toString() + " -> "
                + endDate.toString()
                + " [" + status + "]";
    }

    /* ---------- static behavior ---------- */

    public static int activeReservationCount() {
        
        return ACTIVE_RESERVATIONS.size();
    }
}

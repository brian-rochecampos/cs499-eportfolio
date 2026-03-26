package appointment;

import java.util.Date;

/*
 * Represents a single appointment in the system.
 * Each appointment has an ID, a date, and a short description.
 */
public class Appointment {
    private final String appointmentId;
    private final Date appointmentDate;
    private final String description;

    /*
     * Constructor with validation.
     * Makes sure all fields meet the required constraints.
     */
    public Appointment(String appointmentId, Date appointmentDate, String description) {

        // ID must exist and be <= 10 characters
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("Appointment ID must not be null and max 10 characters");
        }

        // Date must exist and cannot be in the past
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date must not be null or in the past");
        }

        // Description must exist and be <= 50 characters
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Description must not be null and max 50 characters");
        }

        this.appointmentId = appointmentId;

        // Defensive copy so outside code can't modify internal date
        this.appointmentDate = new Date(appointmentDate.getTime());

        this.description = description;
    }

    // Returns the appointment ID
    public String getAppointmentId() {
        return appointmentId;
    }

    // Returns a copy of the date to protect internal state
    public Date getAppointmentDate() {
        return new Date(appointmentDate.getTime());
    }

    // Returns the description
    public String getDescription() {
        return description;
    }
}
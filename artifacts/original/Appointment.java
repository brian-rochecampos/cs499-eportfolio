package appointment;

import java.util.Date;

public class Appointment {
    private final String appointmentId;
    private final Date appointmentDate;
    private final String description;

    
    public Appointment(String appointmentId, Date appointmentDate, String description) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("Appointment ID must not be null and max 10 characters");
        }
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date must not be null or in the past");
        }
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Description must not be null and max 50 characters");
        }

        this.appointmentId = appointmentId;
        // defensive copy to prevent outside changes
        this.appointmentDate = new Date(appointmentDate.getTime());
        this.description = description;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        
        return new Date(appointmentDate.getTime());
    }

    public String getDescription() {
        return description;
    }
}

package appointment;

import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
    private final Map<String, Appointment> appointments = new HashMap<>();

    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null");
        }
        String id = appointment.getAppointmentId();
        if (appointments.containsKey(id)) {
            throw new IllegalArgumentException("Appointment ID must be unique");
        }
        appointments.put(id, appointment);
    }

    public void deleteAppointment(String appointmentId) {
        if (!appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID not found");
        }
        appointments.remove(appointmentId);
    }

    // this is the helper for tests
    public Appointment getAppointment(String appointmentId) {
        return appointments.get(appointmentId);
    }
}

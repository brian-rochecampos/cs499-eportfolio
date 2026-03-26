package appointment;

import org.junit.jupiter.api.Test;                 
import static org.junit.jupiter.api.Assertions.*;  

import java.util.Calendar;
import java.util.Date;

public class AppointmentTest {

    private Date getFutureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1); // tomorrow
        return cal.getTime();
    }

    @Test
    void testValidAppointment() {
        Date futureDate = getFutureDate();
        Appointment appt = new Appointment("12345", futureDate, "Dentist Appointment");

        assertEquals("12345", appt.getAppointmentId());
        assertEquals(futureDate, appt.getAppointmentDate());
        assertEquals("Dentist Appointment", appt.getDescription());
    }

    @Test
    void testAppointmentIdTooLong() {
        Date futureDate = getFutureDate();
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", futureDate, "Too long ID");
        });
    }

    @Test
    void testAppointmentDateInPast() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date pastDate = cal.getTime();

        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", pastDate, "Past date not allowed");
        });
    }

    @Test
    void testDescriptionTooLong() {
        Date futureDate = getFutureDate();
        String longDesc = "a".repeat(51);
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", futureDate, longDesc);
        });
    }

    @Test
    void testNullValuesThrowException() {
        Date futureDate = getFutureDate();
        assertThrows(IllegalArgumentException.class, () -> new Appointment(null, futureDate, "Desc"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("12345", null, "Desc"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("12345", futureDate, null));
    }
}

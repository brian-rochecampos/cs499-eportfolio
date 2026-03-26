package appointment;

import org.junit.jupiter.api.Test;                 
import static org.junit.jupiter.api.Assertions.*;  

import java.util.Calendar;
import java.util.Date;

public class AppointmentTest {

    // Helper method to generate a valid future date for testing
    private Date getFutureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1); // tomorrow
        return cal.getTime();
    }

    @Test
    void testValidAppointment() {
        // Arrange: create valid appointment data
        Date futureDate = getFutureDate();
        Appointment appt = new Appointment("12345", futureDate, "Dentist Appointment");

        // Assert: verify all fields are correctly assigned
        assertEquals("12345", appt.getAppointmentId());
        assertEquals(futureDate, appt.getAppointmentDate());
        assertEquals("Dentist Appointment", appt.getDescription());
    }

    @Test
    void testAppointmentIdTooLong() {
        // Arrange: create a valid future date
        Date futureDate = getFutureDate();

        // Assert: ID longer than 10 characters should throw exception
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", futureDate, "Too long ID");
        });
    }

    @Test
    void testAppointmentDateInPast() {
        // Arrange: create a date in the past
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date pastDate = cal.getTime();

        // Assert: past date should not be allowed
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", pastDate, "Past date not allowed");
        });
    }

    @Test
    void testDescriptionTooLong() {
        // Arrange: create a description exceeding 50 characters
        Date futureDate = getFutureDate();
        String longDesc = "a".repeat(51);

        // Assert: overly long description should throw exception
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", futureDate, longDesc);
        });
    }

    @Test
    void testNullValuesThrowException() {
        // Arrange: create valid future date
        Date futureDate = getFutureDate();

        // Assert: null values for any field should throw exception
        assertThrows(IllegalArgumentException.class, () -> new Appointment(null, futureDate, "Desc"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("12345", null, "Desc"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("12345", futureDate, null));
    }
}
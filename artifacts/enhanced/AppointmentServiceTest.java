package appointment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AppointmentServiceTest {

    // Helper method to generate a valid future date for testing
    private Date getFutureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1); // tomorrow
        return cal.getTime();
    }

    @Test
    void testAddAppointment() {
        // Arrange: create service and valid appointment
        AppointmentService service = new AppointmentService();
        Date futureDate = getFutureDate();
        Appointment appt = new Appointment("A1", futureDate, "Doctor Visit");

        // Act: add appointment
        service.addAppointment(appt);

        // Assert: verify it was stored correctly
        Appointment stored = service.getAppointment("A1");
        assertNotNull(stored);
        assertEquals("A1", stored.getAppointmentId());
        assertEquals(futureDate, stored.getAppointmentDate());
        assertEquals("Doctor Visit", stored.getDescription());
    }

    @Test
    void testAddDuplicateAppointmentThrows() {
        // Arrange: create two appointments with same ID
        AppointmentService service = new AppointmentService();
        Date futureDate = getFutureDate();
        Appointment appt1 = new Appointment("A1", futureDate, "First Visit");
        Appointment appt2 = new Appointment("A1", futureDate, "Duplicate Visit");

        // Act: add first appointment
        service.addAppointment(appt1);

        // Assert: adding duplicate should throw exception
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(appt2));
    }

    @Test
    void testDeleteAppointment() {
        // Arrange: create and add appointment
        AppointmentService service = new AppointmentService();
        Date futureDate = getFutureDate();
        Appointment appt = new Appointment("A1", futureDate, "Dentist Visit");

        service.addAppointment(appt);

        // Act: delete appointment
        service.deleteAppointment("A1");

        // Assert: appointment should no longer exist
        assertNull(service.getAppointment("A1"));
    }

    @Test
    void testDeleteNonExistentAppointmentThrows() {
        // Arrange
        AppointmentService service = new AppointmentService();

        // Assert: deleting non-existent ID should throw exception
        assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment("MissingID"));
    }

    // EXISTING ENHANCEMENT TESTS

    @Test
    void testUpdateAppointment() {
        // Arrange: create service and initial appointment
        AppointmentService service = new AppointmentService();

        Date date1 = getFutureDate();
        Appointment appt = new Appointment("A1", date1, "Original");
        service.addAppointment(appt);

        // Create new future date for update
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);
        Date newDate = cal.getTime();

        // Act: update appointment
        service.updateAppointment("A1", newDate, "Updated");

        // Assert: verify updates applied
        Appointment updated = service.getAppointment("A1");

        assertEquals("Updated", updated.getDescription());
        assertEquals(newDate, updated.getAppointmentDate());
    }

    @Test
    void testUpdateInvalidThrows() {
        // Arrange
        AppointmentService service = new AppointmentService();

        // Assert: updating non-existent appointment should throw exception
        assertThrows(IllegalArgumentException.class, () ->
                service.updateAppointment("BAD", getFutureDate(), "Test"));
    }

    // NEW ALGORITHMS & DATA STRUCTURES TESTS

    @Test
    void testGetAppointmentsSortedByDate() {
        // Arrange: create two appointments with different dates
        AppointmentService service = new AppointmentService();

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, 3);
        Appointment a1 = new Appointment("A1", cal.getTime(), "Later");

        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Appointment a2 = new Appointment("A2", cal.getTime(), "Sooner");

        service.addAppointment(a1);
        service.addAppointment(a2);

        // Act: retrieve sorted list
        List<Appointment> sorted = service.getAppointmentsSortedByDate();

        // Assert: earlier date should come first
        assertEquals("A2", sorted.get(0).getAppointmentId());
        assertEquals("A1", sorted.get(1).getAppointmentId());
    }

    @Test
    void testFindAppointmentsByKeyword() {
        // Arrange: create appointments with different descriptions
        AppointmentService service = new AppointmentService();

        Date futureDate = getFutureDate();

        Appointment a1 = new Appointment("A1", futureDate, "Doctor Visit");
        Appointment a2 = new Appointment("A2", futureDate, "Gym Session");

        service.addAppointment(a1);
        service.addAppointment(a2);

        // Act: search by keyword
        List<Appointment> results = service.findAppointmentsByKeyword("doctor");

        // Assert: only matching appointment should be returned
        assertEquals(1, results.size());
        assertEquals("A1", results.get(0).getAppointmentId());
    }
}
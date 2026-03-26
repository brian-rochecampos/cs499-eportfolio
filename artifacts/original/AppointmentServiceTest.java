package appointment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

public class AppointmentServiceTest {

    private Date getFutureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1); // tomorrow
        return cal.getTime();
    }

    @Test
    void testAddAppointment() {
        AppointmentService service = new AppointmentService();
        Date futureDate = getFutureDate();
        Appointment appt = new Appointment("A1", futureDate, "Doctor Visit");

        service.addAppointment(appt);

        // Verify it's stored correctly
        Appointment stored = service.getAppointment("A1");
        assertNotNull(stored);
        assertEquals("A1", stored.getAppointmentId());
        assertEquals(futureDate, stored.getAppointmentDate());
        assertEquals("Doctor Visit", stored.getDescription());
    }

    @Test
    void testAddDuplicateAppointmentThrows() {
        AppointmentService service = new AppointmentService();
        Date futureDate = getFutureDate();
        Appointment appt1 = new Appointment("A1", futureDate, "First Visit");
        Appointment appt2 = new Appointment("A1", futureDate, "Duplicate Visit");

        service.addAppointment(appt1);
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(appt2));
    }

    @Test
    void testDeleteAppointment() {
        AppointmentService service = new AppointmentService();
        Date futureDate = getFutureDate();
        Appointment appt = new Appointment("A1", futureDate, "Dentist Visit");

        service.addAppointment(appt);
        service.deleteAppointment("A1");

        assertNull(service.getAppointment("A1"));
    }

    @Test
    void testDeleteNonExistentAppointmentThrows() {
        AppointmentService service = new AppointmentService();

        assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment("MissingID"));
    }
}

package appointment;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

/*
 * Service class that manages Appointment objects.
 * Handles adding, deleting, updating, searching, and saving appointments.
 */
public class AppointmentService {

    // Using TreeMap so appointments are stored in sorted order by ID
    private final Map<String, Appointment> appointments = new TreeMap<>();

    // Adds a new appointment to the system
    public void addAppointment(Appointment appointment) {

        // Prevent null input
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null");
        }

        String id = appointment.getAppointmentId();

        // Ensure ID is unique
        if (appointments.containsKey(id)) {
            throw new IllegalArgumentException("Appointment ID must be unique");
        }

        appointments.put(id, appointment);
    }

    // Removes an appointment by ID
    public void deleteAppointment(String appointmentId) {

        // Validate ID exists
        if (appointmentId == null || !appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID not found");
        }

        appointments.remove(appointmentId);
    }

    // Updates an existing appointment by replacing it with a new object
    public void updateAppointment(String appointmentId, Date newDate, String newDescription) {

        // Make sure appointment exists
        if (appointmentId == null || !appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID not found");
        }

        // Validate new date
        if (newDate == null || newDate.before(new Date())) {
            throw new IllegalArgumentException("New date must not be null or in the past");
        }

        // Validate new description
        if (newDescription == null || newDescription.length() > 50) {
            throw new IllegalArgumentException("Description must not be null and max 50 characters");
        }

        // Create updated object and replace existing one
        Appointment updatedAppointment = new Appointment(appointmentId, newDate, newDescription);
        appointments.put(appointmentId, updatedAppointment);
    }

    /*
     * Returns a list of appointments sorted by date.
     * Uses built-in sort with a comparator.
     */
    public List<Appointment> getAppointmentsSortedByDate() {

        List<Appointment> list = new ArrayList<>(appointments.values());

        // Sort by date (earliest first)
        list.sort((a, b) -> a.getAppointmentDate().compareTo(b.getAppointmentDate()));

        return list;
    }

    /*
     * Searches appointments by keyword in description.
     * Uses simple linear search.
     */
    public List<Appointment> findAppointmentsByKeyword(String keyword) {

        if (keyword == null) {
            throw new IllegalArgumentException("Keyword cannot be null");
        }

        List<Appointment> results = new ArrayList<>();

        for (Appointment appt : appointments.values()) {
            if (appt.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(appt);
            }
        }

        return results;
    }

    // Returns a single appointment by ID
    public Appointment getAppointment(String appointmentId) {
        return appointments.get(appointmentId);
    }

    // ================= DATABASE ENHANCEMENT =================

    /*
     * Saves appointments to a file.
     * Simulates basic persistence (like a simple database).
     */
    public void saveAppointmentsToFile(String filename) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            for (Appointment appt : appointments.values()) {
                writer.write(appt.getAppointmentId() + ","
                        + appt.getAppointmentDate().getTime() + ","
                        + appt.getDescription());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace(); // simple error handling for now
        }
    }

    /*
     * Loads appointments from a file.
     * Rebuilds objects and stores them in memory.
     */
    public void loadAppointmentsFromFile(String filename) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                // Skip invalid lines
                if (parts.length != 3) {
                    continue;
                }

                String id = parts[0];
                Date date = new Date(Long.parseLong(parts[1]));
                String description = parts[2];

                Appointment appt = new Appointment(id, date, description);
                appointments.put(id, appt);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
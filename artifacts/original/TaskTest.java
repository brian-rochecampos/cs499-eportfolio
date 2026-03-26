package task;

/*
 * Brian Roche-Campos
 * CS 320 - Software Test, Automation QA
 * Date: 9/16/2025
 *
 * Tests for Task class.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void testValidTask() {
        Task task = new Task("12345", "Homework", "Finish math problems");
        assertEquals("12345", task.getTaskId());
        assertEquals("Homework", task.getName());
        assertEquals("Finish math problems", task.getDescription());
    }

    @Test
    void testInvalidTaskId() {
        assertThrows(IllegalArgumentException.class, () -> new Task("12345678910", "Homework", "Too long id"));
        assertThrows(IllegalArgumentException.class, () -> new Task(null, "Homework", "Null id"));
    }

    @Test
    void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> new Task("123", "ThisNameIsWayTooLongForTheLimit", "desc"));
        assertThrows(IllegalArgumentException.class, () -> new Task("123", null, "desc"));
    }

    @Test
    void testInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Task("123", "Homework",
            "This description is way too long and should definitely fail the fifty character limit check"));
        assertThrows(IllegalArgumentException.class, () -> new Task("123", "Homework", null));
    }

    @Test
    void testUpdateNameAndDescription() {
        Task task = new Task("123", "Homework", "Do problems");
        task.setName("Math HW");
        task.setDescription("Finish chapter 4");
        assertEquals("Math HW", task.getName());
        assertEquals("Finish chapter 4", task.getDescription());
    }
}

package task;

/*
 *
 * Tests for Task class.
 * These tests verify constructor validation and setter methods.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void testValidTask() {
        // Arrange & Act: create a valid task
        Task task = new Task("12345", "Homework", "Finish math problems");

        // Assert: verify all fields are correctly assigned
        assertEquals("12345", task.getTaskId());
        assertEquals("Homework", task.getName());
        assertEquals("Finish math problems", task.getDescription());
    }

    @Test
    void testInvalidTaskId() {
        // Assert: task ID longer than 10 characters should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Task("12345678910", "Homework", "Too long id"));

        // Assert: null task ID should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Task(null, "Homework", "Null id"));
    }

    @Test
    void testInvalidName() {
        // Assert: name longer than 20 characters should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Task("123", "ThisNameIsWayTooLongForTheLimit", "desc"));

        // Assert: null name should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Task("123", null, "desc"));
    }

    @Test
    void testInvalidDescription() {
        // Assert: description longer than 50 characters should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Task("123", "Homework",
            "This description is way too long and should definitely fail the fifty character limit check"));

        // Assert: null description should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Task("123", "Homework", null));
    }

    @Test
    void testUpdateNameAndDescription() {
        // Arrange: create a valid task
        Task task = new Task("123", "Homework", "Do problems");

        // Act: update name and description
        task.setName("Math HW");
        task.setDescription("Finish chapter 4");

        // Assert: verify updates were applied
        assertEquals("Math HW", task.getName());
        assertEquals("Finish chapter 4", task.getDescription());
    }
}
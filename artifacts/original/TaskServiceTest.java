package task;

/*
 * Brian Roche-Campos
 * CS 320 - Software Test, Automation QA
 * Date: 9/16/2025
 *
 * Tests for TaskService.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService();
    }

    @Test
    void testAddTask() {
        Task task = new Task("1", "Clean", "Clean the house");
        service.addTask(task);
        assertEquals(task, service.getTask("1"));
    }

    @Test
    void testAddTaskWithDuplicateId() {
        Task task1 = new Task("1", "Clean", "Clean the house");
        Task task2 = new Task("1", "Cook", "Make dinner");
        service.addTask(task1);
        assertThrows(IllegalArgumentException.class, () -> service.addTask(task2));
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("2", "Cook", "Make dinner");
        service.addTask(task);
        service.deleteTask("2");
        assertNull(service.getTask("2"));
    }

    @Test
    void testUpdateTaskName() {
        Task task = new Task("3", "Study", "Read textbook");
        service.addTask(task);
        service.updateTaskName("3", "Review notes");
        assertEquals("Review notes", service.getTask("3").getName());
    }

    @Test
    void testUpdateTaskDescription() {
        Task task = new Task("4", "Workout", "Go jogging");
        service.addTask(task);
        service.updateTaskDescription("4", "Gym session");
        assertEquals("Gym session", service.getTask("4").getDescription());
    }
}

package task;

/*
 *
 * Tests for TaskService.
 * These tests verify adding, deleting, and updating tasks in the service.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    // Service instance used across tests
    private TaskService service;

    // Runs before each test to initialize a fresh TaskService
    @BeforeEach
    void setUp() {
        service = new TaskService();
    }

    @Test
    void testAddTask() {
        // Arrange: create a valid task
        Task task = new Task("1", "Clean", "Clean the house");

        // Act: add task to service
        service.addTask(task);

        // Assert: verify task is stored and retrievable
        assertEquals(task, service.getTask("1"));
    }

    @Test
    void testAddTaskWithDuplicateId() {
        // Arrange: create two tasks with the same ID
        Task task1 = new Task("1", "Clean", "Clean the house");
        Task task2 = new Task("1", "Cook", "Make dinner");

        // Act: add the first task
        service.addTask(task1);

        // Assert: adding a duplicate ID should throw exception
        assertThrows(IllegalArgumentException.class, () -> service.addTask(task2));
    }

    @Test
    void testDeleteTask() {
        // Arrange: add a task to the service
        Task task = new Task("2", "Cook", "Make dinner");
        service.addTask(task);

        // Act: delete the task
        service.deleteTask("2");

        // Assert: task should no longer exist
        assertNull(service.getTask("2"));
    }

    @Test
    void testUpdateTaskName() {
        // Arrange: add a task
        Task task = new Task("3", "Study", "Read textbook");
        service.addTask(task);

        // Act: update the task name
        service.updateTaskName("3", "Review notes");

        // Assert: verify the name was updated
        assertEquals("Review notes", service.getTask("3").getName());
    }

    @Test
    void testUpdateTaskDescription() {
        // Arrange: add a task
        Task task = new Task("4", "Workout", "Go jogging");
        service.addTask(task);

        // Act: update the task description
        service.updateTaskDescription("4", "Gym session");

        // Assert: verify the description was updated
        assertEquals("Gym session", service.getTask("4").getDescription());
    }
}
package task;

/*
 * Manages adding, deleting, and updating tasks (in memory).
 * Uses a HashMap for efficient storage and lookup by task ID.
 */

import java.util.HashMap;
import java.util.Map;

public class TaskService {

    // Stores tasks using taskId as the key
    private Map<String, Task> tasks = new HashMap<>();

    // Adds a new task to the system
    public void addTask(Task task) {

        // Ensure task ID is unique
        if (tasks.containsKey(task.getTaskId()))
            throw new IllegalArgumentException("Task ID already exists");

        // Store the task
        tasks.put(task.getTaskId(), task);
    }

    // Deletes a task by ID
    public void deleteTask(String taskId) {

        // Removes the task if it exists (no exception if not found)
        tasks.remove(taskId);
    }

    // Updates the name of a task
    public void updateTaskName(String taskId, String newName) {

        // Retrieve task by ID
        Task t = tasks.get(taskId);

        // If task exists, update name
        if (t != null) t.setName(newName);
    }

    // Updates the description of a task
    public void updateTaskDescription(String taskId, String newDescription) {

        // Retrieve task by ID
        Task t = tasks.get(taskId);

        // If task exists, update description
        if (t != null) t.setDescription(newDescription);
    }

    // Retrieves a task by ID
    public Task getTask(String taskId) {

        // Returns the task if found, otherwise null
        return tasks.get(taskId);
    }
}
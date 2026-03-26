package task;

/*
 * Brian Roche-Campos
 * CS 320 - Software Test, Automation QA
 * Date: 9/16/2025
 *
 * Manages adding, deleting, and updating tasks (in memory).
 */

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private Map<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId()))
            throw new IllegalArgumentException("Task ID already exists");
        tasks.put(task.getTaskId(), task);
    }

    public void deleteTask(String taskId) {
        tasks.remove(taskId);
    }

    public void updateTaskName(String taskId, String newName) {
        Task t = tasks.get(taskId);
        if (t != null) t.setName(newName);
    }

    public void updateTaskDescription(String taskId, String newDescription) {
        Task t = tasks.get(taskId);
        if (t != null) t.setDescription(newDescription);
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }
}

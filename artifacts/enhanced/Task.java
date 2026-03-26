package task;

/*
 * Task class with ID, name, and description.
 * This class represents a task and enforces validation rules on all fields.
 */
public class Task {

    // Unique identifier for the task (cannot be changed after creation)
    private final String taskId;

    // Name of the task (modifiable, max 20 characters)
    private String name;

    // Description of the task (modifiable, max 50 characters)
    private String description;

    // Constructor: initializes a task and validates all inputs
    public Task(String taskId, String name, String description) {

        // Validate task ID (not null, max length 10)
        if (taskId == null || taskId.length() > 10)
            throw new IllegalArgumentException("Invalid task ID");

        // Validate name (not null, max length 20)
        if (name == null || name.length() > 20)
            throw new IllegalArgumentException("Invalid name");

        // Validate description (not null, max length 50)
        if (description == null || description.length() > 50)
            throw new IllegalArgumentException("Invalid description");

        // Assign validated values
        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    // Getter for task ID
    public String getTaskId() { return taskId; }

    // Getter for task name
    public String getName() { return name; }

    // Getter for task description
    public String getDescription() { return description; }

    // Updates the task name with validation
    public void setName(String name) {
        if (name == null || name.length() > 20)
            throw new IllegalArgumentException("Invalid name");
        this.name = name;
    }

    // Updates the task description with validation
    public void setDescription(String description) {
        if (description == null || description.length() > 50)
            throw new IllegalArgumentException("Invalid description");
        this.description = description;
    }
}
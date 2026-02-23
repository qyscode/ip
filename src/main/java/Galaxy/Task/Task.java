package galaxy.task;

/**
 * Represents a generic task in the Galaxy application.
 * Responsible for representing a TODO task.
 * A Task has a name, completion status, and a task type.
 * Specific task types such as {@code Deadline} and {@code Event}
 * extend this class.
 */
public class Task  {
    private final String taskType;
    private final String taskName;
    private boolean isDone;

    /**
     * Creates a Task with the given name, completion status, and type.
     *
     * @param name The name/description of the task.
     * @param isDone Whether the task is completed.
     * @param taskType The type identifier of the task.
     */
    public Task(String name, boolean isDone, String taskType) {
        this.taskName = name;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Returns the name of this task.
     *
     * @return The task name.
     */
    public String getName() {
        return this.taskName;
    }

    /**
     * Returns the completion indicator of this task.
     *
     * @return {@code "X"} if the task is done, otherwise a blank space.
     */
    public String getCondition() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns whether this task is completed.
     *
     * @return {@code true} if the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the task type identifier.
     *
     * @return The task type.
     */
    public String taskType() {
        return this.taskType;
    }

    /**
     * Sets the completion status of this task.
     *
     * @param bool {@code true} to mark the task as done,
     *             {@code false} otherwise.
     */
    public void setDone(boolean bool) {
        this.isDone = bool;
    }

    /**
     * Returns the string representation of the task
     * in a user-readable format.
     *
     * @return Formatted string representing this task.
     */
    public String toString() {
        return "[" + "T" + "][" + this.getCondition() + "] " + this.getName();
    }

    /**
     * Converts this task into a CSV-formatted string
     * for file storage.
     *
     * @return CSV representation of this task.
     */
    public String toCSV() {
        String isDoneCond = this.isDone() ? "T" : "F";
        return this.taskName + "," + isDoneCond + "," + this.taskType;
    }
}
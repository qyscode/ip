package galaxy.task;

import java.time.LocalDateTime;

/**
 * Represents a task that has a deadline.
 * A Deadline task extends {@code Task} and includes a {@code LocalDateTime}
 * indicating when the task is due.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Creates a Deadline task with the given name and deadline.
     *
     * @param deadlineName The name/description of the task.
     * @param isDone Whether the task is completed.
     * @param taskType The type identifier of the task.
     * @param deadline The date and time by which the task must be completed.
     */
    public Deadline(String deadlineName, boolean isDone, String taskType, LocalDateTime deadline) {
        super(deadlineName, isDone, taskType);
	    this.deadline = deadline;
    }

    /**
     * Returns the deadline of this task.
     *
     * @return The {@code LocalDateTime} representing the deadline.
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the string representation of the Deadline task
     * in a user-readable format.
     *
     * @return Formatted string representing this task.
     */
    @Override
    public String toString() {
        return "[" + "D" + "][" + this.getCondition() + "] " +
                this.getName() + " (by: " + this.getDeadline() + ")";
    }

    /**
     * Converts this task into a CSV-formatted string
     * for file storage. The sequence of the String is
     * designed to suit Storage.java
     *
     * @return CSV representation of this task.
     */
    @Override
    public String toCSV() {
        String isDoneCond = this.isDone() ? "T" : "F";
        return this.getName() + "," + isDoneCond + "," + this.taskType() + "," + this.deadline;
    }
}

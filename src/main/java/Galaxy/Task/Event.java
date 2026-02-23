package galaxy.task;

/**
 * Represents a task that occurs over a specific time period.
 * An Event task extends {@code Task} and includes a start and end time.
 */
public class Event extends Task {
    private final String start;
    private final String end; // as opposed to 'deadline' in Deadline

    /**
     * Creates an Event task with the given name and time period.
     *
     * @param eventName The name/description of the event.
     * @param isDone Whether the task is completed.
     * @param taskType The type identifier of the task.
     * @param end The end time of the event.
     * @param start The start time of the event.
     */
    public Event(String eventName, boolean isDone, String taskType, String end, String start) {
        super(eventName, isDone, taskType);
	this.start = start;
	this.end = end;
    }

    /**
     * Returns the start time of this event.
     *
     * @return The start time as a String.
     */
    public String getStart() {
        return this.start;
    }

    /**
     * Returns the end time of this event.
     *
     * @return The end time as a String.
     */
    public String getEnd() {
        return this.end;
    }

    /**
     * Returns the string representation of the Event task
     * in a user-readable format.
     *
     * @return Formatted string representing this event.
     */
    @Override
    public String toString() {
        return "[" + "E" + "][" + this.getCondition() + "] "
                + this.getName() + " (from: " + this.getStart() + " to: " + this.getEnd() + ")";

    }

    /**
     * Converts this task into a CSV-formatted string
     * for file storage.
     *
     * @return CSV representation of this event.
     */
    @Override
    public String toCSV() {
        String isDoneCond = this.isDone() ? "T" : "F";
        return this.getName() + "," + isDoneCond + "," + this.taskType() +
                "," + this.start + "," + this.end;
    }
}

    

package galaxy.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String deadlineName, boolean isDone, String taskType, LocalDateTime deadline) {
        super(deadlineName, isDone, taskType);
	    this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[" + "D" + "][" + this.getCondition() + "] " +
                this.getName() + " (by: " + this.getDeadline() + ")";
    }

    @Override
    public String toCSV() {
        String isDoneCond = this.isDone() ? "T" : "F";
        return this.getName() + "," + isDoneCond + "," + this.taskType() + "," + this.deadline;
    }
}

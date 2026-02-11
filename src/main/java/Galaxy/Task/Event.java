package galaxy.task;

public class Event extends Task {
    private final String start;
    private final String end; // as opposed to 'deadline' in Deadline

    public Event(String eventName, boolean isDone, String taskType, String end, String start) {
        super(eventName, isDone, taskType);
	this.start = start;
	this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[" + "E" + "][" + this.getCondition() + "] "
                + this.getName() + " (from: " + this.getStart() + " to: " + this.getEnd() + ")";

    }

    @Override
    public String toCSV() {
        String isDoneCond = this.isDone() ? "T" : "F";
        return this.getName() + "," + isDoneCond + "," + this.taskType() +
                "," + this.start + "," + this.end;
    }
}

    

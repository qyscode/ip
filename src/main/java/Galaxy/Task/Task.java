package galaxy.task;

public class Task  {
    private final String taskType;
    private final String taskName;
    private boolean isDone;

    public Task(String name, boolean isDone, String taskType) {
        this.taskName = name;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    public String getName() {
        return this.taskName;
    }

    public String getCondition() {
        return isDone ? "X" : " ";
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String taskType() {
        return this.taskType;
    }

    public void setDone(boolean bool) {
        this.isDone = bool;
    }

    public String toString() {
        return "[" + "T" + "][" + this.getCondition() + "] " + this.getName();
    }
    public String toCSV() {
        String isDoneCond = this.isDone() ? "T" : "F";
        return this.taskName + "," + isDoneCond + "," + this.taskType;
    }
}
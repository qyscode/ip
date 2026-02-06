public class Task  {
    private final String taskType;
    private final String taskName;
    private boolean done;

    public Task(String name, boolean done, String taskType) {
        this.taskName = name;
        this.done = done;
        this.taskType = taskType;
    }

    public String getName() {
        return this.taskName;
    }
    public String getCondition() {
        if (done) {
            return "X";
        } else return " ";
    }

    public void setDone(boolean bool) {
        this.done = bool;
    }

    public String getType() {
        return taskType;
    }
    public String toString() {
        return "[" + "T" + "][" + this.getCondition() + "] " + this.getName();
    }
}
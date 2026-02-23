package galaxy.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for a new, empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for loading existing tasks from storage.
     * @param tasks A list of Task objects loaded from your CSV.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the underlying List for compatibility with your existing Parser.
     * @return The list of tasks.
     */
    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Adds a task to the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the list by index.
     * @param index The zero-based index of the task.
     */
    public void deleteIdx(int index) {
        if (!tasks.isEmpty() && index >= 0 && index < tasks.size()) {
            this.tasks.remove(index);
        } else {
            // This provides a helpful error message for debugging
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + tasks.size());
        }
    }

    public Task getIdx(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets the current number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }


    public TaskList findTasks(String keyword) {
        TaskList results = new TaskList();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.addTask(task);
            }
        }
        return results;
    }

    public void printFoundTasks(String keyword) {
        TaskList matches = findTasks(keyword);
        System.out.println("____________________________________________________________");
        if (matches.getSize() == 0) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int index = 1;
            for (Task task : matches.getList()) {
                System.out.println(index + "." + task.toString());
                index++;
            }
        }
        System.out.println("____________________________________________________________");
    }

}
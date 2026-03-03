package galaxy.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of {@code Task} objects.
 * Provides operations for adding, removing, retrieving,
 * and searching tasks within the list.
 */
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
        assert (task != null) : "This is a null pointer [Task object]"; // AI was used to come up with
        // simple assumptions for assert statements
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

    /**
     * Returns the task at the specified index.
     *
     * @param index The zero-based index of the task.
     * @return The task at the specified index.
     */
    public Task getIdx(int index) {
        assert index >= 0 && index < tasks.size(); // AI was used to come up with
        // simple assumptions for assert statements
        return this.tasks.get(index);
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Finds tasks whose names contain the given keyword.
     * Implemented using .contains()
     *
     * @param keyword The keyword to search for.
     * @return A new {@code TaskList} containing matching tasks.
     */
    public TaskList findTasks(String keyword) {
        assert keyword != null && !keyword.isBlank() : "Keyword should not be null";
        assert keyword.matches("[a-zA-Z]+")
                : "Keyword should contain only alphabets"; // NO spec chars or ints
        // AI was used to come up with simple assumptions for assert statements. In this case, it
        // came up with the regex code for keyword.matches() after being prompted for only alphabets.
        TaskList results = new TaskList();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) { // AI was used to write this line
                // saving the effort to think of the methods needed and their logic
                results.addTask(task);
            }
        }
        return results;
    }

    /**
     * Prints tasks whose names contain the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public String printFoundTasks(String keyword) {
        TaskList matches = findTasks(keyword);
        if (matches.getSize() == 0) {
            return "No matching tasks found.";
        } else {
            String output = "Here are the matching tasks in your list:\n";
            int index = 1;
            for (Task task : matches.getList()) {
                output = output.concat(index + "." + task + "\n");
                index++;
            }
            return output;
        }
    }

}
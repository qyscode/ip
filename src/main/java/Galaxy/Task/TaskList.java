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
     * Adds a task to the list.
     */
    public void addTask(Task task) {
        assert (task != null) : "This is a null pointer [Task object]";
        // AI was used to come up with
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
            throw new IndexOutOfBoundsException("Index " + index +
                    " is out of bounds for size " + tasks.size());
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
     * Searches the TaskList for tasks that contain the specified keyword in their names and
     * formats them into a list, with their corresponding indices for display.
     *
     * @param keyword The alphabetic sequence to search for; must not be null or blank.
     * @return A formatted String of matching tasks, or a "not found" message if no matches exist.
     * @throws AssertionError if the keyword is null, blank, or contains non-alphabetic characters.
     */
    public String printFoundTasks(String keyword) {
        assert keyword != null && !keyword.isBlank()
                : "Keyword should not be null";
        assert keyword.matches("[a-zA-Z]+") // AI used to generate  this regex
                : "Keyword should contain only alphabets"; // NO spec chars or ints

        String output = "Here are the matching tasks in your list:\n";

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.getIdx(i);
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) { // AI was used to write this line
                output = output.concat(String.valueOf(i + 1) + ". " + task + "\n");
            }
        }
        if (output.equals("Here are the matching tasks in your list:\n")) {
            return "No matching tasks found.";
        }
        return output;
    }
}
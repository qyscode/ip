package tools;

import galaxy.task.Deadline;
import galaxy.task.Event;
import galaxy.task.Task;
import galaxy.task.TaskList;

import java.time.LocalDateTime;

/**
 * Parses user input commands and executes the corresponding actions
 * on the TaskList. This class acts as the command dispatcher of the application.
 */
public class Parser {

    /**
     * Prints an error message when the task description is empty.
     * This method is extracted out due to repeated use.
     */
    private static String emptyErrorMsg() {
        return "oops your description cannot be empty";
    }

    /**
     * Prints the updated number of tasks after a modification.
     * This method is extracted out due to repeated use.
     * @param taskList The current task list.
     */
    private static String listCount(TaskList taskList) {
        return "Now you have " + taskList.getSize() + " tasks in the list.";
    }

    /**
     * Parses the given user input and executes the corresponding command.
     *
     * @param args The full user input string.
     * @param taskList The current state of list of tasks.
     * @param csvFileName The file used for persistent storage.
     * @return {@code true} if the program should continue running,
     *         {@code false} if the program should terminate.
     */
    public static String parseCommand(String args, TaskList taskList, String csvFileName) {

        enum Commands {
            BYE,
            LIST,
            DELETE,
            MARK,
            UNMARK,
            TODO,
            DEADLINE,
            EVENT,
            FIND,
            PRIORITY
        }
        String[] parts = args.trim().split("\\s+");
        String firstWord = parts[0].toUpperCase();
        Commands command;
        try {
            command = Commands.valueOf(firstWord);
        } catch (IllegalArgumentException e) {
            // This catches non-existent commands e.g. "hi"
            return "oops i don't understand sia";
        }


        switch(command) {
            case BYE:
                Storage storage = new Storage(csvFileName);
                String writeStatus = storage.writeToCSV(taskList);
                String exitMessage = "Bye. Hope to see you again soon!";
                return writeStatus + "\n" + exitMessage;

            case LIST:
                String output = "Here are the tasks in your list:\n";
                for (int i = 0; i < taskList.getSize(); i++) {
                    output = output.concat((String.valueOf(i + 1)) + "." + taskList.getIdx(i).toString());
                    output = output + "\n";
                }
                return output;

            case DELETE:
                int removalIndex = Integer.parseInt(args.substring(7));
                Task taskRelevant = taskList.getIdx(removalIndex - 1);
                taskList.deleteIdx(removalIndex - 1);
                return "Noted. I've removed this task:\n " + taskRelevant.toString();

            case MARK:
                int idxM = Integer.parseInt(args.substring(5));
                taskList.getIdx(idxM - 1).setDone(true);
                return "Nice! I've marked this task as done:\n [X] " + taskList.getIdx(idxM - 1).getName();

            case UNMARK:
                int idxUm = Integer.parseInt(args.substring(7));
                taskList.getIdx(idxUm - 1).setDone(false);
                return "OK, I've marked this task as not done yet:\n [ ] " +
                        taskList.getIdx(idxUm - 1).getName();

            case TODO:
                String taskName = args.subSequence(4, args.length()).toString().trim();

                if (taskName.isEmpty()) {
                    return emptyErrorMsg();
                }

                Task newTask = new Task(taskName, false, "T");
                taskList.addTask(newTask);

                return "Got it. I've added this task:\n " +
                        newTask + "\n" +
                        listCount(taskList);

            case DEADLINE:
                String deadlineName = args.substring(8, args.indexOf("/")).trim();
                if (deadlineName.isEmpty()) {
                    return emptyErrorMsg();
                }

                String deadline = args.substring(args.indexOf("/by") + 4).trim();
                LocalDateTime dateTime = TimeParser.parseTime(deadline);
                assert dateTime != null : "TimeParser returned null unexpectedly"; // AI was used to come up
                // with simple assumptions for assert statements

                Task newDeadline = new Deadline(deadlineName, false, "D", dateTime);
                taskList.addTask(newDeadline);

                return "Got it. I've added this task:\n " +
                        newDeadline + "\n" + listCount(taskList);

            case EVENT:
                String eventName = args.substring(5, args.indexOf("/")).trim();

                if (eventName.isEmpty()) {
                    return emptyErrorMsg();
                }

                String split = args.substring(args.indexOf("/") + 1).trim();
                String eventStart = split.substring(5, split.indexOf("/") - 1).trim();
                String eventEnd = split.substring(split.indexOf("/") + 4).trim();

                Task newEvent = new Event(eventName, false, "E", eventEnd, eventStart);
                taskList.addTask(newEvent);

                return "Got it. I've added this task:\n " +
                        newEvent + "\n" + listCount(taskList);

            case FIND:
                String keyword = args.substring(5).trim();
                return taskList.printFoundTasks(keyword);

            case PRIORITY:
                if (parts.length < 3) {
                    return "Error, use format: priority <idx> p=<level>'";
                }
                try {
                    // task index
                    int idxP = Integer.parseInt(parts[1]);

                    // Parse the priority level (removes "p=" from "p=1")
                    int p = Integer.parseInt(parts[2].replace("p=", ""));

                    taskList.getIdx(idxP - 1).setPriority(p);

                    Task relTask = taskList.getIdx(idxP - 1);
                    relTask.setPriority(p);
                    return String.format("Priority updated! Task " + relTask.getName()
                            + " is now priority %d.", p);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    return "Error, use format: priority <idx> p=<level>";
                }

            default:
                return "oops i don't understand";
        }
    }


}
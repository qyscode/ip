package tools;

import galaxy.task.Deadline;
import galaxy.task.Event;
import galaxy.task.Task;
import galaxy.task.TaskList;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Parses user input commands and executes the corresponding actions
 * on the TaskList. This class acts as the command dispatcher of the application.
 */
public class Parser {

    /*
     * These are common, repeated output announcements generalized to reduce duplication.
     * emptyErrorMsg: prints a message for when the arg is empty.
     * listCount: prints a message indicating the updated number of tasks (after actions).
     */

    /**
     * Prints an error message when the task description is empty.
     */
    private static void emptyErrorMsg() {
        System.out.println("oops your description cannot be empty");
    }

    /**
     * Prints the updated number of tasks after a modification.
     *
     * @param taskList The current task list.
     */
    private static void listCount(TaskList taskList) {
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
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
    public static boolean parseCommand(String args, TaskList taskList, String csvFileName) {

        enum Commands {
            BYE,
            LIST,
            DELETE,
            MARK,
            UNMARK,
            TODO,
            DEADLINE,
            EVENT,
            FIND
        }

        Commands command = Commands.valueOf(args.trim().split("\\s+")[0].toUpperCase());

        switch(command) {
            case BYE:
                Storage storage = new Storage(csvFileName);
                storage.writeToCSV(taskList);
                System.out.println("____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!" +
                        "____________________________________________________________");
                return false;

            case LIST:
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.getSize(); i++) {
                    System.out.println((i + 1) + "." + taskList.getIdx(i).toString());
                }
                return true;

            case DELETE:
                int removalIndex = Integer.parseInt(args.substring(7));
                Task taskRelevant = taskList.getIdx(removalIndex - 1);
                taskList.deleteIdx(removalIndex - 1);
                System.out.println("Noted. I've removed this task:\n " + taskRelevant.toString());
                return true;

            case MARK:
                int idxM = Integer.parseInt(args.substring(5));
                taskList.getIdx(idxM - 1).setDone(true);
                System.out.println("Nice! I've marked this task as done:\n [X] " + taskList.getIdx(idxM - 1).getName());
                return true;

            case UNMARK:
                int idxUm = Integer.parseInt(args.substring(7));
                taskList.getIdx(idxUm - 1).setDone(false);
                System.out.println("OK, I've marked this task as not done yet:\n [ ] " +
                        taskList.getIdx(idxUm - 1).getName());
                return true;

            case TODO:
                String taskName = args.subSequence(4, args.length()).toString().trim();
                if (taskName.isEmpty()) {
                    emptyErrorMsg();
                }
                Task newTask = new Task(taskName, false, "T");
                taskList.addTask(newTask);
                System.out.println("Got it. I've added this task:\n " +
                        newTask.toString());
                //"[" + "T" + "][" + newTask.getCondition() + "] " + taskName);
                listCount(taskList);
                return true;

            case DEADLINE:
                String deadlineName = args.substring(8, args.indexOf("/")).trim();
                if (deadlineName.isEmpty()) {
                    emptyErrorMsg();
                }

                String deadline = args.substring(args.indexOf("/by") + 4).trim();
                LocalDateTime dateTime = TimeParser.parseTime(deadline);
                if (dateTime == null) { return false; }; // parsing failed

                Task newDeadline = new Deadline(deadlineName, false, "D", dateTime);
                taskList.addTask(newDeadline);
                System.out.println("Got it. I've added this task:\n " +
                        newDeadline.toString());
                //"[" + "D" + "][" + newTask.getCondition() + "] " + deadlineName + " (by: " + deadline + ")");
                listCount(taskList);
                return true;

            case EVENT:
                String eventName = args.substring(5, args.indexOf("/")).trim();
                if (eventName.isEmpty()) {
                    emptyErrorMsg();
                }
                String split = args.substring(args.indexOf("/") + 1).trim();
                String eventStart = split.substring(5, split.indexOf("/") - 1).trim();
                String eventEnd = split.substring(split.indexOf("/") + 4).trim();

                Task newEvent = new Event(eventName, false, "E", eventEnd, eventStart);
                taskList.addTask(newEvent);
                System.out.println("Got it. I've added this task:\n " +
                        newEvent.toString());
                //"[" + "D" + "][" + newEvent.getCondition() + "] " + taskName + " (by: " + deadline + ")");
                listCount(taskList);
                return true;

            case FIND:
                String keyword = args.substring(5).trim();
                taskList.printFoundTasks(keyword);
                return true;

            default:
                System.out.println("oops i don't understand");
                return true;
        }
    }


}
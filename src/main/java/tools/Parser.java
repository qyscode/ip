package tools;



//(A-Enums)) and  (Level-7), (A-Packages), (Level-8)


import galaxy.task.Deadline;
import galaxy.task.Event;
import galaxy.task.Task;

import java.time.LocalDateTime;
import java.util.List;




public class Parser {

    /**
     * These are common, repeated output announcements generalized to reduce duplication.
     *
     * emptyErrorMsg: prints a message for when the arg is empty.
     * listCount: prints a message indicating the updated number of tasks (after actions).
     */
    private static void emptyErrorMsg() {
        System.out.println("oops your description cannot be empty");
    }
    private static void listCount(List<Task> arrayList) {
        System.out.println("Now you have " + arrayList.size() + " tasks in the list.");
    }

    /**
     * Parses the input commands and decides the logic with which to proceed.
     * Returns a boolean indicating whether the app should continue running. (Continue if: true)
     *
     * @param args input String with commands.
     * @param taskList the current state of list of Task objects.
     * @param csvFileName the desired file name of the csv file where data will be stored.
     * @return whether the program should continue.
     */
    public static boolean parseCommand(String args, List<Task> taskList, String csvFileName) {

        enum Commands {
            BYE,
            LIST,
            DELETE,
            MARK,
            UNMARK,
            TODO,
            DEADLINE,
            EVENT
        }

        Commands command = Commands.valueOf(args.trim().split("\\s+")[0].toUpperCase());

        switch(command) {
            case BYE:
                Storage.writeToCSV(csvFileName, taskList);
                System.out.println("____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!" +
                        "____________________________________________________________");
                return false;

            case LIST:
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + "." + taskList.get(i).toString());
                }
                return true;

            case DELETE:
                int removalIndex = Integer.parseInt(args.substring(7));
                Task taskRelevant = taskList.get(removalIndex - 1);
                taskList.remove(removalIndex - 1);
                System.out.println("Noted. I've removed this task:\n " + taskRelevant.toString());
                return true;

            case MARK:
                int idxM = Integer.parseInt(args.substring(5));
                taskList.get(idxM - 1).setDone(true);
                System.out.println("Nice! I've marked this task as done:\n [X] " + taskList.get(idxM - 1).getName());
                return true;

            case UNMARK:
                int idxUm = Integer.parseInt(args.substring(7));
                taskList.get(idxUm - 1).setDone(false);
                System.out.println("OK, I've marked this task as not done yet:\n [ ] " + taskList.get(idxUm - 1).getName());
                return true;

            case TODO:
                String taskName = args.subSequence(4, args.length()).toString().trim();
                if (taskName.isEmpty()) {
                    emptyErrorMsg();
                }
                Task newTask = new Task(taskName, false, "T");
                taskList.add(newTask);
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
                taskList.add(newDeadline);
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
                taskList.add(newEvent);
                System.out.println("Got it. I've added this task:\n " +
                        newEvent.toString());
                //"[" + "D" + "][" + newEvent.getCondition() + "] " + taskName + " (by: " + deadline + ")");
                listCount(taskList);
                return true;

            default:
                System.out.println("oops i don't understand");
                return true;
        }
    }


}
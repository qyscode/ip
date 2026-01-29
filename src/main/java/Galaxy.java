import java.util.*;

public class Galaxy {

	// To follow given inheritance pattern
	/*
	public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
	 */

	// To use polymorphism
	/* Task[] tasks = new Task[100];
	 tasks[0] = new Deadline("return book", "Monday");*/

	public static class Task  {
		private final String taskType;
		private final String name;
		private boolean done;

		public Task(String name, boolean done, String taskType) {
			this.name = name;
			this.done = done;
			this.taskType = taskType;
		}

		public String getName() {
			return name;
		}
		public String getCondition() {
			if (done) {
				return "X";
			} else return " ";
		}
		public String getType() {
			return taskType;
		}
		public String toString() {
			return "[" + "T" + "][" + this.getCondition() + "] " + this.getName();
		}
	}

	public static class Deadline extends Task {
		private final String deadline;

		public Deadline(String name, boolean done, String taskType, String deadline) {
			super(name, done, taskType);
			this.deadline = deadline;
		}
		public String getDeadline() {
			return this.deadline;
		}

		public String toString() {
			return "[" + "D" + "][" + this.getCondition() + "] " +
					this.getName() + " (by: " + this.getDeadline() + ")";
		}
	}

	public static class Event extends Deadline {
		private final String start;

		public Event(String name, boolean done, String taskType, String deadline, String start) {
			super(name, done, taskType, deadline);
			this.start = start;
		}

		public String getStart() {
			return this.start;
		}
		public String toString() {
			return "[" + "E" + "][" + this.getCondition() + "] " +
					this.getName() + " (from: " + this.getStart() + " to: " + this.getDeadline() + ")";
		}
	}

	public static void main(String[] args) {
		System.out.println("____________________________________________________________");
 		System.out.println("Hello! I'm Galaxy");
		System.out.println("What can I do for you?");
		Scanner scanner = new Scanner(System.in);
		List<Task> arrayList = new ArrayList<>();

		while (true) {
			String target = scanner.nextLine(); // Read a line of text input

			if (target.equals("bye")) {
				System.out.println("____________________________________________________________\nBye. Hope to see you again soon!");
				System.out.println("____________________________________________________________");
				break;
			} else if (target.equals("list")) {
				System.out.println("Here are the tasks in your list:");
				for (int i = 0; i < arrayList.size(); i++) {
					System.out.println((i + 1) + "." + arrayList.get(i).toString());
				}

			} else if (target.startsWith("delete")) {
				int removalIndex = Integer.parseInt(target.substring(7));
				Task taskRelevant = arrayList.get(removalIndex-1);
				arrayList.remove(removalIndex-1);
				System.out.println("Noted. I've removed this task:\n " + taskRelevant.toString());

			} else if (target.startsWith("mark")) {
				int num = Integer.parseInt(target.subSequence(5,target.length()).toString());
				arrayList.get(num-1).done = true;
				System.out.println("Nice! I've marked this task as done:\n [X] " + arrayList.get(num-1).getName());

			} else if (target.startsWith("unmark")) {
				int num = Integer.parseInt(target.subSequence(7,target.length()).toString());
				arrayList.get(num-1).done = false;
				System.out.println("OK, I've marked this task as not done yet:\n [ ] " + arrayList.get(num-1).getName());

			} else if (target.startsWith("todo")) {
				String taskName = target.subSequence(5,target.length()).toString();
				Task newTask = new Task(taskName, false, "T");
				arrayList.add(newTask);
				System.out.println("Got it. I've added this task:\n " +
						newTask.toString());
						//"[" + "T" + "][" + newTask.getCondition() + "] " + taskName);
				listCount(arrayList);

			} else if (target.startsWith("deadline")) {
				String taskName = target.substring(9, target.indexOf("/")).trim();
				String deadline = target.substring(target.indexOf("/")+4).trim();
				Task newTask = new Deadline(taskName, false, "D", deadline);
				arrayList.add(newTask);
				System.out.println("Got it. I've added this task:\n " +
						newTask.toString());
						//"[" + "D" + "][" + newTask.getCondition() + "] " + taskName + " (by: " + deadline + ")");
				listCount(arrayList);
			} else if (target.startsWith("event")) {
				String taskName = target.substring(6, target.indexOf("/")).trim();

				String split = target.substring(target.indexOf("/")+1).trim();
				String start = split.substring(5,split.indexOf("/")-1).trim();

				String deadline = split.substring(split.indexOf("/")+4).trim();

				Task newTask = new Event(taskName, false, "E", deadline, start);
				arrayList.add(newTask);
				System.out.println("Got it. I've added this task:\n " +
						newTask.toString());
				//"[" + "D" + "][" + newTask.getCondition() + "] " + taskName + " (by: " + deadline + ")");
				listCount(arrayList);
			} else {
				System.out.println("What you saying sia");
			}

		}
		scanner.close();
	}

	/* This deals with the repetitive ending message */
	public static void listCount(List<Task> arrayList) {
		System.out.println("Now you have " + arrayList.size() + " tasks in the list.");
	}
}



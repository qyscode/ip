import java.util.*;



public class Galaxy {
	public static class Task {
		private final String name;
		private boolean done;

		public Task(String name, boolean done) {
			this.name = name;
			this.done = done;
		}

		public String getName() {
			return name;
		}
		public String getCondition() {
			if (done) {
				return "X";
			} else return " ";
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
				for (int i = 0; i < arrayList.size(); i++) {
					System.out.println((i + 1) + ".[" + arrayList.get(i).getCondition() + "] " + arrayList.get(i).getName());

				}
			} else if (target.startsWith("mark")) {
				int num = Integer.parseInt(target.subSequence(5,target.length()).toString());
				arrayList.get(num-1).done = true;
				System.out.println("Nice! I've marked this task as done:\n [X] " + arrayList.get(num-1).getName());

			} else if (target.startsWith("unmark")) {
				int num = Integer.parseInt(target.subSequence(7,target.length()).toString());
				arrayList.get(num-1).done = false;
				System.out.println("OK, I've marked this task as not done yet:\n [ ] " + arrayList.get(num-1).getName());

			} else {
				arrayList.add(new Task(target, false));
				System.out.println("added: " + target);
			}

		}
		scanner.close();

	}
}



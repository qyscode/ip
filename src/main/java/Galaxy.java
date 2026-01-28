import java.util.*;

public class Galaxy {
	public static void main(String[] args) {
		System.out.println("____________________________________________________________");
 		System.out.println("Hello! I'm Galaxy");
		System.out.println("What can I do for you?");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String target = scanner.nextLine(); // Read a line of text input
			if (!target.equals("bye")) {
				System.out.println(target);
			} else {
				System.out.println("____________________________________________________________\nBye. Hope to see you again soon!");
				System.out.println("____________________________________________________________");
				break;
			}

		}
		scanner.close();

	}
}


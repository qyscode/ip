import java.util.*;

public class Galaxy {
	public static void main(String[] args) {
		System.out.println("____________________________________________________________");
 		System.out.println("Hello! I'm Galaxy");
		System.out.println("What can I do for you?");
		Scanner scanner = new Scanner(System.in);
		List<String> arrayList = new ArrayList<>();
		while (true) {
			String target = scanner.nextLine(); // Read a line of text input

			if (target.equals("bye")) {
				System.out.println("____________________________________________________________\nBye. Hope to see you again soon!");
				System.out.println("____________________________________________________________");
				break;
			} else if (target.equals("list")) {
				for (int i=0; i<arrayList.size(); i++) {
					System.out.println((i+1) + ". " + arrayList.get(i));

				}
			} else {
				arrayList.add(target);
				System.out.println("added: " + target);
			}

		}
		scanner.close();

	}
}


package galaxy.task;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import tools.Storage;
import tools.Parser;


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

	public static void main(String[] args) {
		List<Task> taskList = new ArrayList<>();
		String csvFileName = "app-data.csv"; // name of file where data is saved

		// -- INIT --
		//load Data from CSV
		Storage.readCSV("ip/src/main/data/" + csvFileName, taskList); //load data

		// Startup message
		System.out.println("____________________________________________________________");
 		System.out.println("Hello! I'm Galaxy");
		System.out.println("I can save all the tasks in the galaxy. What can I do for you?");

		Scanner scanner = new Scanner(System.in);
		boolean takingInputs = true;
		while (takingInputs) {
			String target = scanner.nextLine(); // Read a line of text input
			takingInputs = Parser.parseCommand(target, taskList, csvFileName);
			// parseCommand will return false where appropriate to end the program
		}
		scanner.close();
	}
}



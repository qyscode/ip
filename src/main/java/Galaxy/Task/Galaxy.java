package galaxy.task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import galaxy.exceptions.DataConversionException;
import tools.Storage;
import tools.Parser;
import tools.Ui;

// AI was used to write first iteration of Javadoc comments before I check them.
/**
 * Main entry point of the Galaxy task management application.
 * This class initializes storage, loads saved tasks,
 * and starts the command loop.
 */
public class Galaxy {
	private TaskList tasks;
	private final String finalFilePath;

	/**
	 * Creates a new Galaxy application instance.
	 * Loads existing tasks from the specified file path.
	 *
	 * @param filePath The path of the CSV file used for persistent storage.
	 */
	public Galaxy(String filePath) {
		finalFilePath = filePath;
		Ui ui = new Ui();
		Storage storage = new Storage(filePath);
		// Startup message
		System.out.println("____________________________________________________________");
		System.out.println("Hello! I'm Galaxy");
		System.out.println("I can save all the tasks in the galaxy. What can I do for you?");
		try {
			tasks = new TaskList();
			//load Data from CSV
			storage.readCSV(filePath, tasks); //load data
		} catch (FileNotFoundException fe) {
			System.out.println("Error: File not found.");
			fe.printStackTrace();
		} catch (IOException | DataConversionException e) {
			// For "cannot read file" issues
			ui.showLoadingError();
			this.tasks = new TaskList(); // Fallback to empty list
		}
	}

	/**
	 * Generates a response for the user's chat message.
	 */
	public String getResponse(String input) {
		try {
			return Parser.parseCommand(input, tasks, finalFilePath);
		} catch (Exception e) {
			return "Oops! " + e.getMessage();
		}
	}


	/*
	 * Starts the main program loop.
	 * Continuously reads user input and processes commands
	 * until termination is requested.

	 **Deprecated CLI Logic**

	public void run() {
		Scanner scanner = new Scanner(System.in);
		boolean takingInputs = true;
		while (takingInputs) {
			String target = scanner.nextLine(); // Read a line of text input
			takingInputs = Parser.parseCommand(target, tasks, finalFilePath);
			// parseCommand will return false where appropriate to end the program
		}
		scanner.close();
	}*/
}



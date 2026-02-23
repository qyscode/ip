package galaxy.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import galaxy.exceptions.DataConversionException;
import tools.Storage;
import tools.Parser;
import tools.Ui;

public class Galaxy {
	private TaskList tasks;
	private final String staticFilePath;

	public static void main(String[] args) {
		new Galaxy("src/main/data/" + "app-data.csv").run();
	}

	public Galaxy(String filePath) {
		staticFilePath = filePath;
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
		} catch (IOException | DataConversionException e) {
			// For "cannot read file" issues
			ui.showLoadingError();
			this.tasks = new TaskList(); // Fallback to empty list
		}
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		boolean takingInputs = true;
		while (takingInputs) {
			String target = scanner.nextLine(); // Read a line of text input
			takingInputs = Parser.parseCommand(target, tasks, staticFilePath);
			// parseCommand will return false where appropriate to end the program
		}
		scanner.close();
	}
}



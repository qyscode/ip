package galaxy.task;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import tools.Storage;
import tools.Parser;
import tools.Ui;


public class Galaxy {
    private TaskList tasks;
	private Ui ui;
	private final String staticFilePath;

	public Galaxy(String filePath) {
		staticFilePath = filePath;
		String csvFileName = "app-data.csv"; // name of file where data is saved
		Ui ui = new Ui();
        Storage storage = new Storage(filePath);
		try {
			tasks = new TaskList();
			//load Data from CSV
			//Storage.readCSV("ip/src/main/data/" + csvFileName, taskList); //load data
			storage.readCSV(filePath, tasks); //load data
		} catch (Exception e) {
			// ************** FIX THIS EXCEPTION *******************
			ui.showLoadingError();
			tasks = new TaskList();
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

	public static void main(String[] args) {
		new Galaxy("ip/src/main/data/" + "app-data.csv").run();
	}
}
	// ********************8

/*
	public static void main(String[] args) {


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
}*/



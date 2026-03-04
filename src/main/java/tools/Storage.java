package tools;

import galaxy.exceptions.DataConversionException;
import galaxy.task.Deadline;
import galaxy.task.Task;
import galaxy.task.Event;
import galaxy.task.TaskList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.File;

import java.util.Objects;

import static tools.TimeParser.parseTime;

/**
 * Handles reading from and writing to the CSV file
 * for persistent storage of tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Initializes/Creates a Storage instance with the specified file path (Note: Final).
     *
     * @param filePath The path of the CSV file used for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the current tasks to a CSV file.
     *
     * @param taskList The list of tasks to be written.
     */
    // note that fileNotFound does not propagate
    public String writeToCSV(TaskList taskList) {
        try (PrintWriter pw = new PrintWriter(this.filePath)) {

            for (int i = 0; i < taskList.getSize(); i++) {
                // iterates through each task and prints each task data
                // as comma separated values
                pw.println(taskList.getIdx(i).toCSV());
            }

            return "Save completed. Data saved to CSV.";

        } catch (FileNotFoundException fe) {
            // body of catch block written by AI
            // prompt: "String output = "Error: File not found.\n" + e.printStackTrace();"
            // asking AI to convert printStackTrace() to String suitable format for output
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            fe.printStackTrace(pw);
            return "Error: File not found.\n" + sw;

        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    /**
     * Reads tasks from a CSV file and loads them into the given TaskList.
     * If the file or its parent directory does not exist,
     * they will be created automatically.
     *
     * @param csvFileName The name of the CSV file.
     * @param taskList The TaskList to populate with loaded tasks.
     * @throws IOException If an I/O error occurs.
     * @throws DataConversionException If the data format is invalid.
     */
    public String readCSV(String csvFileName, TaskList taskList) throws IOException, DataConversionException {
        String response = "";

        try {
            File file = new File(csvFileName);

            // Ensure parent folder exists
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                boolean created = parentDir.mkdirs();
                if (created) {
                    response = response.concat("Created missing folder: " + parentDir.getAbsolutePath()+"\n");
                }
            }

            // Ensure file exists
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    response = response.concat("Created new empty file: " + file.getAbsolutePath()+"\n");
                }
            }

            // Read CSV safely
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // process each line
                    String[] row = line.split(","); // simple CSV parsing

                    assert row.length >= 3 && row.length < 6 : "Task is stored incorrectly. Number of arguments" +
                            "does not match"; // AI was used to come up with simple assumptions for assert statements
                    int rowLength = row.length;
                    boolean isDoneCond = Objects.equals(row[1], "T");
                    if (rowLength == 3) { // a To-Do object
                        taskList.addTask(new Task(row[0], isDoneCond, row[2]));
                    } else if (rowLength == 4) { // a Deadline object
                        taskList.addTask(new Deadline(row[0], isDoneCond, row[2], parseTime(row[3])));
                    } else if (rowLength == 5) { // a Event object
                        taskList.addTask(new Event(row[0], isDoneCond, row[2], row[3], row[4]));
                    } else {
                        throw new IllegalArgumentException("Invalid number of arguments: " + row.length);
                    }
                }
            }

            response = response.concat("Data loaded from CSV: " + file.getAbsolutePath()+"\n");

        } catch (IOException e) {
            response = response.concat("Error accessing the CSV file.");
            e.printStackTrace();
        }
        return response;
    }



}
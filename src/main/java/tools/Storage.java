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
import java.io.File;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static tools.TimeParser.parseTime;

/**
 * Handles reading from and writing to the CSV file
 * for persistent storage of tasks.
 */
public class Storage {
    private String filePath;

    /**
     * Test method for experimenting with CSV writing.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        //String csvFileName = "app-data.csv"; // name of file where data is saved
        //writeToCSV(csvFileName);
        // Sample data: a list of string arrays, each representing a row
        List<String[]> data = Arrays.asList(
                new String[]{"Name", "Age", "City"},
                new String[]{"John Doe", "30", "New York"},
                new String[]{"Jane Smith", "25", "London"},
                new String[]{"Bob Johnson", "35", "Paris"}
        );

        // Use try-with-resources for automatic resource management
        /*
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFileName))) {
            for (String[] row : data) {
                // Join array elements with a comma (',') delimiter
                String csvLine = String.join(",", row);
                bw.write(csvLine);
                bw.newLine(); // Add a newline character after each record
            }
            System.out.println("CSV file written successfully: " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Creates a Storage instance for the specified file path.
     *
     * @param filePath The path of the CSV file used for storage.
     */
    public Storage(String filePath) {
        // initialise the Storage
        this.filePath = filePath;
    }

    /**
     * Writes the current tasks to a CSV file.
     *
     * @param taskList The list of tasks to be written.
     */
    public void writeToCSV(TaskList taskList) {
        // note that fileNotFound does not propagate
        try (PrintWriter pw = new PrintWriter(this.filePath)) {
             /* "ip/src/main/data/" + csvFileName)) { */

            for (int i = 0; i < taskList.getSize(); i++) {
                pw.println(taskList.getIdx(i).toCSV());
                //System.out.println((i + 1) + "." + taskList.get(i).toString());
            }
            System.out.println("Save completed. Data saved to csv.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
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
    public void readCSV(String csvFileName, TaskList taskList) throws IOException, DataConversionException {
        try {
            File file = new File(csvFileName);
            System.out.println(file.getAbsolutePath());
            // Ensure parent folder exists
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                boolean created = parentDir.mkdirs();
                if (created) {
                    System.out.println("Created missing folder: " + parentDir.getAbsolutePath());
                }
            }

            // Ensure file exists
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("Created new empty file: " + file.getAbsolutePath());
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

            System.out.println("Data loaded from CSV: " + file.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error accessing the CSV file.");
            e.printStackTrace();
        }
    }



}
package tools;

import galaxy.task.Deadline;
import galaxy.task.Task;
import galaxy.task.Event;

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

public class Storage {
    public static void main(String[] args) {

        //String csvFileName = "app-data.csv";
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

    public static void writeToCSV(String csvFileName, List<Task> taskList) {
        // note that fileNotFound does not propagate
        try (PrintWriter pw = new PrintWriter("ip/src/main/data/" + csvFileName)) {

            for (int i = 0; i < taskList.size(); i++) {
                pw.println(taskList.get(i).toCSV());
                //System.out.println((i + 1) + "." + taskList.get(i).toString());
            }
            System.out.println("Save completed. Data saved to csv.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
    }

    public static void readCSV(String csvFileName, List<Task> taskList) {
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
                    // TODO: convert row into Task objects or store in a list

                    int rowLength = row.length;
                    boolean isDoneCond = Objects.equals(row[1], "T");
                    if (rowLength == 3) { // a To-Do object
                        taskList.add(new Task(row[0], isDoneCond, row[2]));
                    } else if (rowLength == 4) { // a Deadline object
                        taskList.add(new Deadline(row[0], isDoneCond, row[2], parseTime(row[3])));
                    } else if (rowLength == 5) { // a Event object
                        taskList.add(new Event(row[0], isDoneCond, row[2], row[3], row[4]));
                    } else {
                        throw new IllegalArgumentException("Invalid number of arguments: " + row.length);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found.");
                e.printStackTrace();
            } catch (IOException e) { //IO is a parent of fileNotFound
                System.out.println("Error: File could not be closed.");
                e.printStackTrace();
            }

            System.out.println("Data loaded from CSV: " + file.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error accessing the CSV file.");
            e.printStackTrace();
        }
    }





}
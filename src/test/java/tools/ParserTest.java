package tools;

import galaxy.task.TaskList;
import galaxy.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {
    private TaskList taskList;
    private final String dummyCsv = "test.csv";

    @BeforeEach
    void setUp() {
        // Start with a fresh, empty list before every test
        taskList = new TaskList();
    }

    @Test
    void parseCommand_todo_addsTaskToList() {
        // Act: Run the TODO command
        boolean shouldContinue = Parser.parseCommand("todo read book", taskList, dummyCsv);

        // Assert:
        assertTrue(shouldContinue, "Program should continue after a todo command");
        assertEquals(1, taskList.getSize(), "TaskList should have 1 task");
        assertEquals("read book", taskList.getIdx(0).getName());
    }

    @Test
    void parseCommand_mark_updatesTaskStatus() {
        // Arrange: Add a task first
        taskList.addTask(new Task("run", false, "T"));

        // Act: Mark task 1 as done
        Parser.parseCommand("mark 1", taskList, dummyCsv);

        // Assert:
        assertTrue(taskList.getIdx(0).getCondition().equals("X"), "Task should be marked with X");
    }

    @Test
    void parseCommand_bye_returnsFalse() {
        // Act: Run the BYE command
        boolean shouldContinue = Parser.parseCommand("bye", taskList, dummyCsv);

        // Assert:
        assertFalse(shouldContinue, "Program should stop (return false) after 'bye'");
    }
}
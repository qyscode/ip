package tools;

import galaxy.task.TaskList;
import galaxy.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        // Act: Run the "TODO" command
        String result = Parser.parseCommand("todo read book", taskList, dummyCsv);

        // Assert:
        assertEquals(1, taskList.getSize(), "TaskList should have 1 task");
        assertEquals("read book", taskList.getIdx(0).getName());
        assertTrue(result.contains("Got it. I've added this task:"));
        assertTrue(result.contains("read book"));
        assertTrue(result.contains("Now you have"));
    }

    @Test
    public void parseCommand_todoEmptyDescription_returnsError() {
        String result = Parser.parseCommand("todo   ", taskList, "data.csv");

        assertEquals(0, taskList.getSize());
        assertEquals("oops your description cannot be empty", result);
    }

    @Test
    void parseCommand_mark_updatesTaskStatus() {
        taskList.addTask(new Task("run", false, "T"));

        // Act: Mark task 1 as done
        String result = Parser.parseCommand("mark 1", taskList, dummyCsv);

        // Assert:
        assertTrue(taskList.getIdx(0).isDone());
        assertEquals("X", taskList.getIdx(0).getCondition(), "Task should be marked with X");
        assertTrue(result.contains("Nice! I've marked this task as done:"));
    }


    @Test
    void parseCommand_unknownCommand_throwsException() {
        // This will fail (throw exception) because "BLAH" is not in the Enum
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseCommand("hello", taskList, "test.csv");
        });
    }
}
package galaxy.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    @Test
    void deleteIdx_validIndex_removesCorrectTask() {
        TaskList list = new TaskList();
        list.addTask(new Task("Task 1", false, "T"));
        list.addTask(new Task("Task 2", false, "T"));

        list.deleteIdx(0); // Delete "Task 1"
        assertEquals(1, list.getSize());
        assertEquals("Task 2", list.getIdx(0).getName(), "The remaining task should be Task 2.");
    }

    @Test
    void deleteIdx_negativeIndex_throwsException() {
        TaskList list = new TaskList();
        list.addTask(new Task("Task 1", false, "T"));

        // Assert that calling -1 throws the appropriate exception
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.deleteIdx(-1);
        });
    }

    @Test
    void deleteIdx_indexEqualsSize_throwsException() {
        TaskList list = new TaskList();
        list.addTask(new Task("Task 1", false, "T"));
        list.addTask(new Task("Task 2", false, "T"));

        int size = list.getSize(); // This is 2

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.deleteIdx(size); // Index 2 does not exist
        });
    }

    @Test
    void deleteIdx_emptyList_throwsException() {
        TaskList list = new TaskList(); // List is empty

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.deleteIdx(0);
        });
    }

    @Test
    void getIdx_emptyList_throwsException() {
        TaskList list = new TaskList();
        // Testing a "boundary case": getting an item from an empty list
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.getIdx(0);
        }, "Should throw exception when accessing empty list.");
    }
    @Test
    void getIdx_negativeIndex_throwsException() {
        TaskList list = new TaskList();
        list.addTask(new Task("Task 1", false, "T"));

        // Assert that calling -1 throws the appropriate exception
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.getIdx(-1);
        });
    }

    @Test
    void getIdx_indexEqualsSize_throwsException() {
        TaskList list = new TaskList();
        list.addTask(new Task("Task 1", false, "T"));
        list.addTask(new Task("Task 2", false, "T"));

        int size = list.getSize(); // This is 2

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.getIdx(size); // Index 2 does not exist
        });
    }

}
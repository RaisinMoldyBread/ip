package raisinchat.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import raisinchat.Parser;
import raisinchat.Storage;
import raisinchat.TestFixtures;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.task.Todo;
import raisinchat.ui.Ui;

public class UnmarkCommandTest {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        ui = TestFixtures.ui();
        storage = TestFixtures.storage();

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("homework", true));
        tasks = new TaskList(taskList);
    }

    @Test
    public void unmark_validIndex_taskUnmarkedCorrectly() throws RaisinChatException {
        Command c = Parser.parse("unmark 1");

        assertDoesNotThrow(() -> c.execute(tasks, ui, storage));

        assertEquals("T | 0 | homework", tasks.getTasks(0).toString());
    }

    @Test
    public void unmark_missingIndex_throwsException() throws RaisinChatException {
        Command c = Parser.parse("unmark");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Hmm, you are doing it wrong! Use command like this: unmark <indexOfTask>",
                e.getMessage()
        );
    }

    @Test
    public void unmark_invalidIndex_throwsException() throws RaisinChatException {
        Command c = Parser.parse("unmark 999");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Such task index does not exist! Please check the list again!",
                e.getMessage()
        );
    }

    @Test
    public void unmark_nonNumericIndex_throwsException() throws RaisinChatException {
        Command c = Parser.parse("unmark one");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals("Task index must be a number!", e.getMessage());
    }

    @Test
    public void unmarkCommand_isNotExitCommand() throws RaisinChatException {
        Command c = Parser.parse("unmark 1");

        assertFalse(c.isExit());
    }
}

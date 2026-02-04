package raisinchat.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import raisinchat.Parser;
import raisinchat.Storage;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.task.Todo;
import raisinchat.ui.Ui;

public class MarkCommandTest {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Initialises tests' common assets
     *
     */
    @BeforeEach
    public void setUp() {
        ui = new Ui();
        storage = new Storage("./data/RaisinChatTaskTestDb.txt");

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("homework", false));
        tasks = new TaskList(taskList);
    }

    /**
     * Test for mark command if inputs are properly parsed and added to task list
     *
     */
    @Test
    public void mark_validIndex_taskMarkedCorrectly() throws RaisinChatException {
        Command c = Parser.parse("mark 1");

        c.execute(tasks, ui, storage);

        assertEquals(
                "T | 1 | homework",
                tasks.getTasks(0).toString()
        );
    }

    /**
     * Test for mark command when index is not valid
     *
     */
    @Test
    public void mark_invalidIndex_throwsException() throws RaisinChatException {
        Command c = Parser.parse("mark 100000");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Such task index does not exist! Please check the list again!",
                e.getMessage()
        );
    }

    /**
     * Test for mark command when index is not a number
     *
     */
    @Test
    public void mark_nonNumericIndex_throwsException() throws RaisinChatException {
        Command c = Parser.parse("mark ten");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Task index must be a number!",
                e.getMessage()
        );
    }

    /**
     * Test for mark command and ensure that mark is not a command that causes the program to exit
     *
     */
    @Test
    public void markCommand_isNotExitCommand() throws RaisinChatException {
        Command c = Parser.parse("mark 1");

        assertFalse(c.isExit());
    }
}

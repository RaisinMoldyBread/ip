package raisinchat.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import raisinchat.Parser;
import raisinchat.Storage;
import raisinchat.TestFixtures;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

public class TodoCommandTest {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        ui = TestFixtures.ui();
        tasks = TestFixtures.emptyTaskList();
        storage = TestFixtures.storage();
    }

    @Test
    public void todo_validInput_taskAddedCorrectly() throws RaisinChatException {
        Command c = Parser.parse("todo read book");

        assertDoesNotThrow(() -> c.execute(tasks, ui, storage));

        assertEquals(1, tasks.size());
        assertEquals("T | 0 | read book", tasks.getTask(0).toString());
    }

    @Test
    public void todo_missingTaskName_throwsException() throws RaisinChatException {
        Command c = Parser.parse("todo");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Hmm, you are doing it wrong! Use command like this: todo <taskName>",
                e.getMessage()
        );
    }

    @Test
    public void todoCommand_isNotExitCommand() throws RaisinChatException {
        Command c = Parser.parse("todo read book");

        assertFalse(c.isExit());
    }
}

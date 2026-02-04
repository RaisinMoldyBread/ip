package raisinchat.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import raisinchat.Parser;
import raisinchat.Storage;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

public class DeadlineCommandTest {


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
        tasks = new TaskList(null);
        storage = new Storage("./data/RaisinChatTaskTestDb.txt");
    }

    /**
     * Test for standard deadline command if inputs are properly parsed and added to task list
     *
     */
    @Test
    public void deadline_validInput_taskAddedCorrectly() throws RaisinChatException {
        Command c = Parser.parse("deadline finish stats /by 2026-01-03 11:00 PM");

        c.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals(
                "D | 0 | finish stats | Jan 3 2026 11:00 pm",
                tasks.getTasks(0).toString()
        );
    }

    /**
     * Test for missing task name, should assert thrown error of help command
     *
     */
    @Test
    public void deadline_missingTaskName_throwsException() throws RaisinChatException {
        Command c = Parser.parse("deadline /by 2026-01-03 11:00 PM");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Hmm, you are doing it wrong! Use command like this: "
                        + "deadline <taskName> /by <yyyy-MM-dd hh:mm AM/PM>",
                e.getMessage()
        );
    }

    /**
     * Test for incomplete deadline datetime, should assert thrown error of help command
     *
     */
    @Test
    public void deadline_incompleteDateTime_throwsException() throws RaisinChatException {
        Command c = Parser.parse("deadline finish stats /by 11:00 PM");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Hmm, you are doing it wrong! Use command like this: "
                        + "deadline <taskName> /by <yyyy-MM-dd hh:mm AM/PM>",
                e.getMessage()
        );
    }

    /**
     * Test for deadline command and ensure that mark is not a command that causes the program to exit
     *
     */
    @Test
    public void deadlineCommand_isNotExitCommand() throws RaisinChatException {
        Command c = Parser.parse("deadline finish stats /by 2026-01-03 11:00 PM");

        assertFalse(c.isExit());
    }
}

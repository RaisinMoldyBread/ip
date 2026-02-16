package raisinchat.command;

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

public class EventCommandTest {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Initialises tests' common assets
     *
     */
    @BeforeEach
    public void setUp() {
        ui = TestFixtures.ui();
        tasks = TestFixtures.emptyTaskList();
        storage = TestFixtures.storage();
    }

    /**
     * Test for standard deadline command if inputs are properly parsed and added to task list
     *
     */
    @Test
    public void event_validInput_taskAddedCorrectly() throws RaisinChatException {
        Command c = Parser.parse(
                "event watch coldplay /from 2026-01-02 10:00 AM /to 2026-01-02 11:00 PM"
        );

        c.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals(
                "E | 0 | watch coldplay | Jan 2 2026 10:00 am -> Jan 2 2026 11:00 pm",
                tasks.getTask(0).toString()
        );
    }

    /**
     * Test for missing task name, should assert thrown error of help command
     *
     */
    @Test
    public void event_missingTaskName_throwsException() throws RaisinChatException {
        Command c = Parser.parse(
                "event /from 2026-01-02 10:00 AM /to 2026-01-02 11:00 PM"
        );

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Hmm, you are doing it wrong! Use command like this: event <taskName> "
                        + "/from <yyyy-MM-dd hh:mm AM/PM> /to <yyyy-MM-dd hh:mm AM/PM>",
                e.getMessage()
        );
    }

    /**
     * Test for incomplete or missing start time, should assert thrown error of help command
     *
     */
    @Test
    public void event_missingStartTime_throwsException() throws RaisinChatException {
        Command c = Parser.parse(
                "event watch coldplay /from /to 2026-01-02 11:00 PM"
        );

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Hmm, you are doing it wrong! Use command like this: event <taskName> "
                        + "/from <yyyy-MM-dd hh:mm AM/PM> /to <yyyy-MM-dd hh:mm AM/PM>",
                e.getMessage()
        );
    }

    /**
     * Test for event command with end time before start time
     *
     */
    @Test
    public void event_endBeforeStart_throwsException() throws RaisinChatException {
        Command c = Parser.parse(
                "event watch coldplay /from 2026-01-02 11:00 PM /to 2026-01-02 10:00 PM"
        );

        String res = c.execute(tasks, ui, storage);

        assertEquals(
                "Hmm, you are doing it wrong! Use command like this: event <taskName> "
                        + "/from <yyyy-MM-dd hh:mm AM/PM> /to <yyyy-MM-dd hh:mm AM/PM>",
                res
        );
        assertEquals(0, tasks.size());
    }

    /**
     * Test for invalid event datetime format, should return parse error message
     *
     */
    @Test
    public void event_invalidDateTime_returnsErrorMessage() throws RaisinChatException {
        Command c = Parser.parse(
                "event watch coldplay /from 2026-13-02 10:00 AM /to 2026-01-02 11:00 PM"
        );

        String res = c.execute(tasks, ui, storage);

        assertEquals("Invalid date format. Use yyyy-MM-dd hh:mm AM/PM", res);
        assertEquals(0, tasks.size());
    }

    /**
     * Test for event command and ensure that mark is not a command that causes the program to exit
     *
     */
    @Test
    public void eventCommand_isNotExitCommand() throws RaisinChatException {
        Command c = Parser.parse(
                "event watch coldplay /from 2026-01-02 10:00 AM /to 2026-01-02 11:00 PM"
        );

        assertFalse(c.isExit());
    }

}

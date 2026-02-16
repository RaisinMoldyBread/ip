package raisinchat.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import raisinchat.Parser;
import raisinchat.Storage;
import raisinchat.TestFixtures;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.Deadline;
import raisinchat.task.Event;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.task.Todo;
import raisinchat.ui.Ui;

public class UpdateCommandTest {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        ui = TestFixtures.ui();
        storage = TestFixtures.storage();

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Deadline("stats", false, LocalDateTime.parse("2026-01-03T11:00")));
        taskList.add(new Event(
                "concert",
                false,
                LocalDateTime.parse("2026-01-02T10:00"),
                LocalDateTime.parse("2026-01-02T11:00")
        ));
        taskList.add(new Todo("homework", false));
        tasks = new TaskList(taskList);
    }

    @Test
    public void update_deadlineBy_updatesTask() throws RaisinChatException {
        Command c = Parser.parse("update 1 /by 2026-01-04 01:00 PM");

        assertDoesNotThrow(() -> c.execute(tasks, ui, storage));

        assertEquals(
            "D | 0 | stats | Jan 4 2026 01:00 pm",
                tasks.getTasks(0).toString()
        );
    }

    @Test
    public void update_eventFrom_updatesTask() throws RaisinChatException {
        Command c = Parser.parse("update 2 /from 2026-01-02 09:00 AM");

        assertDoesNotThrow(() -> c.execute(tasks, ui, storage));

        assertEquals(
            "E | 0 | concert | Jan 2 2026 09:00 am -> Jan 2 2026 11:00 am",
                tasks.getTasks(1).toString()
        );
    }

    @Test
    public void update_eventInvalidRange_throwsException() throws RaisinChatException {
        Command c = Parser.parse("update 2 /from 2026-01-02 11:00 AM /to 2026-01-02 10:00 AM");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals("Invalid date format. Use yyyy-MM-dd hh:mm AM/PM", e.getMessage());
    }

    @Test
    public void update_todoTask_throwsException() throws RaisinChatException {
        Command c = Parser.parse("update 3 /by 2026-01-04 01:00 PM");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals("Todo tasks cannot be updated", e.getMessage());
    }

    @Test
    public void update_deadlineMissingBy_throwsException() throws RaisinChatException {
        Command c = Parser.parse("update 1");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Hmm, you are doing it wrong! Use command like this: update <index> /by <yyyy-MM-dd hh:mm AM/PM>",
                e.getMessage()
        );
    }

    @Test
    public void update_invalidIndex_throwsException() throws RaisinChatException {
        Command c = Parser.parse("update 9 /by 2026-01-04 01:00 PM");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals("Invalid task index", e.getMessage());
    }

    @Test
    public void update_nonNumericIndex_throwsException() {
        RaisinChatException e = assertThrows(
                RaisinChatException.class,
                () -> Parser.parse("update one /by 2026-01-04 01:00 PM")
        );

        assertEquals("First argument must be a task index", e.getMessage());
    }
}

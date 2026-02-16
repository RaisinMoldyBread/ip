package raisinchat.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class FindCommandTest {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        ui = TestFixtures.ui();
        storage = TestFixtures.storage();

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("read book", false));
        taskList.add(new Todo("read docs", false));
        taskList.add(new Todo("sleep", false));
        tasks = new TaskList(taskList);
    }

    @Test
    public void find_matchingTasks_returnsFilteredList() throws RaisinChatException {
        Command c = Parser.parse("find read");

        String res = assertDoesNotThrow(() -> c.execute(tasks, ui, storage));

        assertEquals(
                "Here are the matching tasks in your list right now:\n"
                        + "1. T | 0 | read book\n"
                        + "2. T | 0 | read docs\n",
                res
        );
    }

    @Test
    public void find_noMatches_returnsMessage() throws RaisinChatException {
        Command c = Parser.parse("find gym");

        String res = assertDoesNotThrow(() -> c.execute(tasks, ui, storage));

        assertEquals("No such task found", res);
    }

    @Test
    public void find_missingKeyword_throwsException() throws RaisinChatException {
        Command c = Parser.parse("find");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Hmm, you are doing it wrong! Use command like this: find <keyword>",
                e.getMessage()
        );
    }
}

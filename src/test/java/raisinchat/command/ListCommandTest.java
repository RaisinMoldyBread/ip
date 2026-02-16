package raisinchat.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import raisinchat.Storage;
import raisinchat.TestFixtures;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.task.Todo;
import raisinchat.ui.Ui;

public class ListCommandTest {

    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        ui = TestFixtures.ui();
        storage = TestFixtures.storage();
    }

    @Test
    public void list_emptyList_returnsHeaderOnly() {
        TaskList tasks = TestFixtures.emptyTaskList();
        Command c = new ListCommand(Command.UserCommand.LIST);

        String res = assertDoesNotThrow(() -> c.execute(tasks, ui, storage));

        assertEquals("Here are the list of task right now:\n", res);
    }

    @Test
    public void list_withTasks_returnsFormattedList() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("homework", false));
        taskList.add(new Todo("read book", true));
        TaskList tasks = new TaskList(taskList);
        Command c = new ListCommand(Command.UserCommand.LIST);

        String res = assertDoesNotThrow(() -> c.execute(tasks, ui, storage));

        assertEquals(
                "Here are the list of task right now:\n"
                        + "1. T | 0 | homework\n"
                        + "2. T | 1 | read book\n",
                res
        );
    }
}

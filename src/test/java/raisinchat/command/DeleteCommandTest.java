package raisinchat.command;

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

public class DeleteCommandTest {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        ui = TestFixtures.ui();
        storage = TestFixtures.storage();

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("homework", false));
        taskList.add(new Todo("read book", false));
        tasks = new TaskList(taskList);
    }

    @Test
    public void delete_validIndex_taskRemovedCorrectly() throws RaisinChatException {
        Command c = Parser.parse("delete 1");

        c.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("T | 0 | read book", tasks.getTasks(0).toString());
    }

    @Test
    public void delete_missingIndex_throwsException() throws RaisinChatException {
        Command c = Parser.parse("delete");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Hmm, you are doing it wrong! Use command like this: delete <indexOfTask>",
                e.getMessage()
        );
    }

    @Test
    public void delete_invalidIndex_throwsException() throws RaisinChatException {
        Command c = Parser.parse("delete 99");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals(
                "Such task index does not exist! Please check the list again!",
                e.getMessage()
        );
    }

    @Test
    public void delete_nonNumericIndex_throwsException() throws RaisinChatException {
        Command c = Parser.parse("delete one");

        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> c.execute(tasks, ui, storage)
        );

        assertEquals("Task index must be a number!", e.getMessage());
    }
}

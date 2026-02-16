package raisinchat.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import raisinchat.TestFixtures;
import raisinchat.task.TaskList;

public class HelpCommandTest {

    @Test
    public void help_returnsHelpString() {
        Command c = new HelpCommand(Command.UserCommand.HELP);
        TaskList tasks = TestFixtures.emptyTaskList();

        String res = assertDoesNotThrow(() -> c.execute(tasks, TestFixtures.ui(), TestFixtures.storage()));

        assertTrue(res.contains("This is our command list:"));
        assertTrue(res.contains("todo [name of task]"));
        assertTrue(res.contains("bye / exit"));
    }
}

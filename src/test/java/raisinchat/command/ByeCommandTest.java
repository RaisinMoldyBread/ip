package raisinchat.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import raisinchat.TestFixtures;
import raisinchat.task.TaskList;

public class ByeCommandTest {

    @Test
    public void bye_isExitCommand() {
        Command c = new ByeCommand(Command.UserCommand.BYE);

        assertTrue(c.isExit());
    }

    @Test
    public void bye_execute_returnsGoodbyeMessage() {
        Command c = new ByeCommand(Command.UserCommand.BYE);
        TaskList tasks = TestFixtures.emptyTaskList();

        String res = assertDoesNotThrow(
                () -> c.execute(tasks, TestFixtures.ui(), TestFixtures.storage()));

        assertEquals("Bye :\") Please come back again :\")", res);
    }
}

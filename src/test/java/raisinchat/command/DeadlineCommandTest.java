package raisinchat.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import raisinchat.Parser;
import raisinchat.Storage;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

public class DeadlineCommandTest {

    /**
     * Test for standard deadline command if inputs are properly parsed and added to task list
     *
     */
    @Test
    public void deadlineCommandTest1() {
        String simulatedInput = "deadline finish stats /by 2026-01-03 11:00 PM";
        Ui ui = new Ui();
        TaskList tasks = new TaskList(null);
        Storage storage = new Storage("./data/RaisinChatTaskTestDb.txt");
        try {
            Command c = Parser.parse(simulatedInput);
            c.execute(tasks, ui, storage);
            assertEquals("D | 0 | finish stats | Jan 3 2026 11:00 pm", tasks.getTasks(0).toString());
        } catch (RaisinChatException e) {
            assertEquals("Hmm, you are doing it wrong! Use command like this: "
                            + "deadline <taskName> /by <yyyy-MM-dd hh:mm AM/PM>",
                    e.getMessage());
        }

    }

    /**
     * Test for missing task name, should assert thrown error of help command
     *
     */
    @Test
    public void deadlineCommandTest2() {
        String simulatedInput = "deadline /by 2026-01-03 11:00 PM";
        Ui ui = new Ui();
        TaskList tasks = new TaskList(null);
        Storage storage = new Storage("./data/RaisinChatTaskTestDb.txt");
        try {
            Command c = Parser.parse(simulatedInput);
            c.execute(tasks, ui, storage);
            assertEquals("D | 0 | finish stats | Jan 3 2026 11:00 pm", tasks.getTasks(0).toString());
        } catch (RaisinChatException e) {
            assertEquals("Hmm, you are doing it wrong! Use command like this: "
                            + "deadline <taskName> /by <yyyy-MM-dd hh:mm AM/PM>",
                    e.getMessage());
        }

    }

    /**
     * Test for incomplete deadline datetime, should assert thrown error of help command
     *
     */
    @Test
    public void deadlineCommandTest3() {
        String simulatedInput = "deadline finish stats /by 11:00 PM";
        Ui ui = new Ui();
        TaskList tasks = new TaskList(null);
        Storage storage = new Storage("./data/RaisinChatTaskTestDb.txt");
        try {
            Command c = Parser.parse(simulatedInput);
            c.execute(tasks, ui, storage);
            assertEquals("D | 0 | finish stats | 11:00 pm", tasks.getTasks(0).toString());
        } catch (RaisinChatException e) {
            assertEquals("Hmm, you are doing it wrong! Use command like this: "
                            + "deadline <taskName> /by <yyyy-MM-dd hh:mm AM/PM>",
                    e.getMessage());
        }

    }

    /**
     * Test for deadline command and ensure that mark is not a command that causes the program to exit
     *
     */
    @Test
    public void deadlineCommandTest4() {
        try {
            Command c = Parser.parse("deadline finish stats /by 11:00 PM");
            assertFalse(c.isExit());
        } catch (RaisinChatException e) {
            assertEquals("Empty command received! Type 'help' to see more commands!", e.getMessage());
        }
    }
}

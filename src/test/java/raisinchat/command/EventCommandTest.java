package raisinchat.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import raisinchat.Parser;
import raisinchat.Storage;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

public class EventCommandTest {

    /**
     * Test for standard deadline command if inputs are properly parsed and added to task list
     *
     */
    @Test
    public void EventCommandTest1() {
        String simulatedInput = "event watch coldplay /from 2026-01-02 10:00 AM /to 2026-01-02 11:00 PM";
        Ui ui = new Ui();
        TaskList tasks = new TaskList(null);
        Storage storage = new Storage("./data/RaisinChatTaskTestDb.txt");
        try {
            Command c = Parser.parse(simulatedInput);
            c.execute(tasks, ui, storage);
            assertEquals("E | 0 | watch coldplay | Jan 2 2026 10:00 am -> Jan 2 2026 11:00 pm",
                    tasks.getTasks(0).toString());
        } catch (RaisinChatException e) {
            assertEquals("Hmm, you are doing it wrong! Use command like this: event <taskName> "
                            + "/from <yyyy-MM-dd hh:mm AM/PM> /to <yyyy-MM-dd hh:mm AM/PM>",
                    e.getMessage());
        }

    }

    /**
     * Test for missing task name, should assert thrown error of help command
     *
     */
    @Test
    public void EventCommandTest2() {
        String simulatedInput = "event /from 2026-01-02 10:00 AM /to 2026-01-02 11:00 PM";
        Ui ui = new Ui();
        TaskList tasks = new TaskList(null);
        Storage storage = new Storage("./data/RaisinChatTaskTestDb.txt");
        try {
            Command c = Parser.parse(simulatedInput);
            c.execute(tasks, ui, storage);
            assertEquals("E | 0 | watch coldplay | Jan 2 2026 10:00 am -> Jan 2 2026 11:00 pm",
                    tasks.getTasks(0).toString());
        } catch (RaisinChatException e) {
            assertEquals("Hmm, you are doing it wrong! Use command like this: event <taskName> "
                            + "/from <yyyy-MM-dd hh:mm AM/PM> /to <yyyy-MM-dd hh:mm AM/PM>",
                    e.getMessage());
        }

    }

    /**
     * Test for incomplete or missing start time, should assert thrown error of help command
     *
     */
    @Test
    public void EventCommandTest3() {
        String simulatedInput = "event watch coldplay /from /to 2026-01-02 11:00 PM";
        Ui ui = new Ui();
        TaskList tasks = new TaskList(null);
        Storage storage = new Storage("./data/RaisinChatTaskTestDb.txt");
        try {
            Command c = Parser.parse(simulatedInput);
            c.execute(tasks, ui, storage);
            assertEquals("E | 0 | watch coldplay | Jan 2 2026 10:00 am -> Jan 2 2026 11:00 pm",
                    tasks.getTasks(0).toString());
        } catch (RaisinChatException e) {
            assertEquals("Hmm, you are doing it wrong! Use command like this: event <taskName> "
                            + "/from <yyyy-MM-dd hh:mm AM/PM> /to <yyyy-MM-dd hh:mm AM/PM>",
                    e.getMessage());
        }

    }

    /**
     * Test for event command and ensure that mark is not a command that causes the program to exit
     *
     */
    @Test
    public void EventCommandTest4() {
        try {
            Command c = Parser.parse("event watch coldplay /from /to 2026-01-02 11:00 PM");
            assertFalse(c.isExit());
        } catch (RaisinChatException e) {
            assertEquals("Empty command received! Type 'help' to see more commands!", e.getMessage());
        }
    }

}

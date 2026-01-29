package raisinchat.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import raisinchat.Parser;
import raisinchat.Storage;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.task.Todo;
import raisinchat.ui.Ui;

public class MarkCommandTest {

    /**
     * Test for mark command if inputs are properly parsed and added to task list
     *
     */
    @Test
    public void markCommandTest1() {
        String simulatedInput = "mark 1";
        Ui ui = new Ui();
        ArrayList<Task> taskLists = new ArrayList<>();
        taskLists.add(new Todo("homework", false));
        TaskList tasks = new TaskList(taskLists);
        Storage storage = new Storage("./data/RaisinChatTaskTestDb.txt");
        try {
            Command c = Parser.parse(simulatedInput);
            c.execute(tasks, ui, storage);
            assertEquals("T | 1 | homework",
                    tasks.getTasks(0).toString());
        } catch (RaisinChatException e) {
            assertEquals("mark <indexOfTask>", e.getMessage());
        }

    }

    /**
     * Test for mark command when index is not valid
     *
     */
    @Test
    public void markCommandTest2() {
        String simulatedInput = "mark 100000";
        Ui ui = new Ui();
        ArrayList<Task> taskLists = new ArrayList<>();
        taskLists.add(new Todo("homework", false));
        TaskList tasks = new TaskList(taskLists);
        Storage storage = new Storage("./data/RaisinChatTaskTestDb.txt");
        try {
            Command c = Parser.parse(simulatedInput);
            c.execute(tasks, ui, storage);
            assertEquals("T | 1 | homework",
                    tasks.getTasks(0).toString());
        } catch (RaisinChatException e) {
            assertEquals("Such task index does not exist! Please check the list again!", e.getMessage());
        }

    }

    /**
     * Test for mark command when index is not a number
     *
     */
    @Test
    public void markCommandTest3() {
        String simulatedInput = "mark ten";
        Ui ui = new Ui();
        ArrayList<Task> taskLists = new ArrayList<>();
        taskLists.add(new Todo("homework", false));
        TaskList tasks = new TaskList(taskLists);
        Storage storage = new Storage("./data/RaisinChatTaskTestDb.txt");
        try {
            Command c = Parser.parse(simulatedInput);
            c.execute(tasks, ui, storage);
            assertEquals("T | 1 | homework",
                    tasks.getTasks(0).toString());
        } catch (RaisinChatException e) {
            assertEquals("Task index must be a number!", e.getMessage());
        }

    }

    /**
     * Test for mark command and ensure that mark is not a command that causes the program to exit
     *
     */
    @Test
    public void markCommandTest4() {
        try {
            Command c = Parser.parse("mark 1");
            assertFalse(c.isExit());
        } catch (RaisinChatException e) {
            assertEquals("Empty command received! Type 'help' to see more commands!", e.getMessage());
        }
    }
}

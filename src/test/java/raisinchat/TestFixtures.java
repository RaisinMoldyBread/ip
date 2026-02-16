package raisinchat;

import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

public final class TestFixtures {

    private TestFixtures() {
    }

    public static Ui ui() {
        return new Ui();
    }

    public static TaskList emptyTaskList() {
        return new TaskList();
    }

    public static Storage storage() {
        return new Storage("./data/RaisinChatTaskTestDb.txt");
    }
}

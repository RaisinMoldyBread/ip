package raisinchat;

import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

/**
 * A utility class that provides static factory methods to create common objects
 * used for testing the RaisinChat application.
 * <p>
 * This class serves as a central point for obtaining fresh instances of
 * {@link Ui}, {@link TaskList}, and {@link Storage} with consistent configurations
 * tailored for test environments.
 */
public final class TestFixtures {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private TestFixtures() {
    }

    /**
     * Creates a new, clean instance of the User Interface.
     *
     * @return A new {@link Ui} instance ready for interaction testing.
     */
    public static Ui ui() {
        return new Ui();
    }

    /**
     * Creates an empty task list.
     * <p>
     * Useful for testing scenarios that require a clean state without any
     * pre-loaded tasks.
     *
     * @return A new, empty {@link TaskList}.
     */
    public static TaskList emptyTaskList() {
        return new TaskList();
    }

    /**
     * Creates a Storage instance pointing to a dedicated test database file.
     * <p>
     * The storage is initialized with the path {@code "./data/RaisinChatTaskTestDb.txt"}
     * to ensure that unit tests do not overwrite or interfere with the actual
     * production data.
     *
     * @return A {@link Storage} instance configured for the test environment.
     */
    public static Storage storage() {
        return new Storage("./data/RaisinChatTaskTestDb.txt");
    }
}

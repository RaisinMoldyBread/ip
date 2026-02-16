package raisinchat.task;

/**
 * Base type for all task variants.
 */
public class Task {
    protected String taskName;
    protected Boolean isDone;

    /**
     * Creates a task with the given name and completion state.
     *
     * @param initName task name
     * @param haveDone whether the task is completed
     */
    public Task(String initName, boolean haveDone) {
        this.taskName = initName;
        this.isDone = haveDone;
    }

    /**
     * Marks the task as done and returns a user-facing status message.
     *
     * @return status message after marking the task
     */
    public String markDone() {
        if (this.isDone) {
            return "This task was already marked as done in the past!";
        } else {
            this.isDone = true;
            return String.format("Great job completing the task!\n"
                            + "I've gone ahead and mark it as done!\n"
                            + "\t%s",
                    this);
        }
    }

    /**
     * Marks the task as not done and returns a user-facing status message.
     *
     * @return status message after unmarking the task
     */
    public String markUndone() {
        if (!this.isDone) {
            return "Oop... you actually haven't completed this task!";
        } else {
            this.isDone = false;
            return String.format("Hmm... falling behind are we?\n"
                            + "I've unmarked the task as done!\n"
                            + "\t%s",
                    this);
        }
    }

    /**
     * Returns the storage format used for persistence.
     *
     * @return full storage string for this task
     */
    public String getFullString() {
        return this.isDone ? String.format("1 | %s", this.taskName)
                : String.format("0 | %s", this.taskName);
    }

    @Override
    public String toString() {
        return this.isDone ? String.format("1 | %s", this.taskName)
                : String.format("0 | %s", this.taskName);
    }
}

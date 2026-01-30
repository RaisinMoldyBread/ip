package raisinchat.task;

/**
 * Abstraction of a Task which is inherited by other Task types
 */
public class Task {
    protected String taskName;
    protected Boolean isDone;

    /**
     * Creates the Task object
     *
     * @param initName of task
     * @param haveDone false as default
     */
    public Task(String initName, boolean haveDone) {
        this.taskName = initName;
        this.isDone = haveDone;
    }

    /**
     * Returns result of marking task. If task has been marked before, notify
     * user that task has already been completed
     *
     * @return String result
     */
    public String markDone() {
        if (this.isDone) {
            return "This task was already marked as done in the past!";
        } else {
            this.isDone = true;
            return String.format("Great job completing the task!\n"
                                    + "I've gone ahead and mark it as done!\n"
                                    + "\t%s",
                                    this.toString());
        }
    }

    /**
     * Returns result of unmarking task. If task has not been marked, notify
     * user that task has not been marked
     *
     * @return String result
     */
    public String markUndone() {
        if (!this.isDone) {
            return "Oop... you actually haven't completed this task!";
        } else {
            this.isDone = false;
            return String.format("Hmm... falling behind are we?\n"
                                    + "I've unmarked the task as done!\n"
                                    + "\t%s",
                                    this.toString());
        }
    }

    /**
     * Supports child class fullString() method as some classes need this method to print the full datetime format
     *
     * @return normal string
     */
    public String fullString() {
        return this.isDone ? String.format("1 | %s", this.taskName)
                            : String.format("0 | %s", this.taskName);
    }

    @Override
    public String toString() {
        return this.isDone ? String.format("1 | %s", this.taskName)
                            : String.format("0 | %s", this.taskName);
    }
}

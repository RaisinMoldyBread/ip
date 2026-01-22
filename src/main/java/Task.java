public class Task {
    protected String taskName;
    protected Boolean isDone;

    public Task(String initName) {
        this.taskName = initName;
        this.isDone = false;
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
            return String.format("Great job completing the task!\n" +
                    "I've gone ahead and mark it as done!\n " +
                    "   %s", this.toString());
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
            return String.format("Hmm... falling behind are we?\n" +
                    "I've unmarked the task as done!\n  " +
                    "   %s", this.toString());
        }
    }

    @Override
    public String toString() {
        return this.isDone ? String.format("[X] %s", this.taskName)
                : String.format("[ ] %s", this.taskName);
    }
}

public class Task {
    String taskName;

    public Task(String initName) {
        this.taskName = initName;
    }

    @Override
    public String toString() {
        return String.format("%s", this.taskName);
    }
}

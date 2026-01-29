import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> existingList) {
        if (existingList == null) {
            this.taskList = new ArrayList<>();
        } else {
            this.taskList = existingList;
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    public Task getTasks(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    public int size() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the list of task right now:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(taskList.get(i).toString())
                    .append("\n");
        }
        return sb.toString();
    }
}

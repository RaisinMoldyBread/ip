import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RaisinChat {
    public static final String CHATNAME = "RaisinChat";
    public static final String LOGO = "__________        .__       .__       _________ .__            __   \n" +
            "\\______   \\_____  |__| _____|__| ____ \\_   ___ \\|  |__ _____ _/  |_ \n" +
            " |       _/\\__  \\ |  |/  ___/  |/    \\/    \\  \\/|  |  \\\\__  \\\\   __\\\n" +
            " |    |   \\ / __ \\|  |\\___ \\|  |   |  \\     \\___|   Y  \\/ __ \\|  |  \n" +
            " |____|_  /(____  /__/____  >__|___|  /\\______  /___|  (____  /__|  \n" +
            "        \\/      \\/        \\/        \\/        \\/     \\/     \\/      ";
    public static final String BORDERS = "----------------------------------------------";
    public static final String HELPSTRING = """
            List - List all available tasks
            Help - List all commands available to chatbot
            Mark [Task index] - Marks task at index specified to be done
            Unmark [Task index] - Marks a task as not completed
            Bye/Exit - Exit Chatbot :(
            Key in anything to add to task list!""";
    static List<Task> listOfTask = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(LOGO);
        System.out.println(BORDERS);
        System.out.println("Hello! I'm " + CHATNAME);
        System.out.println("What can I do for you?");
        System.out.println(BORDERS);
        boolean waitUser = true;
        Scanner scanner  = new Scanner(System.in);

        while (waitUser) {
            String userInput = scanner.nextLine().trim();
            String[] parts = userInput.split("\\s+");
            if (parts[0].equalsIgnoreCase("bye") || userInput.equalsIgnoreCase("exit")) {
                waitUser = false;
            } else if (parts[0].equalsIgnoreCase("help")) {
                printOutput(HELPSTRING);
            } else if (parts[0].equalsIgnoreCase("list")) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < listOfTask.size(); i++) {
                    sb.append(i + 1)
                            .append(". ")
                            .append(listOfTask.get(i).toString())
                            .append("\n");
                }
                printOutput(sb.toString());
            } else if (parts[0].equalsIgnoreCase("mark")) {
                if (parts.length < 2) {
                    printOutput("Please specify a task index.");
                    return;
                }

                int index = -1;
                try {
                    index = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    printOutput("Task index must be a number.");
                    continue;
                }

                if (index <= 0 || index > listOfTask.size()) {
                    printOutput("Such task index does not exist!");
                } else {
                    Task task = listOfTask.get(index - 1);
                    printOutput(task.markDone());
                }

            } else if (parts[0].equalsIgnoreCase("unmark")) {
                if (parts.length < 2) {
                    printOutput("Please specify a task index.");
                    return;
                }

                int index = -1;
                try {
                    index = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    printOutput("Task index must be a number.");
                    continue;
                }

                if (index <= 0 || index > listOfTask.size()) {
                    printOutput("Such task index does not exist!");
                } else {
                    Task task = listOfTask.get(index - 1);
                    printOutput(task.markUndone());
                }
            } else {
                Task newTask = new Task(userInput);
                listOfTask.add(newTask);
                String addedTaskString = "We have added: %s";
                String finalString = String.format(addedTaskString, userInput);
                printOutput(finalString);
            }

        }
        System.out.println(BORDERS);
        System.out.println("Bye :\") Please come back again :\")");
        System.out.println(BORDERS);

    }

    public static void printOutput(String data) {
        System.out.println(BORDERS);
        System.out.println(data);
        System.out.println(BORDERS);
    }
}

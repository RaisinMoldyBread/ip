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

        while (waitUser) { // As long as  "bye"/"exit" isn't supplied, loop forever
            String userInput = scanner.nextLine().trim();
            String[] parts = userInput.split("\\s+");
            if (parts[0].equalsIgnoreCase("bye")
                    || userInput.equalsIgnoreCase("exit")) {
                waitUser = false; // This causes loop to break and exit application
            } else if (parts[0].equalsIgnoreCase("help")) {
                // Prints the help string for the chatbot
                printOutput(HELPSTRING);
            } else if (parts[0].equalsIgnoreCase("list")) {
                // Lists the tasks available
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < listOfTask.size(); i++) {
                    sb.append(i + 1)
                            .append(". ")
                            .append(listOfTask.get(i).toString())
                            .append("\n");
                }
                printOutput(sb.toString());
            } else if (parts[0].equalsIgnoreCase("mark")) {
                if (parts.length < 2) { // Checks if input contains an index
                    printOutput("Please specify a task index.");
                    return;
                }

                int index = -1;
                try { // If second part of input is not number, throw error message to user
                    index = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    printOutput("Task index must be a number.");
                    continue;
                }

                // If index supplied is not valid, throw error to user
                if (index <= 0 || index > listOfTask.size()) {
                    printOutput("Such task index does not exist!");
                } else {
                    // Index is valid, proceed to mark task
                    Task task = listOfTask.get(index - 1);
                    printOutput(task.markDone());
                }

            } else if (parts[0].equalsIgnoreCase("unmark")) {
                if (parts.length < 2) { // Checks if input contains an index
                    printOutput("Please specify a task index.");
                    return;
                }

                int index = -1;
                try { // If second part of input is not number, throw error message to user
                    index = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    printOutput("Task index must be a number.");
                    continue;
                }

                // If index supplied is not valid, throw error to user
                if (index <= 0 || index > listOfTask.size()) {
                    printOutput("Such task index does not exist!");
                } else {
                    // Index is valid, proceed to unmark task
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

    /**
     * Method to print string results with border, since border would be used
     * in all printed responses
     *
     * @param data String to print from results/chatbot response
     *
     */
    public static void printOutput(String data) {
        System.out.println(BORDERS);
        System.out.println(data);
        System.out.println(BORDERS);
    }
}

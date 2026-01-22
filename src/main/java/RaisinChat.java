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
            list - List all available tasks
            help - List all commands available to chatbot
            todo [name of task] - Creates a todo task
            deadline [name of task] /by [deadline of task] - Creates task with deadline specified
            event [name of task] /from [start] /to [end] - Creates event task with start time and end time
            mark [Task index] - Marks task at index specified to be done
            unmark [Task index] - Marks a task as not completed
            bye/exit - Exit Chatbot :(""";
    static List<Task> listOfTask = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(LOGO);
        String intro = String.format("Hello! I'm %s\n" +
                "What can I do for you today?", CHATNAME);
        printOutput(intro);
        boolean waitUser = true;
        Scanner scanner  = new Scanner(System.in);

        while (waitUser) { // As long as  "bye"/"exit" isn't supplied, loop forever
            String userInput = scanner.nextLine().trim();
            String[] parts = userInput.split("\\s+", 2);
            // We split userInput to 2 parts, 1st will always be commands
            // 2nd will always be some args, we will further filter in the future
            String command = parts[0];
            String arguments = parts.length > 1 ? parts[1] : "";
            if (command.equalsIgnoreCase("bye")
                    || command.equalsIgnoreCase("exit")) {
                waitUser = false; // This causes loop to break and exit application

            } else if (command.equalsIgnoreCase("help")) {
                // Prints the help string for the chatbot
                printOutput(HELPSTRING);

            } else if (command.equalsIgnoreCase("list")) {
                // Lists the tasks available
                StringBuilder sb = new StringBuilder("Here are the list of task right now:\n");
                for (int i = 0; i < listOfTask.size(); i++) {
                    sb.append(i + 1)
                            .append(". ")
                            .append(listOfTask.get(i).toString())
                            .append("\n");
                }
                printOutput(sb.toString());

            } else if (command.equalsIgnoreCase("mark")) {
                if (arguments.isBlank()) { // Checks if input contains an index
                    printOutput("Please specify a task index.");
                    continue;
                }

                int index = -1;
                try { // If second part of input is not number, throw error message to user
                    index = Integer.parseInt(arguments);

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

            } else if (command.equalsIgnoreCase("unmark")) {
                if (arguments.isBlank()) { // Checks if input contains an index
                    printOutput("Please specify a task index.");
                    continue;
                }

                int index = -1;
                try { // If second part of input is not number, throw error message to user
                    index = Integer.parseInt(arguments);
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
            } else if (command.equalsIgnoreCase("todo")) {
                Task newTodo = new Todo(arguments);
                listOfTask.add(newTodo);
                String res = String.format("Got it! I have added this task\n" +
                        "   %s\n" +
                        "Now you have %d tasks!", newTodo.toString(), listOfTask.size());
                printOutput(res);

            } else if (command.equalsIgnoreCase("deadline")) {
                String[] getDeadline = arguments.split("/by", 2);
                // We split using /by so that we can extract deadline time
                if (getDeadline.length < 2) {
                    printOutput("Please specify deadline using /by");
                    continue;
                }
                String nameTask = getDeadline[0].trim();
                String by = getDeadline[1].trim();
                Task deadlineTask = new Deadline(nameTask, by);
                listOfTask.add(deadlineTask);
                String res = String.format("Got it! I have added this task\n" +
                        "   %s\n" +
                        "Now you have %d tasks!", deadlineTask.toString(), listOfTask.size());
                printOutput(res);

            } else if (command.equalsIgnoreCase("event")) {
                String[] getStart = arguments.split("/from", 2);
                // We split using /from first to get task name
                if (getStart.length < 2) {
                    printOutput("Please specify start time using /from");
                    continue;
                }
                String nameTask = getStart[0].trim();
                String getFullTiming = getStart[1].trim();
                // We split again using /to to get the actual start and end times
                String[] getActualTiming = getFullTiming.split("/to", 2);
                if (getActualTiming.length < 2) {
                    printOutput("Please specify end time AFTER /from using /to");
                    continue;
                }
                String startTime = getActualTiming[0].trim();
                String endTime = getActualTiming[1].trim();
                Task eventTask = new Event(nameTask, startTime, endTime);
                listOfTask.add(eventTask);
                String res = String.format("Got it! I have added this task\n" +
                        "   %s\n" +
                        "Now you have %d tasks!", eventTask.toString(), listOfTask.size());
                printOutput(res);
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

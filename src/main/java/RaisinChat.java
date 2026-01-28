import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RaisinChat {
    public static final String CHATNAME = "RaisinChat";
    public static final String LOGO = "__________        .__       .__       _________ .__            __   \n"
            + "\\______   \\_____  |__| _____|__| ____ \\_   ___ \\|  |__ _____ _/  |_ \n"
            + " |       _/\\__  \\ |  |/  ___/  |/    \\/    \\  \\/|  |  \\\\__  \\\\   __\\\n"
            + " |    |   \\ / __ \\|  |\\___ \\|  |   |  \\     \\___|   Y  \\/ __ \\|  |  \n"
            + " |____|_  /(____  /__/____  >__|___|  /\\______  /___|  (____  /__|  \n"
            + "        \\/      \\/        \\/        \\/        \\/     \\/     \\/      ";

    public static final String BORDERS = "----------------------------------------------";
    public static final String HELPSTRING = """
            list - List all available tasks
            help - List all commands available to chatbot
            todo [name of task] - Creates a todo task
            deadline [name of task] /by [deadline of task] - Creates task with deadline specified
            event [name of task] /from [start] /to [end] - Creates event task with start time and end time
            delete [Task index] - Deletes a task in the list
            mark [Task index] - Marks task at index specified to be done
            unmark [Task index] - Marks a task as not completed
            bye/exit - Exit Chatbot :(""";
    static List<Task> listOfTask = new ArrayList<>();
    public static final String DATALOCATION = "./data/RaisinChatTaskDb.txt";

    public static void main(String[] args) {
        readDatabase(DATALOCATION);
        System.out.println(LOGO);
        String intro = String.format("Hello! I'm %s\n" +
                "What can I do for you today?", CHATNAME);
        printOutput(intro);
        Scanner scanner  = new Scanner(System.in);
        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                shouldExit = processInput(scanner.nextLine());
            } catch (RaisinChatException e) {
                printOutput(e.getMessage());
            }
        }
        try {
            saveDatabase(listOfTask, DATALOCATION);
        } catch (RaisinChatException e) {
            printOutput(e.getMessage());
        }

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

    /**
     * Method to process user inputs and act accordingly based on help list commands
     *
     * @param userInput String to process and parse
     * @return Should exit application or not
     * @throws UnkownCommandException if command is not in help list
     */
    public static boolean processInput(String userInput) throws RaisinChatException {
        String input = userInput.trim();
        if (input.isEmpty()) {
            throw new RaisinChatException("Empty command received! Type 'help' to see more commands!");
        }

        String[] commandInput = input.split("\\s+", 2);
        String commandAction = commandInput[0].toLowerCase();
        String args = (commandInput.length == 2) ? commandInput[1] : "";

        switch (commandAction) {
            case "help":
                printHelp();
                return false;

            case "list":
                printList();
                return false;

            case "todo":
                processTodo(args);
                return false;

            case "event":
                processEvent(args);
                return false;

            case "deadline":
                processDeadline(args);
                return false;

            case "delete":
                processDelete(args);
                return false;

            case "mark":
                processMarkTask(args);
                return false;

            case "unmark":
                processUnmarkTask(args);
                return false;

            case "bye":
                printBye();
                return true;

            case "exit":
                printBye();
                return true;

            default:
                throw new UnkownCommandException(commandAction);
        }
    }

    /**
     * Method to attempt to read past saved task, if none exist, create one
     *
     * @param location Location of database of Task list to read
     */
    public static void readDatabase(String location) {
        File taskFile = new File(location);

        try {
            File parentDir = taskFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!taskFile.exists()) {
                taskFile.createNewFile();
                return; // nothing to read yet
            }

            try (Scanner fileReader = new Scanner(taskFile)) {
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    String[] record = data.split("\\|");

                    switch (record[0].trim()) {
                        case "T":
                            listOfTask.add(new Todo(
                                    record[2].trim(),
                                    record[1].trim().equals("1")));
                            break;

                        case "D":
                            listOfTask.add(new Deadline(
                                    record[2].trim(),
                                    record[1].trim().equals("1"),
                                    record[3].trim()));
                            break;

                        case "E":
                            String[] times = record[3].split("-");
                            listOfTask.add(new Event(
                                    record[2].trim(),
                                    record[1].trim().equals("1"),
                                    times[0].trim(),
                                    times[1].trim()));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            printOutput("Failed to read data storage :(");
        }
    }

    /**
     * Deletes past database of tasks and writes a new one
     *
     * @param taskData Current end-state of Tasks in the list saved by user
     * @param location Location of database of Task list to save
     * @throws RaisinChatException If application still fails to create data file
     */
    public static void saveDatabase(List<Task> taskData, String location) throws RaisinChatException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATALOCATION))) {
            for (int i = 0; i < listOfTask.size(); i++) {
                bw.write(listOfTask.get(i).toString());
                if (i < listOfTask.size() - 1) {
                    bw.newLine();
                }
            }
            printOutput("Successfully saved the file!");
        } catch (IOException e) {
            printOutput("An error occurred while trying to save your data! :(");
        }
    }

    /**
     * Method to process Todo command
     *
     * @param arguments Task to add into the list
     * @throws MissingArgException if command is not used as todo <taskName>
     */
    public static void processTodo(String arguments) throws MissingArgException {
        if (arguments.isEmpty()) {
            throw new MissingArgException("todo <taskName>");
        }

        Task newTodo = new Todo(arguments, false);
        listOfTask.add(newTodo);
        String res = String.format("Got it! I have added this task\n"
                        + "\t%s\n"
                        + "Now you have %d tasks!",
                newTodo.toString(),
                listOfTask.size());
        printOutput(res);

    }

    /**
     * Method to process event command
     *
     * @param arguments to process for adding events
     * @throws MissingArgException if command is not used as event <taskName> /from <start> /to <end>
     */
    public static void processEvent(String arguments) throws MissingArgException {
        String[] splitArgs = arguments.split("/from", 2);
        // We split using /from first to get task name
        if (splitArgs.length < 2) {
            throw new MissingArgException("event <taskName> /from <start> /to <end>");
        }
        String nameTask = splitArgs[0].trim();
        String getFullTiming = splitArgs[1].trim();
        // We split again using /to to get the actual start and end times
        String[] getActualTiming = getFullTiming.split("/to", 2);
        if (getActualTiming.length < 2) {
            throw new MissingArgException("event <taskName> /from <start> /to <end>");
        }
        String startTime = getActualTiming[0].trim();
        String endTime = getActualTiming[1].trim();
        if (nameTask.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new MissingArgException("event <taskName> /from <start> /to <end>");
        }
        Task eventTask = new Event(nameTask, false, startTime, endTime);
        listOfTask.add(eventTask);
        String res = String.format("Got it! I have added this task\n"
                        + "\t%s\n"
                        + "Now you have %d tasks!",
                eventTask.toString(),
                listOfTask.size());
        printOutput(res);

    }

    /**
     * Method to process deadline command
     *
     * @param arguments to process for adding deadline
     * @throws MissingArgException if command is not used as deadline <taskName> /by <end>
     */
    public static void processDeadline(String arguments) throws MissingArgException {
        String[] getDeadline = arguments.split("/by", 2);
        // We split using /by so that we can extract deadline time
        if (getDeadline.length < 2) {
            throw new MissingArgException("deadline <taskName> /by <end>");
        }
        String nameTask = getDeadline[0].trim();
        String by = getDeadline[1].trim();
        if (nameTask.isEmpty() || by.isEmpty()) {
            throw new MissingArgException("deadline <taskName> /by <end>");
        }
        Task deadlineTask = new Deadline(nameTask, false, by);
        listOfTask.add(deadlineTask);
        String res = String.format("Got it! I have added this task\n"
                        + "\t%s\n"
                        + "Now you have %d tasks!",
                deadlineTask.toString(),
                listOfTask.size());
        printOutput(res);
    }

    /**
     * Method to process delete command
     *
     * @param arguments to process for deleting tasks based on list
     * @throws MissingArgException if command is not used as delete <indexOfTask>
     * @throws RaisinChatException if index of task does NOT exist or index is NOT a number
     */
    public static void processDelete(String arguments) throws RaisinChatException {
        if (arguments.isBlank()) { // Checks if input contains an index
            throw new MissingArgException("delete <indexOfTask>");
        }

        int index = -1;
        try { // If second part of input is not number, throw error message to user
            index = Integer.parseInt(arguments);

        } catch (NumberFormatException e) {
            throw new RaisinChatException("Task index must be a number!");
        }

        // If index supplied is not valid, throw error to user
        if (index <= 0 || index > listOfTask.size()) {
            throw new RaisinChatException("Such task index does not exist! Please check the list again!");
        } else {
            // Index is valid, proceed to delete task
            Task task = listOfTask.get(index - 1);
            listOfTask.remove(index - 1);
            String res = String.format("Noted. I have removed this task:\n"
                    + "\t%s\n"
                    + "You now have %d tasks in the list!",
                    task.toString(),
                    listOfTask.size());
            printOutput(res);
        }
    }

    /**
     * Method to process mark command
     *
     * @param arguments to process for marking tasks
     * @throws MissingArgException if command is not used as mark <indexOfTask>
     * @throws RaisinChatException if index of task does NOT exist or index is NOT a number
     */
    public static void processMarkTask(String arguments) throws RaisinChatException {
        if (arguments.isBlank()) { // Checks if input contains an index
            throw new MissingArgException("mark <indexOfTask>");
        }

        int index = -1;
        try { // If second part of input is not number, throw error message to user
            index = Integer.parseInt(arguments);

        } catch (NumberFormatException e) {
            throw new RaisinChatException("Task index must be a number!");
        }

        // If index supplied is not valid, throw error to user
        if (index <= 0 || index > listOfTask.size()) {
            throw new RaisinChatException("Such task index does not exist! Please check the list again!");
        } else {
            // Index is valid, proceed to mark task
            Task task = listOfTask.get(index - 1);
            printOutput(task.markDone());
        }
    }

    /**
     * Method to process unmark command
     *
     * @param arguments to process for unmarking tasks
     * @throws MissingArgException if command is not used as unmark <indexOfTask>
     * @throws RaisinChatException if index of task does NOT exist or index is NOT a number
     */
    public static void processUnmarkTask(String arguments) throws RaisinChatException {
        if (arguments.isBlank()) { // Checks if input contains an index
            throw new MissingArgException("unmark <indexOfTask>");
        }

        int index = -1;
        try { // If second part of input is not number, throw error message to user
            index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new RaisinChatException("Task index must be a number!");
        }

        // If index supplied is not valid, throw error to user
        if (index <= 0 || index > listOfTask.size()) {
            throw new RaisinChatException("Such task index does not exist! Please check the list again!");
        } else {
            // Index is valid, proceed to unmark task
            Task task = listOfTask.get(index - 1);
            printOutput(task.markUndone());
        }
    }

    /**
     * Method to print help page
     */
    public static void printHelp() {
        printOutput(HELPSTRING);
    }

    /**
     * Method to print list of task currently present
     */
    public static void printList() {
        StringBuilder sb = new StringBuilder("Here are the list of task right now:\n");
        for (int i = 0; i < listOfTask.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(listOfTask.get(i).toString())
                    .append("\n");
        }
        printOutput(sb.toString());
    }

    /**
     * Method to print Goodbye text
     */
    public static void printBye() {
        printOutput("Bye :\") Please come back again :\")");
    }
}

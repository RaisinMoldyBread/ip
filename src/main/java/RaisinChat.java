import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RaisinChat {
    static String CHATNAME = "RaisinChat";
    static String LOGO = "__________        .__       .__       _________ .__            __   \n" +
            "\\______   \\_____  |__| _____|__| ____ \\_   ___ \\|  |__ _____ _/  |_ \n" +
            " |       _/\\__  \\ |  |/  ___/  |/    \\/    \\  \\/|  |  \\\\__  \\\\   __\\\n" +
            " |    |   \\ / __ \\|  |\\___ \\|  |   |  \\     \\___|   Y  \\/ __ \\|  |  \n" +
            " |____|_  /(____  /__/____  >__|___|  /\\______  /___|  (____  /__|  \n" +
            "        \\/      \\/        \\/        \\/        \\/     \\/     \\/      ";
    static String BORDERS = "----------------------------------------------";
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
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye") || userInput.equalsIgnoreCase("exit")) {
                waitUser = false;
            }
            else if (userInput.equalsIgnoreCase("help")) {
                printOutput("List - List all available tasks \n" +
                        "Help - List all commands available to chatbot \n" +
                        "Bye - Exit Chatbot :( \n" +
                        "Key in anything to add to task list!");
            }
            else if (userInput.equalsIgnoreCase("list")) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < listOfTask.size(); i++) {
                    sb.append(i + 1)
                            .append(". ")
                            .append(listOfTask.get(i).toString())
                            .append("\n");
                }
                printOutput(sb.toString());
            }
            else {
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

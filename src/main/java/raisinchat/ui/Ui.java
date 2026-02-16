package raisinchat.ui;

import java.util.Scanner;

/**
 * Handles user interactions and input/output operations.
 */
public class Ui {

    private static final String CHAT_NAME = "RaisinChat";
    private static final String LOGO = "__________        .__       .__       _________ .__            __   \n"
            + "\\______   \\_____  |__| _____|__| ____ \\_   ___ \\|  |__ _____ _/  |_ \n"
            + " |       _/\\__  \\ |  |/  ___/  |/    \\/    \\  \\/|  |  \\\\__  \\\\   __\\\n"
            + " |    |   \\ / __ \\|  |\\___ \\|  |   |  \\     \\___|   Y  \\/ __ \\|  |  \n"
            + " |____|_  /(____  /__/____  >__|___|  /\\______  /___|  (____  /__|  \n"
            + "        \\/      \\/        \\/        \\/        \\/     \\/     \\/      ";
    private final Scanner scanner;


    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Oh no... we are unable to read your past data :\")");
    }

    /**
     * Prints the welcome message and logo of the application
     */
    public String showWelcome() {
        System.out.println(LOGO);
        return String.format("Hello! I'm %s\n"
                + "What can I do for you today?", CHAT_NAME);
    }

    public String showGoodbye() {
        return "Bye :\") Please come back again :\")";
    }

}

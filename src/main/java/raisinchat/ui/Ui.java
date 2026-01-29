package raisinchat.ui;

import java.util.Scanner;

/**
 * Abstraction of the interaction with the User
 */
public class Ui {

    private static final String CHATNAME = "RaisinChat";
    private static final String LINE = "___________________________________________";
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

    /**
     * Prints the standard message format for a given message
     *
     * @param message to print in the application's standard message format
     */
    public void showMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("Oh no... we are unable to read your past data :\")");
    }

    /**
     * Prints the welcome message and logo of the application
     */
    public void showWelcome() {
        System.out.println(LOGO);
        String intro = String.format("Hello! I'm %s\n"
                + "What can I do for you today?", CHATNAME);
        showMessage(intro);
    }

    public void showGoodbye() {
        showMessage("Bye :\") Please come back again :\")");
    }

}

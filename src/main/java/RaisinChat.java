public class RaisinChat {
    static String chatName = "RaisinChat";
    static String logo = "__________        .__       .__       _________ .__            __   \n" +
            "\\______   \\_____  |__| _____|__| ____ \\_   ___ \\|  |__ _____ _/  |_ \n" +
            " |       _/\\__  \\ |  |/  ___/  |/    \\/    \\  \\/|  |  \\\\__  \\\\   __\\\n" +
            " |    |   \\ / __ \\|  |\\___ \\|  |   |  \\     \\___|   Y  \\/ __ \\|  |  \n" +
            " |____|_  /(____  /__/____  >__|___|  /\\______  /___|  (____  /__|  \n" +
            "        \\/      \\/        \\/        \\/        \\/     \\/     \\/      ";
    static String borders = "----------------------------------------------";
    public static void main(String[] args) {
        System.out.println(logo);
        System.out.println(borders);
        System.out.println("Hello! I'm " + chatName);
        System.out.println("What can I do for you?");
        System.out.println(borders);
        System.out.println("Bye. Hope ot see you again soon!");
        System.out.println(borders);
    }

    public static void printOutput(String data) {
        System.out.println(borders);
        System.out.println(data);
        System.out.println(borders);
    }
}

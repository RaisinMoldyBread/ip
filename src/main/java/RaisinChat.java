public class RaisinChat {

    public static final String DATALOCATION = "./data/RaisinChatTaskDb.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public RaisinChat(String fileDb) {
        this.ui = new Ui();
        this.storage = new Storage(fileDb);

        try {
            this.tasks = new TaskList(this.storage.load());

        } catch (RaisinChatException e) {
            this.ui.showLoadingError();
            this.ui.showMessage(e.getMessage());
            this.tasks = new TaskList(null);
        }

    }

    public void run() {
        ui.showWelcome();
        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                shouldExit = c.isExit();
            } catch (RaisinChatException e) {
                ui.showMessage(e.getMessage());
            }
        }

        boolean isSaved =  this.storage.save(this.tasks);
        if (!isSaved) {
            System.out.println("Save failed");
        } else {
            System.out.println("Saved tasks successfully!");
        }

    }

    public static void main(String[] args) {
        new RaisinChat(DATALOCATION).run();
    }



}

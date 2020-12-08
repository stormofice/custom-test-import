import java.io.File;

public class Main {

    public static final Main INSTANCE = new Main();

    public ConfigHandler configHandler;
    public NetworkHandler networkHandler;
    public ImportHandler importHandler;
    public boolean firstStart = true;
    public MainFrame mainFrame;

    public static void main(String[] args) {
        INSTANCE.init();
    }

    private void init() {

        configHandler = new ConfigHandler();

        importHandler = new ImportHandler();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            configHandler.save();
        }));

        networkHandler = new NetworkHandler();

        if (firstStart) {
            configHandler.setImportType(ImportType.SEARCH_MAIN_FILE);
            configHandler.setBaseFolder(new File("."));
            new ConfigDialog(firstStart);
        } else
            mainFrame = new MainFrame();

    }

}

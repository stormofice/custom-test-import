import java.io.File;

public class Main {

    public static final Main INSTANCE = new Main();

    public ConfigHandler configHandler;
    private ImportHandler importHandler;
    private MainFrame mainFrame;

    public static void main(String[] args) {
        INSTANCE.init();
    }

    private void init() {

        configHandler = new ConfigHandler();

        importHandler = new ImportHandler();
        importHandler.setBaseFolder(configHandler.getBaseFolder());
        importHandler.setImportType(configHandler.getImportType());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            configHandler.save();
        }));

        mainFrame = new MainFrame();
        mainFrame.setVisible(true);

    }

}

import java.io.File;

public class Main {

    public static final Main INSTANCE = new Main();

    private ConfigHandler configHandler;
    private ImportHandler importHandler;

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

    }

}

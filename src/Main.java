import java.io.File;

public class Main {

    public static final Main INSTANCE = new Main();
    private ImportHandler importHandler;

    public static void main(String[] args) {
        INSTANCE.init();
    }

    private void init() {
        importHandler = new ImportHandler();
        importHandler.setBaseFolder(new File("DEBUG"));
        importHandler.setImportType(ImportType.SEARCH_MAIN_FILE);
        importHandler.importCustomTest(new File("DEBUG"));

    }
}

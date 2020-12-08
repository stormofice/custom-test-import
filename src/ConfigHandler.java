import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {

    private File configFile;
    private Properties configProperties;

    public ConfigHandler() {

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("window")) {
            configFile = new File(System.getProperty("user.home") + File.separator + "AppData" + File.separator + "Roaming" + File.separator + "customTestImport.config");
        } else {
            // :(
            configFile = new File(System.getProperty("user.home") + File.separator + "customTestImport.config");
        }


        configProperties = new Properties();

        if (configFile.exists()) {
            try {
                FileReader propReader = new FileReader(configFile);
                configProperties.load(propReader);
            } catch (Exception e) {
                System.err.println("couldn't read config file");
                e.printStackTrace();
            }
        } else {
            try {
                configFile.createNewFile();
                setImportType(ImportType.SEARCH_MAIN_FILE);
                setBaseFolder(new File(""));
            } catch (IOException e) {
                System.err.println("couldn't create config file");
                e.printStackTrace();
            }
        }

    }

    public ImportType getImportType() {
        return ImportType.valueOf(configProperties.getProperty("IMPORT_TYPE"));
    }

    public void setImportType(ImportType importType) {
        configProperties.setProperty("IMPORT_TYPE", importType.toString());
    }

    public File getBaseFolder() {
        return new File(configProperties.getProperty("BASE_FOLDER"));
    }

    public void setBaseFolder(File baseFolder) {
        configProperties.setProperty("BASE_FOLDER", baseFolder.getAbsolutePath());
    }

    public void save() {
        try {
            FileWriter propWriter = new FileWriter(configFile);
            configProperties.store(propWriter, "Settings for custom test importer");
        } catch (IOException e) {
            System.err.println("couldn't write config file");
            e.printStackTrace();
        }
        System.out.println("Saved config");
    }
}

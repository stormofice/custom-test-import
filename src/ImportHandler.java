import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImportHandler {

    private ImportType importType;
    private File baseFolder;

    public ImportHandler() {
        importType = ImportType.BASE_FOLDER;
    }

    public void importCustomTest(File customTest) {


        if (baseFolder == null) {
            throw new IllegalArgumentException("Base folder must not be null");
        }

        if (importType == ImportType.BASE_FOLDER) {
            File customTestFile = new File(baseFolder.getPath() + File.separator + customTest.getName());
            copyFile(customTest.toPath(), customTestFile.toPath());
        } else if (importType == ImportType.SEARCH_PUBLIC_TEST) {
            List<Path> result = null;
            try (Stream<Path> paths = Files.walk(baseFolder.toPath())) {
                result = paths.filter(Files::isReadable)
                        .filter(Files::isRegularFile)
                        .filter(p -> p.getFileName().toString().startsWith(customTest.getName().split("CustomTest.java")[0] + "PublicTest"))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (result == null || result.size() != 1) {
                throw new IllegalArgumentException("No according public test found");
            }

            File parent = result.get(0).toFile().getParentFile();
            copyFile(customTest.toPath(), new File(parent.getPath() + File.separator + customTest.getName()).toPath());
        } else if (importType == ImportType.SEARCH_MAIN_FILE) {
            List<Path> result = null;
            try (Stream<Path> paths = Files.walk(baseFolder.toPath())) {
                result = paths.filter(Files::isReadable)
                        .filter(Files::isRegularFile)
                        .filter(p -> p.getFileName().toString().startsWith(customTest.getName().split("CustomTest.java")[0] + ".java"))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (result == null || result.size() != 1) {
                throw new IllegalArgumentException("No according public test found");
            }

            File parent = result.get(0).toFile().getParentFile();
            copyFile(customTest.toPath(), new File(parent.getPath() + File.separator + customTest.getName()).toPath());
        }

    }

    public void copyFile(Path p1, Path p2) {
        try {
            Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Successfully copied to " + p2.toAbsolutePath().toString());
        } catch (IOException e) {
            System.err.println("Couldn't copy file " + p2.toAbsolutePath().toString());
            e.printStackTrace();
        }
    }

    public void setImportType(ImportType importType) {
        this.importType = importType;
    }

    public void setBaseFolder(File baseFolder) {
        this.baseFolder = baseFolder;
    }
}

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

/**
 * Method {@code ClosableFileReader#close} have to be closed to free up resources
 */
public class ClosableFileReader implements Closeable {
    private final String filePath;
    private final FileInputStream inputStream;
    private final Scanner scanner;

    private ClosableFileReader(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.inputStream = new FileInputStream(this.filePath);
        this.scanner = new Scanner(inputStream, "UTF-8");

    }

    public static ClosableFileReader forFile(String path) {
        try {
            return new ClosableFileReader(path);
        } catch (FileNotFoundException re) {
            throw new RuntimeException(re);
        }
    }

    public Optional<String> line() {
        if (scanner.hasNextLine()) {
            return Optional.of(scanner.nextLine());
        }
        return Optional.empty();
    }

    public void close() throws IOException {
        System.out.println("Closing file streams.");
        inputStream.close();
        scanner.close();
    }
}

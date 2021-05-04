import java.awt.*;
import java.io.IOException;
import java.util.Optional;

public class WordCounterApp {
    public static void main(String[] args) {
        String path = "src\\main\\resources\\lorem-ipsum.txt";
        try (ClosableFileReader lineReader = ClosableFileReader.forFile(path)) {
            System.out.println("Read the file. Starting counting words.");
            WordCounter counter = new WordCounter(ComparisionMode.IGNORE_CASE);
            Optional<String> line = lineReader.line();
            long startTime = System.currentTimeMillis();
            while (line.isPresent()) {
                counter.analyze(line.get());
                line = lineReader.line();
            }
            System.out.println("Finished counting words in " + (System.currentTimeMillis() - startTime) + ": " + counter.sortedByValue());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

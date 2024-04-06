package Controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Controller {
    public Controller(){

    }
    public static List<String> loadFromFile(String fileName) {
        List<String> content = new ArrayList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            try {
                content = (List)reader.lines().collect(Collectors.toList());
            } catch (Throwable var6) {
                try {
                    reader.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            reader.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return (List)content;
    }

    public static void saveToFile(String fileName, List<String> content) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));

            try {
                Objects.requireNonNull(writer);
                content.forEach(writer::println);
            } catch (Throwable var6) {
                try {
                    writer.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            writer.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    public static List<String> listFilesRecursive(String directoryPath) {
        return (List)listFilesRecursiveHelper(new File(directoryPath)).collect(Collectors.toList());
    }

    private static Stream<String> listFilesRecursiveHelper(File directory) {
        return Stream.of(directory.listFiles()).flatMap((file) -> {
            return file.isDirectory() ? listFilesRecursiveHelper(file) : Stream.of(file.getAbsolutePath());
        });
    }

    public static void saveToFile(String fileName, String content) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));

        try {
            writer.println(content);
        } catch (Throwable var6) {
            try {
                writer.close();
            } catch (Throwable var5) {
                var6.addSuppressed(var5);
            }

            throw var6;
        }

        writer.close();
    }
}

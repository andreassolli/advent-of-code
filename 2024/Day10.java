import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Day10 {
    public static void main(String[] args) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get("2024/inputs/Day8.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static boolean walk(List<String> lines){

    }
}

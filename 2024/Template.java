import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Template {
    public static void main(String[] args) throws IOException {
        //Standard
        List<String> lines = Files.readAllLines(Paths.get("2024/inputs/Day.txt"));

        for(String line: lines){

        }

        //Single line
        BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day.txt"));
        String line = reader.readLine();

        for(int i = 0; i < line.length(); i++){
            char c = line.charAt(i);
        }

        int part1 = 0;
        int part2 = 0;

        System.out.println(part1);
        System.out.println(part2);
    }
}

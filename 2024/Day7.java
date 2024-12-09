import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Day7 {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day6.txt"))) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String line: lines){
            String[] all = line.split(":");
            int result = parseInt(all[0]);
            all = all[1].split(" ");

        }
    }
}
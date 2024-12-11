import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Day8 {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day8.txt"))) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Character, List<String>> antennas = new HashMap<>();
        Set<String> antinodes = new HashSet<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c == '.') continue;

                List<String> positions = antennas.computeIfAbsent(c, k -> new ArrayList<>());
                for (String prev : positions) {
                    String[] coords = prev.split(",");
                    int prevY = parseInt(coords[0]);
                    int prevX = parseInt(coords[1]);

                    int diffX = x - parseInt(coords[0]);
                    int diffY = y - parseInt(coords[1]);

                    int antinodeY1 = prevY - diffY;
                    int antinodeX1 = prevX - diffX;
                    if (antinodeY1 >= 0 && antinodeY1 < lines.size() &&
                            antinodeX1 >= 0 && antinodeX1 < line.length()) {
                        antinodes.add(antinodeY1 + "," + antinodeX1);
                    }

                    int antinodeY2 = y + diffY;
                    int antinodeX2 = x + diffX;
                    if (antinodeY2 >= 0 && antinodeY2 < lines.size() &&
                            antinodeX2 >= 0 && antinodeX2 < line.length()) {
                        antinodes.add(antinodeY2 + "," + antinodeX2);
                    }
                }

                positions.add(y + "," + x);
            }
        }
        System.out.println(antinodes.size());
    }
}

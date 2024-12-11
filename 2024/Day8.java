import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Day8 {
    public static void main(String[] args) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get("2024/inputs/Day8.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
            System.out.println(findAntinodes(lines, false));
            System.out.println(findAntinodes(lines, true));
    }

    private static int findAntinodes(List<String> lines, boolean all) {
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

                    int diffX = x - prevX;
                    int diffY = y - prevY;

                    int antinodeY1 = prevY - diffY;
                    int antinodeX1 = prevX - diffX;

                    int antinodeY2 = y + diffY;
                    int antinodeX2 = x + diffX;

                    if(all){
                        antinodes.add(y + "," + x);
                        antinodes.add(prevY + "," + prevX);
                    }

                    while(antinodeX1 >= 0 && antinodeY1 >= 0 && antinodeX1 < line.length() && antinodeY1 < lines.size()){
                        antinodes.add(antinodeY1 + "," + antinodeX1);
                        if(!all) break;
                        antinodeX1 -= diffX;
                        antinodeY1 -= diffY;
                    }

                    while(antinodeX2 >= 0 && antinodeY2 >= 0 && antinodeX2 < line.length() && antinodeY2 < lines.size()){
                        antinodes.add(antinodeY2 + "," + antinodeX2);
                        if(!all) break;
                        antinodeX2 += diffX;
                        antinodeY2 += diffY;
                    }
                }

                positions.add(y + "," + x);
            }
        }
        return antinodes.size();
    }
}

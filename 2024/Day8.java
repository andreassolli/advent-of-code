import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

        HashSet<String> antinodes = new HashSet<>();
        HashMap<Character,ArrayList<String>> antennas = new HashMap<>();
        for(int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            for(int j = 0; j < line.length(); j++){
                char antenna = line.charAt(j);
                if(antenna == '.') continue;
                ArrayList<String> prevAntennas = antennas.getOrDefault(antenna, new ArrayList<>());
                prevAntennas.add(i + "," + j);
                antennas.put(antenna, prevAntennas);
                for(int k = 0; k < prevAntennas.size(); k++){
                    String[] previous = prevAntennas.get(k).split(",");
                    int x = parseInt(previous[0]);
                    int y = parseInt(previous[1]);
                    int diffX = Math.abs(x - j);
                    int diffY = Math.abs(y - i);

                    if(diffX == 0 && diffY == 0) continue;
                    if(x-diffX >= 0 && y-diffY >= 0) antinodes.add((x-diffX) + "," + (y-diffY));
                    if(j+diffX < line.length() && i+diffY < lines.size()) antinodes.add((j+diffX) + "," + (i+diffY));
                }
            }
        }
        System.out.println(antinodes.size());
        for(String antinode: antinodes){
            System.out.println(antinode);
        }

    }
}

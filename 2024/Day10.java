import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10 {
    public static void main(String[] args) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get("2024/inputs/Day10.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int part1 = 0;
        int part2 = 0;
        Set<String> seen = new HashSet<>();
        for(int y = 0; y < lines.size(); y++){
            for(int x = 0; x < lines.getFirst().length(); x++){
                if(lines.get(y).charAt(x) == '0'){
                    part1 += walk(lines, x, y, seen, x, y);
                    part2 += walk(lines, x, y, seen, -1, -1);
                }
            }
        }

        System.out.println(part1);
        System.out.println(part2);

    }

    private static int walk(List<String> lines, int x, int y, Set<String> seen, int startX, int startY){
        int num = Character.digit(lines.get(y).charAt(x), 10);
        if(num == 9 && (seen.add(startY + "," + startX + ": " + y + "," + x) || startX == -1)) return 1;
        int count = 0;
        int[][] directions = {{0,1}, {1,0}, {-1,0}, {0,-1}};
        for(int[] direction: directions){
            int newY = y + direction[0];
            int newX = x + direction[1];
            if(newY < 0 || newX < 0 || newY >= lines.size() || newX >= lines.getFirst().length()) continue;
            if(Character.digit(lines.get(newY).charAt(newX), 10) == num + 1){
                count += walk(lines, newX, newY, seen, startX, startY);
            }
        }
        return count;
    }

}

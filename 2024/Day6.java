import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

public class Day6 {
    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get("2024/inputs/Day6.txt"));

        int x = 0;
        int y = 0;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains("^")) {
                y = i;
                for (int j = 0; j < line.length(); j++) {
                    if (line.charAt(j) == '^') {
                        x = j;
                        break;
                    }
                }
                break;
            }
        }

        HashSet<String> visited = walk(lines, x, y);
        int possibleLoops = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                if (lines.get(i).charAt(j) != '^'
                        && visited.contains(i + "," + j)) {
                    if (walkObstacle(lines, x, y, j, i) == 0) possibleLoops++;
                }
            }
        }
        System.out.println(visited.size());
        System.out.println(possibleLoops);
    }

    private static int walkObstacle(List<String> lines, int x, int y, int obstacleX, int obstacleY) {
        String obstacleLine = lines.get(obstacleY);
        lines.set(obstacleY, obstacleLine.substring(0, obstacleX) + "#" + obstacleLine.substring(obstacleX + 1));

        HashSet<String> obstacles = new HashSet<>();
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int i = 0;

        while (x > 0 && y > 0 && y < lines.size() - 1 && x < lines.getFirst().length() - 1) {
            if (lines.get(y + directions[i][0]).charAt(x + directions[i][1]) == '#'){
                if(!obstacles.add(x + "," + y + " - " + directions[i][0] + directions[i][1])){
                    lines.set(obstacleY, obstacleLine);
                    return 0;
                }
                i++;
            }
            else {
                y += directions[i][0];
                x += directions[i][1];
            }
            if(i == 4) i = 0;
        }
        lines.set(obstacleY, obstacleLine);
        return (obstacles.size());
    }

    private static HashSet<String> walk(List<String> lines, int x, int y) {
        HashSet<String> visited = new HashSet<>();
        visited.add(y + "," + x);
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int i = 0;

        while (x > 0 && y > 0 && y < lines.size() - 1 && x < lines.getFirst().length() - 1) {
            if (lines.get(y + directions[i][0]).charAt(x + directions[i][1]) == '#') i++;
            else {
                y += directions[i][0];
                x += directions[i][1];
            }
            visited.add(y + "," + x);
            if(i == 4) i = 0;
        }

        return visited;
    }



}
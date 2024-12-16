import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day15 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("2024/inputs/Day15.txt"));

        boolean isInput = true;
        int fx = 0;
        int fy = 0;
        List<String> input = new ArrayList<>();

        for(int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            input.add(line);
            if(line.contains("@")){
                fy = i;
                fx = line.charAt(line.indexOf("@"));
            }

            if(!isInput){

            }
            if(line.isEmpty()) isInput = false;
        }

        char[][] grid = new char[input.size()][input.getFirst().length()];
        for(int y = 0; y < input.size(); y++){
            for(int x = 0; x < input.getFirst().length(); x++){
                grid[y][x] = input.get(y).charAt(x);
            }
        }

    }

    private static char[][] move(char direction, char[][] grid, int x, int y){
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        String s = "v^><";

        int dy = directions[0][s.indexOf(direction)];
        int dx = directions[1][s.indexOf(direction)];

        int newY = y + dy;
        int newX = x + dx;

        int count = 0;

        while(grid[newY][newX] == '0'){
            count++;
            newY += dy;
            newX += dx;
        }

        for(int i = 0; i <= count + 1; i++){
            grid[y][x] = '0';
        }

        System.out.println(count);
        return grid;
    }
}

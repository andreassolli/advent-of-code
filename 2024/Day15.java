import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import static java.util.Map.entry;

public class Day15 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("2024/inputs/Day15.txt"));
        int size = lines.getFirst().length();
        char[][] grid = new char[size][size];
        char[][] grid2 = new char[size][size*2];
        int fx = 0, fy = 0, fy2 = 0, fx2 = 0;
        boolean isBoard = true;

        for(int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            if(line.isEmpty()){
                isBoard = false;
                continue;
            }
            if(isBoard) {
                for(int x = 0; x < size; x++){
                    char c = line.charAt(x);
                    grid[i][x] = c;
                    if(c == 'O') {
                        grid2[i][x * 2] = '[';
                        grid2[i][x * 2 + 1] = ']';
                    } else {
                        grid2[i][x * 2] = c;
                        grid2[i][x * 2 + 1] = c;
                    }
                    if(c == '@') {
                        fy = i;
                        fx = x;
                        fy2 = i;
                        fx2 = x * 2;
                        grid2[i][x * 2 + 1] = '.';
                    }
                }
            } else {
                for(int c = 0; c < line.length(); c++){
                    for(int y = 0; y < size; y++){
                        System.out.println(grid2[y]);
                    }
                    int[] newLoc = move(line.charAt(c), grid, fx, fy, false);
                    int[] newLoc2 = move(line.charAt(c), grid2, fx2, fy2, true);
                    fy = newLoc[0];
                    fx = newLoc[1];
                    fy2 = newLoc2[0];
                    fx2 = newLoc2[1];
                }
            }
        }

        int part1 = 0;
        int part2 = 0;
        for(int y = 0; y < size; y++){
            System.out.println(grid2[y]);
            for(int x = 0; x < size; x++){
                if(grid[y][x] == 'O') part1 += y * 100 + x;
                if(grid2[y][x] == '[') part2 += y  * 100 + x + 1;
                if(grid2[y][x * 2] == '[') part2 += y  * 100 + x + 1;
            }
        }

        System.out.println(part1);
        System.out.println(part2);
    }

    private static int[] move(char d, char[][] grid, int x, int y, boolean part2){
        Map<Character, Integer[]> directions = Map.of(
                '^', new Integer[]{-1, 0},
                'v', new Integer[]{1, 0},
                '<', new Integer[]{0, -1},
                '>', new Integer[]{0, 1}
        );
        int dy = directions.get(d)[0];
        int dx = directions.get(d)[1];
        int newY = y + dy, newX = x + dx, count = 1;


        if(part2){
            while(grid[y + dy * count][x + dx * count] == '[' || grid[y + dy * count][x + dx * count] == ']'
                    || (count > 1 && (d == '^' || d == 'v') && (grid[y + dy * count][x + dx * count + 1] == '[' || grid[y + dy * count][x + dx * count - 1] == ']'))) {
                count++;
            }
        } else {
            while(grid[y + dy * count][x + dx * count] == 'O') count++;
        }

        if(grid[y + dy * count][x + dx * count] == '#') return new int[]{y,x};
        if(part2 && (d == '^' || d == 'v') && (grid[y + dy * count][x - 1] == '#' || grid[y + dy * count][x + 1] == '#')) return new int[]{y,x};


        System.out.println(count);
        if(part2){
            for(int i = count; i > 1; i--){
                int cy = y + dy * i;
                int cx = x + dx * i;
                char c = grid[cy - dy][cx - dx];
                char c2 = '.';
                int r = 0;
                if(c == '['){
                    c2 = ']';
                    r = 1;
                } else if (c == ']'){
                    c2 = '[';
                    r = -1;
                }
                System.out.println(c);
                if(d == '^' || d == 'v'){
                    grid[cy][cx] = c;
                    grid[cy][cx + r] = c2;
                    grid[cy - dy][cx - dx + r] = '.';
                } else {
                    grid[cy][cx] = c;
                }
            }
        } else {
            for(int i = 1; i < count; i++) grid[newY + dy * i][newX + dx * i] = 'O';
        }

        grid[y][x] = '.';
        grid[newY][newX] = '@';

        return new int[]{newY, newX};
    }
}

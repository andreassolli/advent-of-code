import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Day14 {
    private static final int width = 101;
    private static final int height = 103;
    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get("2024/inputs/Day14.txt"));

        int[] quadrants = {0,0,0,0};

        for (String line : lines) {
            String[] s = line.split(" ");
            int[] p = parseLine(s[0]), v = parseLine(s[1]);
            calculate(quadrants, p, v);
        }

        int part1 = quadrants[0] * quadrants[1] * quadrants[2] * quadrants[3];
        int part2 = tree(lines);
        System.out.println(part1);
        System.out.println(part2);
    }
    private static int[] parseLine(String s) {
        String[] newS = s.split(",");
        return new int[] {
                parseInt(newS[0].substring(2)),
                parseInt(newS[1]),
        };
    }

    private static void calculate(int[] quadrants, int[] p, int[] v){
        int seconds = 100;

        int[] loc = move(p,v,seconds);

        int newX = loc[0], newY = loc[1];

        if(newX < width / 2 && newY < height / 2) quadrants[0] += 1;
        else if(newX > width / 2 && newY < height / 2) quadrants[1] += 1;
        else if(newX < width / 2 && newY > height / 2) quadrants[2] += 1;
        else if(newX > width / 2 && newY > height / 2) quadrants[3] += 1;
    }

    private static int[] move(int[] p, int[] v, int seconds){

        int px = p[0], py = p[1],
            vx = v[0], vy = v[1];

        int newX = ((px + vx * seconds) % width + width) % width;
        int newY = ((py + vy * seconds) % height + height) % height;

        return new int[] {newX, newY};
    }

    private static void print(List<String> lines, int seconds){
        char[][] grid = new char[101][103];

        for(String line: lines){
            String[] s = line.split(" ");
            int[] p = parseLine(s[0]), v = parseLine(s[1]);
            int[] loc = move(p, v, seconds);
            grid[loc[0]][loc[1]] = '#';
        }

        Arrays.stream(grid).forEach(System.out::println);
    }

    private static int tree(List<String> lines){
        boolean tree = false;
        int i = 2000;
        while(!tree){
            i++;
            Set<String> seen = new HashSet<>();
            for (String line : lines) {
                String[] s = line.split(" ");
                int[] p = parseLine(s[0]), v = parseLine(s[1]);
                int[] loc = move(p, v, i);
                if(!seen.add(loc[0] + "," + loc[1])) break;
            }
            if(seen.size() == 500) tree = true;
        }

        print(lines, i);
        return i;
    }
}

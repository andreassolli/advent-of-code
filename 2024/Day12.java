import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day12 {
    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get("2024/inputs/Day12.txt"));

        int part1 = 0, part2 = 0;
        int rows = lines.size();
        int cols = lines.getFirst().length();
        boolean[][] visited = new boolean[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (!visited[y][x]) {
                    int[] result = floodFillRegion(lines, visited, y, x);
                    int area = result[0];
                    int perimeter = result[1];
                    int corners = result[2];
                    System.out.println("Area: " + area + " Perimeter: " + perimeter + " corners: " + corners);
                    part1 += area * perimeter;
                    part2 += area * corners;
                }
            }
        }

        System.out.println(part1);
        System.out.println(part2);
    }

    private static int[] floodFillRegion(List<String> lines, boolean[][] visited, int startY, int startX) {
        int area = 0, perimeter = 0, corners = 0;
        char c = lines.get(startY).charAt(startX);
        int rows = lines.size();
        int cols = lines.getFirst().length();

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startY, startX});
        visited[startY][startX] = true;

        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, -1, 0, 1};

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int y = cell[0];
            int x = cell[1];
            area++;

            List<Integer> directions = new ArrayList<>();
            int cellPerimeter = 4;
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= rows || nx < 0 || nx >= cols) continue;
                char neighborChar = lines.get(ny).charAt(nx);
                if (neighborChar == c) {
                    directions.add(i);
                    cellPerimeter--;
                    if (!visited[ny][nx]) {
                        visited[ny][nx] = true;
                        stack.push(new int[]{ny, nx});
                    }
                }
            }

            if(directions.size() <= 1) corners += 4 - directions.size();
            if(directions.size() == 2){
                if(directions.get(1) - directions.get(0) == 1 || directions.get(1) - directions.get(0) == 3) corners++;
            }
            perimeter += cellPerimeter;
        }

        return new int[]{area, perimeter, corners};
    }
}

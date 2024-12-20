import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Day18 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("2024/inputs/Day18.txt"));
        int[] start = {0,0};
        int[] end = {70,70};
        char[][] maze = new char[71][71];
        int size = 1024;
        int current = 0;
        for(String line: lines){
            if(current >= size) break;
            int x = parseInt(line.split(",")[0]);
            int y = parseInt(line.split(",")[1]);
            maze[y][x] = '#';
            current++;
        }

        int minimumCost = bfs(maze, start, end);
        int line = search(maze, start, end, lines);

        //Part 1
        System.out.println(minimumCost);
        //Part 2
        System.out.println(lines.get(line));
    }

    private static int search(char[][] maze, int[] start, int[] end, List<String> lines){
        int num = 1024;
        int size = lines.size() - 1;
        while (num <= size){
            num++;
            int x = parseInt(lines.get(num).split(",")[0]);
            int y = parseInt(lines.get(num).split(",")[1]);
            maze[y][x] = '#';
            int cost = bfs(maze, start, end);
            if(cost == -1) break;
        }
        return num;
    }

    private static int bfs(char[][] maze, int[] start, int[] end) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        boolean[][][] visited = new boolean[maze.length][maze[0].length][4];
        queue.add(new int[]{start[0], start[1], 0, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int cost = current[2];

            if (x == end[0] && y == end[1]) return cost;

            for (int newDir = 0; newDir < 4; newDir++) {
                int nx = x + dx[newDir];
                int ny = y + dy[newDir];

                if (isValid(maze, nx, ny) && !visited[nx][ny][newDir]) {
                    visited[nx][ny][newDir] = true;
                    int newCost = cost + 1;
                    queue.add(new int[]{nx, ny, newCost, newDir});
                }
            }
        }
        return -1;
    }

    private static boolean isValid(char[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != '#';
    }
}

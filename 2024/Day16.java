import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day16 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("2024/inputs/Day16.txt"));
        char[][] maze = lines.stream().map(String::toCharArray).toArray(char[][]::new);

        int[] start = findPoint(maze, 'S');
        int[] end = findPoint(maze, 'E');

        int minimumCost = bfs(maze, start, end);
        System.out.println("Minimum cost to reach the end: " + minimumCost);
    }

    private static int[] findPoint(char[][] maze, char target) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private static int bfs(char[][] maze, int[] start, int[] end) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        Set<String> goodFields = new HashSet<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        boolean[][][] visited = new boolean[maze.length][maze[0].length][4];
        queue.add(new int[]{start[0], start[1], 0, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int cost = current[2];
            int dir = current[3];

            if (x == end[0] && y == end[1]){
                for(String field: goodFields){
                    System.out.println(field);
                }
                System.out.println(goodFields.size());
                return cost;
            }

            for (int newDir = 0; newDir < 4; newDir++) {
                int nx = x + dx[newDir];
                int ny = y + dy[newDir];

                if (isValid(maze, nx, ny) && !visited[nx][ny][newDir]) {
                    visited[nx][ny][newDir] = true;
                    goodFields.add(nx + "," + ny);
                    int newCost = cost + ((dir == newDir || dir == -1) ? 1 : 1001);
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

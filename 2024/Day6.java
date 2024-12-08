import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day6 {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day6.txt"))) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        int x = 0;
        int y = 0;

        for(int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            if(line.contains("^")){
                y = i;
                for(int j = 0; j < line.length(); j++){
                    if(line.charAt(j) == '^'){
                        x = j;
                        break;
                    }
                }
                break;
            }
        }

        int possibleLoops = 0;
        for(int i = 0; i < lines.size(); i++){
            for(int j = 0; j < lines.getFirst().length() - 1; j++){
                if(lines.get(i).charAt(j) != '#'){
                    if(walk(lines, x, y, i, j) == 0) possibleLoops++;
                }
            }
        }
        System.out.println(walk(lines, x, y, x, y));
        System.out.println(possibleLoops);
    }

    private static int walk(ArrayList<String> lines, int x, int y, int obstacleX, int obstacleY){
        int sameWalk = 0;
        boolean checkLoop = false;
        if(x != obstacleX && y != obstacleY) {
            String obstacleLine = lines.get(obstacleY);
            lines.remove(obstacleY);
            obstacleLine = obstacleLine.substring(0, obstacleX) + "#" + obstacleLine.substring(obstacleX);
            lines.add(obstacleY, obstacleLine);
            checkLoop = true;
        }

        HashSet<String> visited = new HashSet<>();
        visited.add(x + "," + y);

        int direction = 0;

        while(x > 0 && y > 0 && y < lines.size() - 1 && x < lines.getFirst().length() - 1){
            if(direction == 0){
                if(lines.get(y - 1).charAt(x) == '#') direction++;
                else y--;
            } else if(direction == 1){
                if(lines.get(y).charAt(x + 1) == '#') direction++;
                else x++;
            } else if(direction == 2){
                if(lines.get(y + 1).charAt(x) == '#') direction++;
                else y++;
            } else if(direction == 3){
                if(lines.get(y).charAt(x - 1) == '#') direction++;
                else x--;
            }

            boolean exists = visited.add(x + "," + y);
            if(checkLoop){
                if(!exists) sameWalk++;
                else{ sameWalk = 0;}

                if(sameWalk > 1) return 0;
            }
            if(direction == 4) direction = 0;
        }

        return(visited.size());

    }

}

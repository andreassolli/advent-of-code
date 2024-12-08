import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4 {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day4.txt"))) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        int total = 0;
        int totalMas = 0;

        for(int i = 0; i < lines.size(); i++){
           String line = lines.get(i);
           for(int j = 0; j < line.length(); j++){
               if(line.charAt(j) == 'X'){
                   total += directions(lines, i, j);
               }
               if(line.charAt(j) == 'A'){
                   if(around(lines, i, j)) totalMas++;
               }
           }
        }

        System.out.println(total);
        System.out.println(totalMas);
    }

    private static int directions(ArrayList<String> lines, int x, int y){
        int count = 0;
        int[][] directions = {{0,1}, {0,-1}, {1,1}, {1,0}, {1,-1}, {-1,1}, {-1,-1}, {-1,0}};
        String word = "MAS";
        int chars = word.length();

        for(int[] direction: directions){
            boolean match = true;
            int dirX = direction[0];
            int dirY = direction[1];
            if(0 > x + dirX * chars || x + dirX * chars >= lines.size()
                    ||  0 > y + dirY * chars || y + dirY * chars >= lines.getFirst().length()) continue;
            for(int i = 1; i <= chars; i++){
                if(lines.get(x + dirX * i).charAt(y + dirY*i) != word.charAt(i-1)){
                    match = false;
                    break;
                };
            }

            if(match) count++;
        }
        return count;
    }

    private static boolean around(ArrayList<String> lines, int i, int j){
        if (i < 1 || j < 1 || i >= lines.size() - 1 || j >= lines.get(i).length() - 1) return false;
        int charTotals = 'M' + 'S';
        return(lines.get(i - 1).charAt(j - 1) + lines.get(i + 1).charAt(j + 1) == charTotals &&
                lines.get(i - 1).charAt(j + 1) + lines.get(i + 1).charAt(j - 1) == charTotals);
    }
}

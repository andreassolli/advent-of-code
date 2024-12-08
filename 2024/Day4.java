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
                   if(north(lines, i, j)) total++;
                   if(northeast(lines, i, j)) total++;
                   if(northwest(lines, i, j)) total++;
                   if(south(lines, i, j)) total++;
                   if(southeast(lines, i, j)) total++;
                   if(southwest(lines, i, j)) total++;
                   if(east(lines, i, j)) total++;
                   if(west(lines, i, j)) total++;
               }
               if(line.charAt(j) == 'A'){
                   if(around(lines, i, j)) totalMas++;
               }
           }
        }

        System.out.println(total);
        System.out.println(totalMas);
    }

    private static boolean north(ArrayList<String> lines, int i, int j){
        if (i < 3) return false;
        return lines.get(i - 1).charAt(j) == 'M'
                && lines.get(i - 2).charAt(j) == 'A'
                && lines.get(i - 3).charAt(j) == 'S';
    }

    private static boolean south(ArrayList<String> lines, int i, int j){
        if (i >= lines.size() - 3) return false;
        return lines.get(i + 1).charAt(j) == 'M'
                && lines.get(i + 2).charAt(j) == 'A'
                && lines.get(i + 3).charAt(j) == 'S';
    }

    private static boolean east(ArrayList<String> lines, int i, int j){
        if (j >= lines.get(i).length() - 3) return false;
        return lines.get(i).charAt(j + 1) == 'M'
                && lines.get(i).charAt(j + 2) == 'A'
                && lines.get(i).charAt(j + 3) == 'S';
    }

    private static boolean west(ArrayList<String> lines, int i, int j){
        if (j < 3) return false;
        return lines.get(i).charAt(j - 1) == 'M'
                && lines.get(i).charAt(j - 2) == 'A'
                && lines.get(i).charAt(j - 3) == 'S';
    }

    private static boolean northwest(ArrayList<String> lines, int i, int j){
        if (j < 3 || i < 3) return false;
        return lines.get(i - 1).charAt(j - 1) == 'M'
                && lines.get(i - 2).charAt(j - 2) == 'A'
                && lines.get(i - 3).charAt(j - 3) == 'S';
    }

    private static boolean northeast(ArrayList<String> lines, int i, int j){
        if (j >= lines.get(i).length() - 3 || i < 3) return false;
        return lines.get(i - 1).charAt(j + 1) == 'M'
                && lines.get(i - 2).charAt(j + 2) == 'A'
                && lines.get(i - 3).charAt(j + 3) == 'S';
    }

    private static boolean southeast(ArrayList<String> lines, int i, int j){
        if (j < 3 || i >= lines.size() - 3) return false;
        return lines.get(i + 1).charAt(j - 1) == 'M'
                && lines.get(i + 2).charAt(j - 2) == 'A'
                && lines.get(i + 3).charAt(j - 3) == 'S';
    }

    private static boolean southwest(ArrayList<String> lines, int i, int j){
        if (j >= lines.get(i).length() - 3 || i >= lines.size() - 3) return false;
        return lines.get(i + 1).charAt(j + 1) == 'M'
                && lines.get(i + 2).charAt(j + 2) == 'A'
                && lines.get(i + 3).charAt(j + 3) == 'S';
    }

    private static boolean around(ArrayList<String> lines, int i, int j){
        if (i < 1 || j < 1 || i >= lines.size() - 1 || j >= lines.get(i).length() - 1) return false;
        int charTotals = 'M' + 'S';
        return(lines.get(i - 1).charAt(j - 1) + lines.get(i + 1).charAt(j + 1) == charTotals &&
                lines.get(i - 1).charAt(j + 1) + lines.get(i + 1).charAt(j - 1) == charTotals);
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Day2 {
    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get("2024/inputs/Day2.txt"));

        int safe = 0;
        int reallySafe = 0;

        for (String line : lines) {
            List<Integer> report = new ArrayList<>();
            String[] numbersString = line.split(" ");
            for(String numString: numbersString){
                report.add(parseInt(numString));
            }

            if(isSafe(report)){
                safe++;
                reallySafe++;
            } else if (isReallySafe(report)){
                reallySafe++;
            }

        }

        System.out.println(safe);
        System.out.println(reallySafe);

    }

    private static boolean isSafe(List<Integer> report){
        int previous = report.getFirst();
        int direction = 0;

        for(int i = 1; i < report.size(); i++){
            int number = report.get(i);
            int difference = Math.abs(number - previous);
            if(direction == 0) direction = previous > number ? -1 : 1;
            if(difference < 1 || difference > 3) { direction = 0; break;}
            if(previous > number && direction == 1) { direction = 0; break;}
            if(previous < number && direction == -1) { direction = 0; break;}

           previous = number;
        }

        return direction != 0;
    }

    private static boolean isReallySafe(List<Integer> report){
        for(int i = 0; i < report.size(); i++){
            List<Integer> testReport = new ArrayList<>();

            testReport.addAll(report.subList(0, i));
            testReport.addAll(report.subList(i + 1, report.size()));

            if(isSafe(testReport)){
                return true;
            }
        }
        return false;
    }

}

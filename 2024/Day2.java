import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {
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
        if(isSafe(report)){
            return true;
        }
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

    public static void checkReports(){
        int totalSafe = 0;
        int totalSafeDampener = 0;

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()){
            String reportString = scanner.nextLine();
            if(reportString.isEmpty()) break;
            String[] reportArray = reportString.split(" ");
            List<Integer> reportList = new ArrayList<>();
            for(String stringNumber: reportArray){
                reportList.add(Integer.valueOf(stringNumber));
            }
            if(isSafe(reportList)) totalSafe++;
            if(isReallySafe(reportList)) totalSafeDampener++;
        }

        System.out.println(totalSafe);
        System.out.println(totalSafeDampener);
    }


}

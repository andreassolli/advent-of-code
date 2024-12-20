import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws  IOException{
        StringBuilder lines = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day3.txt"));
        String line = reader.readLine();

        while(line != null){
            lines.append(line);
            line = reader.readLine();
        }

        System.out.println(multiply(lines));
        System.out.println(multiplyDo(lines));
    }

    private static int multiply(StringBuilder input){
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher matcher = pattern.matcher(input.toString());

        int total = 0;

        while (matcher.find()) {
            String match = matcher.group();
            match = match.substring(4, match.length()-1);
            String[] numbers = match.split(",");
            total += parseInt(numbers[0]) * parseInt(numbers[1]);
        }
        return total;
    }

    private static int multiplyDo(StringBuilder input){
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(input.toString());

        int totalDo = 0;
        boolean multiply = true;
        while(matcher.find()){
            String match = matcher.group();
            if(match.equals("do()")) multiply = true;
            if(match.equals("don't()")) multiply = false;
            if(multiply && !match.equals("do()")){
                match = match.substring(4, match.length()-1);
                String[] numbers = match.split(",");
                totalDo += parseInt(numbers[0]) * parseInt(numbers[1]);
            }
        }
        return totalDo;
    }
}


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class Day5 {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day5.txt"))) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(correctOrder(lines));
        System.out.println(fixNotCorrect(lines));
    }

    private static int correctOrder(ArrayList<String> lines){
        HashMap<String, String> order = new HashMap<>();

        int printingIndex = lines.indexOf("");
        int total = 0;

        for (int i = 0; i < lines.size(); i++) {
            if(i < printingIndex){
                String[] numbers = lines.get(i).split("\\|");

                String after = order.getOrDefault(numbers[0], "");
                after = after + ',' + numbers[1];
                order.put(numbers[0], after);
            }
            if(i > printingIndex){
                boolean correct = true;
                ArrayList<String> numbers = new ArrayList<>(Arrays.asList(lines.get(i).split(",")));
                for(int j = 0; j < numbers.size(); j++){
                    String after = order.getOrDefault(numbers.get(j), "");
                    for(int k = 0; k < j; k++){
                        if(after.contains(numbers.get(k))){
                            correct = false;
                            break;
                        }
                    }
                    if(!correct) break;
                }
                if(correct){
                    int middle = parseInt(numbers.get(numbers.size() / 2));
                    total += middle;
                }
            }
        }
        return total;
    }

    private static int fixNotCorrect(ArrayList<String> lines){
        HashMap<String, String> order = new HashMap<>();

        int printingIndex = lines.indexOf("");
        int total = 0;

        for (int i = 0; i < lines.size(); i++) {
            if(i < printingIndex){
                String[] numbers = lines.get(i).split("\\|");

                String after = order.getOrDefault(numbers[0], "");
                after = after + ',' + numbers[1];
                order.put(numbers[0], after);
            }
            if(i > printingIndex){
                boolean correct = false;
                boolean initiallyCorrect = true;
                ArrayList<String> numbers = new ArrayList<>(Arrays.asList(lines.get(i).split(",")));
                while(!correct) {
                    int changes = 0;
                    for (int j = 0; j < numbers.size(); j++) {
                        String after = order.getOrDefault(numbers.get(j), "");
                        for (int k = 0; k < j; k++) {
                            if (after.contains(numbers.get(k))) {
                                String number = numbers.get(k);
                                numbers.remove(number);
                                numbers.add(j, number);
                                changes++;
                            }
                        }
                    }
                    if(changes == 0) correct = true;
                    else if(initiallyCorrect) initiallyCorrect = false;
                }
                if(!initiallyCorrect) {
                    int middle = parseInt(numbers.get(numbers.size() / 2));
                    total += middle;
                }
            }
        }
        return total;
    }
}

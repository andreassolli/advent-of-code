import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Day9 {
    public static void main(String[] args) {
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day9.txt"));
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> file = new ArrayList<>();
        for(int i = 0; i < line.length(); i++) {
            int id = i % 2 == 0 ? i / 2 : -1;
            int size = Character.digit(line.charAt(i), 10);
            for(int j = 0; j < size; j++){
                file.add(id);
            }
        }

        for(int i = line.length() - 1; i >= 0 ; i--){
            if(i % 2 == 0) shift(file);
        }

        System.out.println(checksum(file));
    }

    private static void shift(List<Integer> file){
        for(int i = file.size() - 1; i >= 0; i--){
            for(int j = 0; j < i; j++){
                if(file.get(j) == -1){
                    file.set(j, file.get(i));
                    file.remove(i);
                    break;
                }
            }
        }
    }

    private static int checksum(List<Integer> file){
        int sum = 0;
        for(int i = 0; i < file.size(); i++){
            sum += file.get(i) * i;
        }
        return sum;
    }
}

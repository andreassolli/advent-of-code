import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class Day9 {
    public static void main(String[] args) {
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day9.txt"));
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(shift(line, true));
        System.out.println(shift(line, false));
    }

    private static long shift(String line, boolean all){
        List<Integer> file = new ArrayList<>();
        for(int i = 0; i < line.length(); i++) {
            int id = i % 2 == 0 ? i / 2 : -1;
            int size = Character.digit(line.charAt(i), 10);
            for(int j = 0; j < size; j++){
                file.add(id);
            }
        }

        if(all){
            return(checksum(shiftAll(file), true));
        } else {
            return(checksum(shiftIf(file), false));
        }
    }

    private static List<Integer> shiftIf(List<Integer> file){
        List<Integer> newFile = new ArrayList<>(file);
        int lookingFor = 9;
        int needed = 0;
        int gap = 0;
        for(int i = newFile.size() - 1; i >= 0; i--){
            if(lookingFor == 0) break;
            int id = newFile.get(i);
            if(id != lookingFor && id > 0) {
                System.out.println(lookingFor);
                System.out.println(id);
                int open = 0;
                for(int j = 0; j < i; j++){
                    if(newFile.get(j) == -1){
                        open++;
                        System.out.println("Open++ for " + j + " Total: " + open);
                        System.out.println("Needed: " + needed);
                        if(open == needed){
                            System.out.println("Needed: " + needed);
                            System.out.println("Current: " + lookingFor);
                            System.out.println("Open: " + open);
                            System.out.println(i);
                            System.out.println("NewFile(i): " + newFile.get(i));
                            for(int k = needed; k > 0; k--){
                                newFile.set(1 + j - k, lookingFor);
                                System.out.println(newFile.get(i+k+gap));
                                newFile.set(i + k + gap, -1);
                                System.out.println(newFile.get(i+k+gap));
                            }
                            break;
                        }
                    } else {
                        open = 0;
                    }
                }
                gap = 0;
                if(id < lookingFor) lookingFor--;
                needed = 0;
            } else {
                if(id == -1) gap++;
                needed++;
            }
        }
        for(int num: newFile){
            System.out.println(num);
        }
        return newFile;
    }

    private static List<Integer> shiftAll(List<Integer> file){
        List<Integer> newFile = new ArrayList<>(file);
        for(int i = newFile.size() - 1; i >= 0; i--){
            for(int j = 0; j < i; j++){
                if(newFile.get(j) == -1 && newFile.get(i) != -1){
                    newFile.set(j, newFile.get(i));
                    newFile.remove(i);
                    break;
                }
            }
        }
        for(int num: newFile){
            System.out.println(num);
        }
        return newFile;
    }

    private static long checksum(List<Integer> file, boolean all){
        long sum = 0;
        for(int i = 0; i < file.size(); i++){
            int id = file.get(i);
            if (id == -1 && all) break;
            else if (id != -1) sum += (long) id * i;
        }
        return sum;
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Day1 {
    public static void main(String[] args){
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get("2024/inputs/Day1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for(String line: lines){
            String[] numbers = line.split(" {3}");
            int x = parseInt(numbers[0]);
            int y = parseInt(numbers[1]);
            String numberString = "Number: " + y;
            int numberAmount = map.getOrDefault(numberString, 0);
            list1.add(binarySearch(list1, x), x);
            list2.add(binarySearch(list2, y), y);
            map.put(numberString, numberAmount + 1);
        }
        System.out.println(distanceSorted(list1, list2));
        System.out.println(distanceMap(list1, map));

    }

    private static int binarySearch(List<Integer> list, int num){
        int start = 0;
        int end = list.size() - 1;

        while(start <= end){
            int mid = start + (end - start) / 2;
            if(list.get(mid) < num){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    public static int distanceSorted(List<Integer>list1, List<Integer>list2){
        int distance = 0;
        for(int i = 0; i < list1.size(); i++){
            distance += Math.abs(list1.get(i) - list2.get(i));
        }
        return distance;
    }

    public static int distanceMap(List<Integer> list, Map<String,Integer> map){
        int distance = 0;
        for (int number : list) {
            int amount = map.getOrDefault("Number: " + number, 0);
            distance += number * amount;
        }
        return distance;
    }

}

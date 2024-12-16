import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class Day11 {
    private static final Map<Long, Map<Long, Long>> cache = new HashMap<>();
    public static void main(String[] args) throws IOException{
        Map<Long, Long> stoneMap = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day11.txt"));
        List<Long> stones = Arrays.stream(reader.readLine().split(" "))
                .map(s -> parseLong(s.trim()))
                .collect(Collectors.toList());

        for (Long s : stones) {
            stoneMap.put(s, stoneMap.getOrDefault(s, 0L) + 1);
        }

        int blinks = 75;
        for (int i = 0; i < blinks; i++) {
            if(i < 25) rules(stones);
            stoneMap = rules(stoneMap);
        }

        System.out.println(stones.size());
        long total = stoneMap.values().stream().mapToLong(Long::longValue).sum();
        System.out.println(total);
    }

    private static Map<Long, Long> rules(Map<Long, Long> stones) {
        Map<Long, Long> newMap = new HashMap<>();

        for (Map.Entry<Long, Long> entry : stones.entrySet()) {
            long val = entry.getKey();
            long count = entry.getValue();

            Map<Long, Long> resultForVal;
            if (cache.containsKey(val)) {
                resultForVal = cache.get(val);
            } else {
                resultForVal = transform(val);
                cache.put(val, resultForVal);
            }

            for (Map.Entry<Long, Long> resEntry : resultForVal.entrySet()) {
                long newVal = resEntry.getKey();
                long newValCount = resEntry.getValue() * count;
                newMap.put(newVal, newMap.getOrDefault(newVal, 0L) + newValCount);
            }
        }

        return newMap;
    }

    private static Map<Long, Long> transform(long val) {
        Map<Long, Long> result = new HashMap<>();
        if (val == 0) {
            result.put(1L, 1L);
        } else {
            String numString = Long.toString(val);
            if (numString.length() % 2 == 0) {
                String num1 = removeZero(numString.substring(0, numString.length() / 2));
                String num2 = removeZero(numString.substring(numString.length() / 2));
                long v1 = parseLong(num1);
                long v2 = parseLong(num2);
                result.put(v1, result.getOrDefault(v1, 0L) + 1);
                result.put(v2, result.getOrDefault(v2, 0L) + 1);
            } else {
                long newVal = val * 2024;
                result.put(newVal, 1L);
            }
        }
        return result;
    }

    private static void rules(List<Long> stones) {
        List<Long> oldStones = new ArrayList<>(stones);
        List<Long> newStones = new ArrayList<>();

        for (long val : oldStones) {
            if (val == 0) {
                newStones.add(1L);
            } else if (Long.toString(val).length() % 2 == 0) {
                String numString = Long.toString(val);

                String num1 = removeZero(numString.substring(0, numString.length() / 2));
                String num2 = removeZero(numString.substring(numString.length() / 2));

                newStones.add(parseLong(num1));
                newStones.add(parseLong(num2));
            } else {
                newStones.add(val * 2024);
            }
        }

        stones.clear();
        stones.addAll(newStones);
    }

    private static String removeZero(String number) {
        String cleaned = number.replaceAll("^0+", "");
        return cleaned.isEmpty() ? "0" : cleaned;
    }
}

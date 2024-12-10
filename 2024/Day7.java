import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

//import static java.lang.Float.parseFloat;

public class Day7 {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day7.txt"))) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        long correct = 0;
        long concat = 0;
        for (String line : lines) {
            String[] all = line.split(": ");
            long result = parseLong(all[0]);
            all = all[1].split(" ");
            ArrayList<Long> numbers = Arrays.stream(all)
                    .map(Long::parseLong)
                    .collect(Collectors.toCollection(ArrayList::new));

            if(valid(result, numbers, false)) correct += result;
            if(valid(result, numbers, true)) concat += result;

        }

        System.out.println(correct);
        System.out.println(concat);
    }

    private static boolean valid(long target, ArrayList<Long> numbers, boolean concat) {
        if (numbers.size() == 1) return numbers.getFirst() == target;

        Long a = numbers.getFirst();
        Long b = numbers.get(1);
        if (valid(target, newList(a + b, numbers), concat)) return true;
        if (valid(target, newList(a * b, numbers), concat)) return true;
        return concat && valid(target, newList(Long.parseLong("" + a + b), numbers), true);
    }

    private static ArrayList<Long> newList(Long combined, ArrayList<Long> numbers) {
        ArrayList<Long> result = new ArrayList<>(numbers);
        result.removeFirst();
        result.set(0, combined);
        return result;
    }

}
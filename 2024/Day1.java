import java.util.*;

public class Day1 {

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

    public static void createSortedLists(){
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int x = 1;
        while(x<=2000){
            int number = scanner.nextInt();
            if(x % 2 == 0){
                list1.add(binarySearch(list1, number), number);
            } else {
                list2.add(binarySearch(list2, number), number);
            }
            x++;
        }

        int distance = 0;
        for(int i = 0; i < list1.size(); i++){
            distance += Math.abs(list1.get(i) - list2.get(i));
        }

        System.out.println(distance);
    }

    public static void createSetAndList(){
        List<Integer> list1 = new ArrayList<>();
        Map<String, Integer> list2 = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        int x = 1;
        while(x<=2000){
            int number = scanner.nextInt();

            if(x % 2 == 0){
                String numberString = "Number: " + number;
                int numberAmount = list2.getOrDefault(numberString, 0);
                list2.put(numberString, numberAmount + 1);
            } else {
                list1.add(number);
            }
            x++;
        }

        int distance = 0;
        for (int number : list1) {
            int amount = list2.getOrDefault("Number: " + number, 0);
            distance += number * amount;
        }

        System.out.println(distance);

    }

}

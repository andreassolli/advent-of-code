import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day9 {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("2024/inputs/Day9.txt"));
        String line = reader.readLine();

        System.out.println(performBlockByBlockCompaction(line));
        System.out.println(performWholeFileCompaction(line));
    }

    private static long performBlockByBlockCompaction(String line) {
        List<Integer> disk = parseDisk(line);
        compactByBlocks(disk);
        return checksum(disk);
    }

    private static long performWholeFileCompaction(String line) {
        List<Integer> disk = parseDisk(line);

        int maxFileId = 0;
        for (int i = 0; i < line.length(); i += 2) {
            maxFileId++;
        }
        maxFileId--;

        compactByFiles(disk, maxFileId);
        return checksum(disk);
    }

    private static List<Integer> parseDisk(String line) {
        List<Integer> disk = new ArrayList<>();
        int fileId = 0;
        for (int i = 0; i < line.length(); i++) {
            int length = Character.digit(line.charAt(i), 10);
            if (i % 2 == 0) {
                for (int j = 0; j < length; j++) disk.add(fileId);
                fileId++;
            } else {
                for (int j = 0; j < length; j++) disk.add(-1);
            }
        }
        return disk;
    }

    private static void compactByBlocks(List<Integer> disk) {
        for (int i = disk.size() - 1; i >= 0; i--) {
            if (disk.get(i) != -1) {
                for (int j = 0; j < i; j++) {
                    if (disk.get(j) == -1) {
                        disk.set(j, disk.get(i));
                        disk.set(i, -1);
                        break;
                    }
                }
            }
        }
    }

    private static void compactByFiles(List<Integer> disk, int maxFileId) {
        for (int fileId = maxFileId; fileId >= 0; fileId--) moveFileIfPossible(disk, fileId);
    }

    private static void moveFileIfPossible(List<Integer> disk, int fileId) {
        int start = -1, end = -1;
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == fileId) {
                if (start == -1) start = i;
                end = i;
            }
        }

        if (start == -1) return;

        int fileLength = end - start + 1;

        int freeSegmentStart = -1;
        int freeCount = 0;
        int bestStart = -1;

        for (int i = 0; i < start; i++) {
            if (disk.get(i) == -1) {
                if (freeSegmentStart == -1) freeSegmentStart = i;
                freeCount++;
                if (freeCount == fileLength) {
                    bestStart = freeSegmentStart;
                    break;
                }
            } else {
                freeSegmentStart = -1;
                freeCount = 0;
            }
        }

        if (bestStart == -1) return;

        for (int i = 0; i < fileLength; i++) disk.set(bestStart + i, fileId);
        for (int i = start; i <= end; i++) disk.set(i, -1);
    }

    private static long checksum(List<Integer> disk) {
        long sum = 0;
        for (int i = 0; i < disk.size(); i++) {
            int id = disk.get(i);
            if (id != -1) sum += (long) id * i;
        }
        return sum;
    }
}

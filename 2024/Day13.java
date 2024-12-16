import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Day13 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("2024/inputs/Day13.txt"));

        int part1 = 0;
        long part2 = 0;

        for (int i = 0; i < lines.size(); i += 4) {
            int[] a = parseLine(lines.get(i));
            int[] b = parseLine(lines.get(i+1));
            int[] p = parseLine(lines.get(i+2));
            part1 += bruteForce(a,b,p);
            part2 += linearAlgebra(a,b,p);
        }

        System.out.println(part1);
        System.out.println(part2);
    }

    private static int[] parseLine(String line) {
        String[] s = line.trim().split(", ");
        return new int[] {
                parseInt(s[0].split("X")[1].substring(1)),
                parseInt(s[1].substring(2))
        };
    }

    private static int bruteForce(int[]a, int[]b, int[]p){
        int best = 777;

        int ax = a[0], ay = a[1],
                bx = b[0], by = b[1],
                px = p[0], py = p[1];

        for(int j = 1; j <= 100; j++){
            for(int k = 1; k <= 100; k++){
                int nx = ax * j + bx * k;
                int ny = ay * j + by * k;
                if(px == nx && py == ny) best = Math.min(best, 3*j+k);
            }
        }
        if(best < 777) return best;
        return 0;
    }

    private static long linearAlgebra(int[]a, int[]b, int[]p) {
        long m = 10000000000000L;
        long ax = a[0], ay = a[1],
            bx = b[0], by = b[1],
            px = p[0] + m, py = p[1] + m;

        long det = ax*by - ay*bx;
        if (det == 0) return 0;
        long az = px * by - py * bx;
        long bz = py * ax - px * ay;
        if(az % det != 0 || bz % det != 0) return 0;

        return(az/det * 3 + bz/det);
    }
}

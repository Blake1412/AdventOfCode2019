import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day01 {

    static List<Integer> modules = new ArrayList<>();

    static {
        try {
            Scanner scan = new Scanner(new File("Day01\\data.txt"));
            while (scan.hasNext()) {
                modules.add(Integer.parseInt(scan.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int part1() {
        return modules.stream()
                      .map(x -> x / 3 - 2)
                      .mapToInt(Integer::intValue)
                      .sum();
    }

    public static int part2() {
        int fuelTotal = 0;

        for (Integer module : modules) {
            while (module > 8) {
                module = module / 3 - 2;
                fuelTotal += module;
            }
        }
        return fuelTotal;
    }

    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }
}

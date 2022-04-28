import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day08 {
    static List<List<List<Integer>>> layers = new ArrayList<>();

    static {
        try {
            Scanner scan = new Scanner(new File("Day08\\data.txt"));
            String data = scan.next();
            int index = 0;
            while (index < data.length()) {
                List<List<Integer>> layer = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < 25; j++) {
                        row.add(Character.getNumericValue(data.charAt(index)));
                        index++;
                    }
                    layer.add(row);
                }
                layers.add(layer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int part1() {
        int fewestZeroes = Integer.MAX_VALUE, numberOfZeroes, fewestZeroesLayer = -1;
        for (int i = 0; i < layers.size(); i++) {
            numberOfZeroes = 0;
            for (List<Integer> row : layers.get(i)) {
                for (Integer number : row) {
                    if (number == 0) numberOfZeroes++;
                }
            }
            if (numberOfZeroes < fewestZeroes) {
                fewestZeroes = numberOfZeroes;
                fewestZeroesLayer = i;
            }
        }

        int numberOfOnes = 0, numberOfTwos = 0;

        for (List<Integer> row : layers.get(fewestZeroesLayer)) {
            for (Integer number : row) {
                if (number == 1) numberOfOnes++;
                if (number == 2) numberOfTwos++;
            }
        }
        return numberOfOnes * numberOfTwos;
    }

    public static void part2() {
        StringBuilder message = new StringBuilder();
        for (int row = 0; row < layers.get(0)
                                      .size(); row++) {
            for (int number = 0; number < layers.get(0)
                                                .get(row)
                                                .size(); number++) {
                for (List<List<Integer>> layer : layers) {
                    int i = layer.get(row)
                                 .get(number);
                    if (i == 2) continue;
                    message.append(i);
                    break;
                }
            }
        }
        for (int i = 0; i < message.length(); i += 25) {
            for (int j = i; j < i + 25; j++) {
                char ch = message.charAt(j);
                if (ch == '0') System.out.print(" ");
                else System.out.print(ch);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        part2();
    }
}

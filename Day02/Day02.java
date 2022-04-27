import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day02 {
    static List<Integer> program = new ArrayList<>();

    static {
        try {
            Scanner scan = new Scanner(new File("Day02\\data.txt"));
            for (String number : scan.nextLine()
                                     .split(",")) {
                program.add(Integer.parseInt(number));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int part1() {
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.setRegister(1, 12);
        computer.setRegister(2, 2);
        computer.runProgram();
        return computer.getRegister(0);
    }

    public static int part2() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                IntcodeComputer computer = new IntcodeComputer(program);
                computer.setRegister(1, i);
                computer.setRegister(2, j);
                computer.runProgram();
                if (computer.getRegister(0) == 19690720) {
                    return 100 * i + j;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }
}

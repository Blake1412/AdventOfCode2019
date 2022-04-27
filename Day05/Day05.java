import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day05 {
    static List<Integer> program = new ArrayList<>();

    static {
        try {
            Scanner scan = new Scanner(new File("Day05\\data.txt"));
            for (String number : scan.nextLine()
                                     .split(",")) {
                program.add(Integer.parseInt(number));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void part1() {
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.inputID(1);
        computer.runProgram();
    }

    public static void part2() {
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.inputID(5);
        computer.runProgram();
    }

    public static void main(String[] args) {
        part1();
        part2();
    }
}

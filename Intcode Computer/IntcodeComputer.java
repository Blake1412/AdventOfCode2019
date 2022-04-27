import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

public class IntcodeComputer {
    private final List<Integer> instructions;
    private final PipedOutputStream output = new PipedOutputStream();

    IntcodeComputer(List<Integer> instructions) {
        this.instructions = new ArrayList<>(instructions);

        PipedInputStream input = new PipedInputStream();
        try {
            input.connect(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setIn(input);
    }

    public void runProgram() {
        int pointerIndex = 0, opcode, baseInstruction, valueOne, valueTwo, storeReg, paramOne, paramTwo;

        while (pointerIndex < instructions.size()) {
            baseInstruction = instructions.get(pointerIndex);

            opcode = (baseInstruction / 10 % 10) * 10 + (baseInstruction % 10);

            switch (opcode) {
                case 1, 2, 5, 6, 7, 8 -> {
                    paramOne = baseInstruction / 100 % 10;
                    paramTwo = baseInstruction / 1000 % 10;

                    if (paramOne == 0) valueOne = instructions.get(instructions.get(pointerIndex + 1));
                    else valueOne = instructions.get(pointerIndex + 1);
                    if (paramTwo == 0) valueTwo = instructions.get(instructions.get(pointerIndex + 2));
                    else valueTwo = instructions.get(pointerIndex + 2);

                    switch (opcode) {
                        case 1, 2, 7, 8 -> {
                            storeReg = instructions.get(pointerIndex + 3);

                            switch (opcode) {
                                case 1 -> instructions.set(storeReg, valueOne + valueTwo);
                                case 2 -> instructions.set(storeReg, valueOne * valueTwo);
                                case 7 -> {
                                    if (valueOne < valueTwo) instructions.set(storeReg, 1);
                                    else instructions.set(storeReg, 0);
                                }
                                case 8 -> {
                                    if (valueOne == valueTwo) instructions.set(storeReg, 1);
                                    else instructions.set(storeReg, 0);
                                }
                            }
                            pointerIndex += 4;
                        }
                        case 5, 6 -> {
                            switch (opcode) {
                                case 5 -> {
                                    if (valueOne != 0) pointerIndex = valueTwo;
                                    else pointerIndex += 3;
                                }
                                case 6 -> {
                                    if (valueOne == 0) pointerIndex = valueTwo;
                                    else pointerIndex += 3;
                                }
                            }
                        }
                    }
                }
                case 3, 4 -> {
                    storeReg = instructions.get(pointerIndex + 1);

                    switch (opcode) {
                        case 3 -> {
                            // Scanner userInput = new Scanner(System.in);
                            int systemID = 0;
                            try {
                                systemID = System.in.read();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            instructions.set(storeReg, systemID);
                        }
                        case 4 -> {
                            System.out.println(instructions.get(storeReg));
                        }
                    }
                    pointerIndex += 2;
                }
                case 99 -> pointerIndex = instructions.size();
            }
        }
    }

    public void inputID(int i) {
        try {
            output.write(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRegister(int register, int value) {
        instructions.set(register, value);
    }

    public int getRegister(int i) {
        return instructions.get(i);
    }
}

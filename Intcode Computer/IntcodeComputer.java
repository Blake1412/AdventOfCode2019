import java.util.ArrayList;
import java.util.List;

public class IntcodeComputer {
    private final List<Integer> instructions;

    IntcodeComputer(List<Integer> instructions) {
        this.instructions = new ArrayList<>(instructions);
    }

    public void runProgram() {
        for (int i = 0; i < instructions.size() - 4; i += 4) {
            int opcode = instructions.get(i);
            int regOne = instructions.get(i + 1);
            int regTwo = instructions.get(i + 2);
            int storeReg = instructions.get(i + 3);

            switch (opcode) {
                case 1 -> instructions.set(storeReg, instructions.get(regOne) + instructions.get(regTwo));
                case 2 -> instructions.set(storeReg, instructions.get(regOne) * instructions.get(regTwo));
            }
        }
    }

    public void setRegister(int register, int value) {
        instructions.set(register, value);
    }

    public int getRegister(int i) {
        return instructions.get(i);
    }
}

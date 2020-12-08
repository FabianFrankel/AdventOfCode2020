package com.days;

import java.util.HashSet;

public class Day8 {
    private final String[] instructions;

    public Day8(String[] instructions) {
        this.instructions = instructions;
    }

    public int getAccumulatorAtFail() {
        var accumulator = new Accumulator();
        if (!tryTraverseToEnd(instructions, accumulator))
            return accumulator.cum;
        return -1;
    }

    public int fixAndGetAccumulatorAtSuccess() {
        var instr_copy = instructions.clone();
        for (int i = 0; i < instr_copy.length; i++) {
            var changed_instr = changeInstruction(instr_copy[i]);
            if (!instr_copy[i].equals(changed_instr)) {
                instr_copy[i] = changed_instr;
                var accumulator = new Accumulator();

                if (tryTraverseToEnd(instr_copy, accumulator))
                    return accumulator.cum;
                else
                    instr_copy[i] = changeInstruction(instr_copy[i]);
            }
        }
        return -1;
    }

    private boolean tryTraverseToEnd(String[] instructions, Accumulator accumulator) {
        var exec_i = new HashSet<Integer>();
        for (int i = 0; i < instructions.length; i++) {
            if (exec_i.contains(i))
                return false;

            exec_i.add(i);
            var splitInstr = instructions[i].split(" ");
            switch (splitInstr[0]) {
                case "acc" -> accumulator.cum += Integer.parseInt(splitInstr[1]);
                case "jmp" -> i += (Integer.parseInt(splitInstr[1]) - 1);
            }

        }
        return true;
    }

    private String changeInstruction(String instr) {
        var splitInstr = instr.split(" ");
        switch (splitInstr[0]) {
            case "jmp" -> instr = "nop" + " " + splitInstr[1];
            case "nop" -> instr = "jmp" + " " + splitInstr[1];
        }
        return instr;
    }

    private class Accumulator {
        int cum = 0;
    }
}

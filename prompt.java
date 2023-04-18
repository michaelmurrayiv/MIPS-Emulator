import java.util.*;

public class prompt {
    /**
        h = show help
        d = dump register state
        s = single step through the program (i.e. execute 1 instruction and stop)
        s num = step through num instructions of the program
        r = run until the program ends
        m num1 num2 = display data memory from location num1 to num2
        c = clear all registers, memory, and the program counter to 0
        q = exit the program
    **/
    public static void h(){
        System.out.print("\nh = show help" +
                "\nd = dump register state" +
                "\ns = single step through the program (i.e. execute 1 instruction and stop)" +
                "\ns num = step through num instructions of the program" +
                "\nr = run until the program ends" +
                "\nm num1 num2 = display data memory from location num1 to num2" +
                "\nc = clear all registers, memory, and the program counter to 0" +
                "\nq = exit the program" + "\n\n");
    }
    public static void d(){
        registerFile.printRegisters();
    }
    public static void s(Map<Integer, Integer> labels, Map<Integer, instruction> instructions, boolean singleInstruction){
        instruction inst = instructions.get(registerFile.returnPC());
        inst.execute();
        registerFile.incrementPC();
        if (singleInstruction) {System.out.println("\t\t1 instruction(s) executed");}

    }
    public static void sNum(int num, Map<Integer, Integer> labels, Map<Integer, instruction> instructions){
        int ctDwn = num;
        while (ctDwn > 0) {
            s(labels, instructions, false);
            ctDwn--;
        }
        System.out.println("\t\t" + num + " instruction(s) executed");

    }
    public static void r(Map<Integer, Integer> labels, Map<Integer, instruction> instructions) {
        while (registerFile.returnPC() < instructions.size()) {
            s(labels, instructions, false);
        }
    }
    public static void mNum1Num2(int num1, int num2){
        for (int i = num1; i <= num2; i++) {
            System.out.print("\n");
            System.out.printf("[%s] = %s", i, stack.accessMemory(i));
        }
        System.out.print("\n\n");
    }

    public static void c(){
        registerFile.resetPC();
        registerFile.clearRegisterFile();
        stack.clearMemory();
        System.out.println("\t\tSimulator reset\n");
    }
    public static void q(){
        System.exit(0);
    }

    public static void b() {
        GHR.outputPredictorAccuracy();
    }

}

import java.util.ArrayList;

public class registerFile {
    private static int[] registerFile = new int[32];
    private static int PC = 0;
    public static int taken = 0;

    public static void printRegisters() {
        /*
            pc = 0
            $0 = 0          $v0 = 0         $v1 = 0         $a0 = 0
            $a1 = 0         $a2 = 0         $a3 = 0         $t0 = 0
            $t1 = 0         $t2 = 0         $t3 = 0         $t4 = 0
            $t5 = 0         $t6 = 0         $t7 = 0         $s0 = 0
            $s1 = 0         $s2 = 0         $s3 = 0         $s4 = 0
            $s5 = 0         $s6 = 0         $s7 = 0         $t8 = 0
            $t9 = 0         $sp = 0         $ra = 0
        */
        System.out.print("\n");
        printPC();
        //line 1
        System.out.printf("%-16s", "$0 = " + registerFile[0]);
        System.out.printf("%-16s", "$v0 = " + registerFile[2]);
        System.out.printf("%-16s", "$v1 = " + registerFile[3]);
        System.out.printf("%-16s", "$a0 = " + registerFile[4]);
        //line 2
        System.out.print("\n");
        System.out.printf("%-16s", "$a1 = " + registerFile[5]);
        System.out.printf("%-16s", "$a2 = " + registerFile[6]);
        System.out.printf("%-16s", "$a3 = " + registerFile[7]);
        System.out.printf("%-16s", "$t0 = " + registerFile[8]);
        // line 3
        System.out.print("\n");
        System.out.printf("%-16s", "$t1 = " + registerFile[9]);
        System.out.printf("%-16s", "$t2 = " + registerFile[10]);
        System.out.printf("%-16s", "$t3 = " + registerFile[11]);
        System.out.printf("%-16s", "$t4 = " + registerFile[12]);
        // line 4
        System.out.print("\n");
        System.out.printf("%-16s", "$t5 = " + registerFile[13]);
        System.out.printf("%-16s", "$t6 = " + registerFile[14]);
        System.out.printf("%-16s", "$t7 = " + registerFile[15]);
        System.out.printf("%-16s", "$s0 = " + registerFile[16]);
        // line 5
        System.out.print("\n");
        System.out.printf("%-16s", "$s1 = " + registerFile[17]);
        System.out.printf("%-16s", "$s2 = " + registerFile[18]);
        System.out.printf("%-16s", "$s3 = " + registerFile[19]);
        System.out.printf("%-16s", "$s4 = " + registerFile[20]);
        // line 6
        System.out.print("\n");
        System.out.printf("%-16s", "$s5 = " + registerFile[21]);
        System.out.printf("%-16s", "$s6 = " + registerFile[22]);
        System.out.printf("%-16s", "$s7 = " + registerFile[23]);
        System.out.printf("%-16s", "$t8 = " + registerFile[24]);
        // line 7
        System.out.print("\n");
        System.out.printf("%-16s", "$t9 = " + registerFile[25]);
        System.out.printf("%-16s", "$sp = " + registerFile[29]);
        System.out.printf("%-16s", "$ra = " + registerFile[31]);
        System.out.print("\n");
        System.out.print("\n");

    }

    public static void incrementPC(){
        PC += 1;
    }

    public static void printPC(){
        System.out.println("pc = " + PC);
    }

    public static int returnPC(){ return PC; }

    public static void resetPC(){
        PC = 0;
    }
    public static void clearRegisterFile(){
        registerFile = new int[32];
    }

    public static void setPC(int address) {
        PC = address;
    }

    public static void setValue(int index, int value){
        registerFile[index] = value;
    }

    public static int getValue(int index){
        return registerFile[index];
    }






}

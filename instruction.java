import java.util.*;

public class instruction {
    private String type;
    private String opcode;
    private String rd;
    private String rt;
    private String rs;
    private String imm;
    private String shamt;
    private String funct;
    private String address;
    private boolean isValid;
    private static Map<String, String> instructions = new HashMap<>();

    public instruction(){
        this.type = "";
        this.opcode = "";
        this.rd = "";
        this.rt = "";
        this.rs = "";
        this.imm = "";
        this.shamt = "";
        this.funct = "";
        this.address = "";
        this.isValid = false;
    }
    public static instruction readInstruction(String line, int currLine, Map<Integer, Integer> labels){
        instruction res = new instruction();
        String[] partiallyFiltered = functions.splitStrings(line.trim().split("[\\s,()]++"));
        ArrayList<String> filteredLine = new ArrayList<String>();
        for (String s: partiallyFiltered){

            if (s.length() != 0){
                filteredLine.add(s);
            }

        }
        int i = 0;

        while (i < filteredLine.size()) {
            String str = filteredLine.get(i);

            if (str.equals("")) { i++; continue; }
            if (str.equals("#")) { break; }

            if (instruction.isInst(str)) {
                res.type = str;
                currLine++;
                res.isValid = true; // stores if the line contains an instruction

                //and, or, add, addi, sll, sub, slt, beq, bne, lw, sw, j, jr, and jal

                if (res.type.equals("and")){
                    //R[rd] = R[rs] & R[rt]
                    res.opcode = "000000";
                    res.rd = register.getBin(filteredLine.get(i+1));
                    res.rs = register.getBin(filteredLine.get(i+2));
                    res.rt = register.getBin(filteredLine.get(i+3));
                    res.shamt = "00000";
                    res.funct = instruction.getBin(filteredLine.get(i));
                    i = i+4;

                } else if (res.type.equals("or")){
                    //R[rd] = R[rs] | R[rt]
                    res.opcode = "000000";
                    res.rd = register.getBin(filteredLine.get(i+1));
                    res.rs = register.getBin(filteredLine.get(i+2));
                    res.rt = register.getBin(filteredLine.get(i+3));
                    res.shamt = "00000";
                    res.funct = instruction.getBin(filteredLine.get(i));
                    i = i+4;

                }else if (res.type.equals("add")){
                    //R[rd] = R[rs] + R[rt]
                    res.opcode = "000000";
                    res.rd = register.getBin(filteredLine.get(i+1));
                    res.rs = register.getBin(filteredLine.get(i+2));
                    res.rt = register.getBin(filteredLine.get(i+3));
                    res.shamt = "00000";
                    res.funct = instruction.getBin(filteredLine.get(i));
                    i = i + 4;

                }else if (res.type.equals("addi")){
                    //R[rt] = R[rs] + SignExtImm
                    res.opcode = "001000";

                    res.rt = register.getBin(filteredLine.get(i+1));
                    res.rs = register.getBin(filteredLine.get(i+2));
                    res.imm = functions.to16Binary(filteredLine.get(i+3));
                    i = i+4;

                }else if (res.type.equals("sll")){
                    // sll: rd rt shamt
                    res.opcode = "000000";
                    res.rs = "00000";
                    res.rd = register.getBin(filteredLine.get(i+1));
                    res.rt = register.getBin(filteredLine.get(i+2));
                    res.shamt = functions.to5Binary(filteredLine.get(i+3));
                    res.funct = instruction.getBin(filteredLine.get(i));
                    i = i+4;
                }else if (res.type.equals("sub")){
                    // rd rs rt
                    res.opcode = "000000";
                    res.rd = register.getBin(filteredLine.get(i+1));
                    res.rs = register.getBin(filteredLine.get(i+2));
                    res.rt = register.getBin(filteredLine.get(i+3));
                    res.shamt = "00000";
                    res.funct = instruction.getBin(filteredLine.get(i));
                    i = i + 4;
                }else if (res.type.equals("slt")){
                    // rd rs rt
                    res.opcode = "000000";
                    res.rd = register.getBin(filteredLine.get(i+1));
                    res.rs = register.getBin(filteredLine.get(i+2));
                    res.rt = register.getBin(filteredLine.get(i+3));
                    res.shamt = "00000";
                    res.funct = instruction.getBin(filteredLine.get(i));
                    i = i + 4;
                }else if (res.type.equals("beq")){
                    // rs rt imm
                    res.opcode = instruction.getBin(filteredLine.get(i));
                    res.rs = register.getBin(filteredLine.get(i+1));
                    res.rt = register.getBin(filteredLine.get(i+2));
                    res.imm = functions.to16Binary(Integer.toString(labels.get(filteredLine.get(i+3).hashCode()) - currLine));
                    i = i + 4;
                }else if (res.type.equals("bne")){
                    // rs rt imm
                    res.opcode = instruction.getBin(filteredLine.get(i));
                    res.rs = register.getBin(filteredLine.get(i+1));
                    res.rt = register.getBin(filteredLine.get(i+2));
                    res.imm = functions.to16Binary(Integer.toString(labels.get(filteredLine.get(i+3).hashCode()) - currLine));
                    i = i+4;
                }else if (res.type.equals("lw")){
                    // rt imm rs
                    res.opcode = instruction.getBin(filteredLine.get(i));
                    res.rs = register.getBin(filteredLine.get(i+3));
                    res.rt = register.getBin(filteredLine.get(i+1));
                    res.imm = functions.to16Binary(filteredLine.get(i+2));
                    i = i+4;

                }else if (res.type.equals("sw")){
                    // rs imm rt
                    res.opcode = instruction.getBin(filteredLine.get(i));
                    res.rs = register.getBin(filteredLine.get(i+3));
                    res.rt = register.getBin(filteredLine.get(i+1));
                    res.imm = functions.to16Binary(filteredLine.get(i+2));
                    i = i+4;
                }else if (res.type.equals("j")){
                    // address
                    //PC=JumpAddr
                    res.opcode = instruction.getBin(filteredLine.get(i));
                    res.address = functions.to26Binary(Integer.toString(labels.get(filteredLine.get(i+1).hashCode())));
                    i = i+2;
                }else if (res.type.equals("jr")){
                    // rs
                    //PC=R[rs]
                    res.opcode = "000000";
                    res.rd = "00000";
                    res.rs = register.getBin(filteredLine.get(i+1));
                    res.rt = "00000";
                    res.shamt = "00000";
                    res.funct = instruction.getBin(filteredLine.get(i));
                    i = i+2;
                }else if (res.type.equals("jal")){
                    // address
                    res.opcode = instruction.getBin(filteredLine.get(i));
                    res.address = functions.to26Binary(Integer.toString(labels.get(filteredLine.get(i+1).hashCode())));
                    i = i+2;
                }

            } else {
                //if filteredLine
                System.out.println("invalid instruction: " + filteredLine.get(i));
                System.exit(0);
                // the string was either a comment, label, or invalid instruction.

            }
        }
        return res;
    }

    public void printBinary() {
        // R-Type
        if (this.getType().equals("and") || this.getType().equals("or") || this.getType().equals("add") ||
            this.getType().equals("sll") || this.getType().equals("sub") || this.getType().equals("slt") ||
                    this.getType().equals("jr")){
            System.out.println(opcode + " " + rs + " " + rt + " " + rd + " " + shamt + " " + funct);
        // I-Type
        }else if (this.getType().equals("addi") || this.getType().equals("beq") || this.getType().equals("bne") ||
                this.getType().equals("lw") || this.getType().equals("sw")){
            System.out.println(opcode + " " + rs + " " + rt + " " + imm);

        // J-Type
        }else if (this.getType().equals("j") || this.getType().equals("jal")){
            System.out.println(opcode + " " + address);
        }

    }

    public static void setInstructions() {
        //R-type - the opcodes for R-type instructions is '000000', but the funct is different for each. These are the funct binary codes.
        instructions.put("and", "100100");
        instructions.put("or", "100101");
        instructions.put("add", "100000");
        instructions.put("sll", "000000");
        instructions.put("sub", "100010");
        instructions.put("slt", "101010");
        instructions.put("jr", "001000");

        //I-type opcodes
        instructions.put("addi", "001000"); // R[rt] = R[rs] + SignExtImm
        instructions.put("beq", "000100");  // rs, rt, address
        instructions.put("bne", "000101");  // rs, rt, address
        instructions.put("lw", "100011");   // R[rt] = M[R[rs]+SignExtImm]
        instructions.put("sw", "101011");   // M[R[rs]+SignExtImm] = R[rt]

        //J-type opcodes
        instructions.put("j", "000010");
        instructions.put("jal", "000011");
    }

    public static String getBin(String key) { return instructions.get(key); }
    public static boolean isInst(String key) { return instructions.containsKey(key); }

    public void printClass(){
        System.out.println(type +" " + rd +" " + rt +" " + rs +" " + imm +" " + shamt +" " + funct + " " +address);
    }

    public boolean getIsValid() {return this.isValid;}

    public String getType() {
        return type;
    }
    public void execute() {
        // R-Type
        if (this.type.equals("and")) {
            and();
        }else if (this.type.equals("or")) {
            or();
        }else if (this.type.equals("add")){
            add();
        }else if (this.type.equals("sll")){
            sll();
        }else if (this.type.equals("sub")){
            sub();
        }else if (this.type.equals("slt")){
            slt();
        }else if (this.type.equals("jr")){
            jr();
            // I-Type
        }else if (this.type.equals("addi")){
            addi();
        }else if (this.type.equals("beq")){
            beq();
        }else if (this.type.equals("bne")){
            bne();
        }else if (this.type.equals("lw")){
            lw();
        }else if (this.type.equals("sw")){
            sw();
            // J-Type
        }else if (this.type.equals("j")){
            j();
        }else if (this.type.equals("jal")){
            jal();
        }

    }
    //and, or, add, addi, sll, sub, slt, beq, bne, lw, sw, j, jr, and jal

    public void and(){
        int intrd = Integer.parseInt(rd, 2);
        int intrs = Integer.parseInt(rs, 2);
        int intrt = Integer.parseInt(rt, 2);

        int output = registerFile.getValue(intrs) & registerFile.getValue(intrt);
        registerFile.setValue(intrd, output);
    }

    public void or(){
        int intrd = Integer.parseInt(rd, 2);
        int intrs = Integer.parseInt(rs, 2);
        int intrt = Integer.parseInt(rt, 2);

        int output = registerFile.getValue(intrs) | registerFile.getValue(intrt);
        registerFile.setValue(intrd, output);
    }

    public void add(){
        int intrd = Integer.parseInt(rd, 2);
        int intrs = Integer.parseInt(rs, 2);
        int intrt = Integer.parseInt(rt, 2);

        int output = registerFile.getValue(intrs) + registerFile.getValue(intrt);
        registerFile.setValue(intrd, output);
    }

    public void addi(){
        int intrt = Integer.parseInt(rt, 2);
        int intrs = Integer.parseInt(rs, 2);
        int intimm = (short)Integer.parseInt(imm, 2);

        int output = registerFile.getValue(intrs) + intimm;
        registerFile.setValue(intrt, output);
    }

    public void sll(){
        int intrd = Integer.parseInt(rd, 2);
        int intrt = Integer.parseInt(rt, 2);
        int intshamt = Integer.parseInt(shamt, 2);

        int output = registerFile.getValue(intrt) << intshamt;
        registerFile.setValue(intrd, output);
    }

    public void sub(){
        int intrd = Integer.parseInt(rd, 2);
        int intrs = Integer.parseInt(rs, 2);
        int intrt = Integer.parseInt(rt, 2);

        int output = registerFile.getValue(intrs) - registerFile.getValue(intrt);
        registerFile.setValue(intrd, output);
    }

    public void slt(){
        int intrd = Integer.parseInt(rd,2);
        int intrs = Integer.parseInt(rs,2);
        int intrt = Integer.parseInt(rt,2);

        int output;

        if (registerFile.getValue(intrs) < registerFile.getValue(intrt)){
            output = 1;
        }
        else{
            output = 0;
        }

        registerFile.setValue(intrd, output);
    }

    public void beq(){
        int intrs = Integer.parseInt(rs,2);
        int intrt = Integer.parseInt(rt,2);
        int intimm = (short)Integer.parseInt(imm, 2);

        if(registerFile.getValue(intrs) == registerFile.getValue(intrt)){
            registerFile.setPC(registerFile.returnPC() + intimm);
        }

    }

    public void bne(){
        int intrs = Integer.parseInt(rs,2);
        int intrt = Integer.parseInt(rt,2);
        int intimm = (short)Integer.parseInt(imm, 2);
        if (registerFile.getValue(intrs) != registerFile.getValue(intrt)){
            registerFile.setPC(registerFile.returnPC() + intimm);
        }

    }

    public void lw(){
        int intrt = Integer.parseInt(rt,2);
        int intrs = Integer.parseInt(rs,2);
        int intimm = Integer.parseInt(imm,2);

        int address = registerFile.getValue(intrs) + intimm; //

        int output = stack.getMemory(address);
        registerFile.setValue(intrt, output);

    }

    public void sw(){
        int intrt = registerFile.getValue(Integer.parseInt(rt, 2));
        int intrs = Integer.parseInt(rs,2);
        int intimm = Integer.parseInt(imm,2);
        int index = registerFile.getValue(intrs) + intimm;
        stack.setMemory(index, intrt);
    }

    public void j(){
        registerFile.setPC(Integer.parseInt(address));
    }

    public void jr(){
        int intrs = Integer.parseInt(rs,2);
        int x = registerFile.getValue(intrs);
        registerFile.setPC(x-1);
    }

    public void jal(){
        registerFile.setValue(31, registerFile.returnPC()+1);
        registerFile.setPC(Integer.parseInt(address)-1);
    }

}

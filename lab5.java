/** Name:  Michael Murray, Daniel Yi
 Section:  3
 Description:  Our working MIPS assembler. **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class lab5 {
    public static void main(String[] args) {
        /** SETUP **/
        instruction.setInstructions();
        register.setRegisters();

        /** First pass: find labels and store addresses **/
        // create a hashmap that stores the label and location
        // to get the address of a string label, will need to add ":" to the label and use that hashCode.
        Map<Integer, Integer> labels = new HashMap<>();

        Scanner sc = null;
        try {
            sc = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int currLine = -1;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] filteredLine = line.trim().split("\\s++");
            char[] chars = filteredLine[0].toCharArray();

            // increments line number if it is not a blank line or all comments
            if (chars.length != 0) {
                if (chars[0] != '#') {
                    currLine++;
                }
            }
            boolean valid = true;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ':') {
                    if (valid && i >= 1) {
                        String label = filteredLine[0].substring(0, i);
                        labels.put(label.hashCode(), currLine);
                    }
                    // make sure chars are 0-9, A-Z, a-z (0-9, 65-90, 97-122)
                    if (!((int) chars[i] <= 9 || (65 <= (int) chars[i] && (int) chars[i] <= 90) ||
                            (97 <= (int) chars[i] && (int) chars[i] <= 122))) {
                        valid = false;
                    }
                }
            }
        }

        /** Second Pass: filter out comments, translate assembly to binary and print out.
         *  labels is the name of the hashmap storing the line number of each label in the .asm file **/

        HashMap<Integer, instruction> instructions = new HashMap<>();

        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        currLine = 0;
        while (sc2.hasNextLine()) {
            String line = sc2.nextLine();
            instruction inst = instruction.readInstruction(line, currLine, labels);


            // if the line contains an instruction, execute the instruction and increment currLine
            if (inst.getIsValid()) {
                instructions.put(currLine, inst);
                currLine = currLine + 1;
            }
        }

        registerFile.resetPC();
        boolean interactiveMode = false;
        Scanner sc3 = null;

        try {
            sc3 = new Scanner(new File(args[1]));
        } catch (Exception e) {
            //Interactive mode
            interactiveMode = true;
            sc3 = new Scanner(System.in);
        }
        String inputString;

        try {
            GHR.initGHR(Integer.parseInt(args[2]));
        } catch (Exception e) {
            GHR.initGHR(2);
        }


        while (interactiveMode || sc3.hasNextLine()) {
            System.out.print("mips> ");
            inputString = sc3.nextLine();
            if (!interactiveMode) {
                System.out.println(inputString);
            }

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

            if (inputString.equals("h")) {
                prompt.h();
            } else if (inputString.equals("d")) {
                prompt.d();
            } else if (inputString.equals("s")) {
                prompt.s(labels, instructions, true);
            } else if (inputString.equals("r")) {
                prompt.r(labels, instructions);
            } else if (inputString.equals("c")) {
                prompt.c();
            } else if (inputString.equals("q")) {
                prompt.q();
            } else if (inputString.equals("b")){
                prompt.b();
            } else {
                String[] str = inputString.split(" ");
                if (str[0].equals("s")) {
                    int num = Integer.parseInt(str[1]);
                    prompt.sNum(num, labels, instructions);

                } else {
                    int num1 = Integer.parseInt(str[1]);
                    int num2 = Integer.parseInt(str[2]);
                    prompt.mNum1Num2(num1, num2);
                }
            }

        }


    }
}
public class stack {
    private static int[] dataMemory = new int[8192];

    public static void clearMemory(){
        dataMemory = new int[8192];
    }
    public static int accessMemory(int index) {
        return dataMemory[index];
    }

    public static void setMemory(int index, int value) {
        dataMemory[index] = value;
    }
    public static int getMemory(int index) {
        return dataMemory[index];
    }
}

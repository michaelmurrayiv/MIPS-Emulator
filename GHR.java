public class GHR {
    private static int size;
    private static int GHR = 0;
    private static int[] predArr;

    private static int corrPred = 0;

    private static int totalBranches = 0;

    public static void initGHR(int bitsize){
        //set size
        size = bitsize;
        //set array size
        predArr = new int[2<<size-1];
    }

    public static void incrementGHR(){
        GHR = GHR << 1;
        GHR += registerFile.taken;

        if (GHR >= (2<<size-1)){
            GHR = GHR % (2<<size-1);
        }
        registerFile.taken = 0;

    }

    public static void taken(){

        if (predArr[GHR] != 3) {
            predArr[GHR]++;
        }
        incrementGHR();
    }

    public static void notTaken(){
        if (predArr[GHR] != 0){
            predArr[GHR]--;
        }
        incrementGHR();
    }

    public static void GHRExec(){
        int prediction = predArr[GHR];
        //False: taken = 0
        if (registerFile.taken == 0){
            //if prediction was correct, need to subtract one
            if (prediction <= 1) {
                corrPred++;
            }
            notTaken();
        }
        //True: taken = 1
        else if (registerFile.taken == 1){
            if (prediction >= 2){ // if prediction was correct
                corrPred++;
            }
            taken();
        }

        totalBranches += 1;
    }

    public static void outputPredictorAccuracy() {
        //(for ghr size = 2): accuracy 61.79% (8360 correct predictions, 13529 predictions)
        float accuracy;
        if (totalBranches > 0) {
            accuracy = 100 * (float) corrPred / totalBranches;
        } else {
            accuracy = 0;
        }
        System.out.print("\naccuracy ");
        System.out.printf("%.2f", accuracy);
        System.out.println("% (" + Integer.toString(corrPred) + " correct predictions, " + Integer.toString(totalBranches) + " predictions)\n");
    }
}

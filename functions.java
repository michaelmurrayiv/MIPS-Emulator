public class functions {
    public static int toDecimal(String binary){
        int x = Integer.parseInt(binary);
        return x;
    }

    public static boolean isNumeric(String strNum){
        if (strNum == null) {
            return false;
        }
        try {
            int x = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }

    public static String to5Binary(String str){
        String x = String.format("%05d", Integer.parseInt(str));
        return x;
    }

    public static String to16Binary(String str) {//TODO: get negative sign extension working.
        if (Integer.parseInt(str) >= 0) {
            String x = Integer.toBinaryString(Integer.parseInt(str));
            return x;
        } else {
            String a = Integer.toBinaryString(Integer.parseInt(str));
            String res = "";
            char[] c = a.toCharArray();
            for (int i = 16; i < 32; i++) {
                res = res + c[i];
            }
            return res;

        }

    }

    public static String to26Binary(String str){
        //TODO: IMPLEMENT TRANSLATION FOR LABELS (ONLY NEEDED IN 16 AND 26)
        String x = String.format("%026d", Integer.parseInt(str));
        return x;
    }


    public static String[] splitStrings(String[] input) {
        String res = "";
        for (String s : input) {
            char[] chars = s.toCharArray();
            String currStr = "";
            for (char c : chars) {
                if (c == '#') {
                    res = res + currStr + ",#,";
                    currStr = "";
                } else if (c == '$') {
                    res = res + currStr + ",";
                    currStr = "$";
                } else if (c == ':') {
                    currStr = "";
                } else {
                    currStr = currStr + c;
                }
            }
            res = res + currStr + ",";
        }
        return res.split(",");
    }

    }

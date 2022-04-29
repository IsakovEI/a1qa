package util;

import java.util.Arrays;

public class IWriter {

    public static void printResult(String[][] result) {
        int[] columnsLength = getColumnLength(result);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                char[] columnValue = getChars(result[i][j], columnsLength[j]);
                sb.append(columnValue);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static char[] getChars(String s, int i1) {
        char[] columnValue = new char[i1];
        char[] tmp = s.toCharArray();
        Arrays.fill(columnValue, ' ');
        System.arraycopy(tmp, 0, columnValue, 0, tmp.length);
        return columnValue;
    }

    private static int[] getColumnLength(String[][] array){
        int[] columnsLength = new int[array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0, k = 0; j < array[i].length; j++, k++) {
                if (k == array[0].length) {
                    k = 0;
                }
                if (array[i][j].length() > columnsLength[k]) {
                    columnsLength[k] = array[i][j].length();
                }
            }
        }
        return columnsLength;
    }


}

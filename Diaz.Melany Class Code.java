package com;
import java.lang.reflect.Array;
import java.util.ArrayList;


/*
   Class: LongestClassSubsequence

   Explores the dynamic programming approach to the longest common subsequence problem
   The only allowed characters are "ACGT".

   Author:  Melany Diaz
   Creation date:  2/29/2016

   Modifications:
        Date         Name          reason
        3/5/2016    Melany Diaz     Debugging
        3/7/2016    Melany Diaz     Fixing one relevant error in print method

*/


/**
 * Given two strings with the characters "ACGT" We'll be printing the longest
 * subsequence using dynamic programming.
 *
 @author Melany Diaz
 */
public class LongestClassSubsequence {

    public static String LCM = "";
    public static String answer = "";

    /**
     * lengthLCS takes two subsequences and computes a table in
     * row-major order. This table is used to construct the
     * optimal solution. The procedure returns the table that
     * contains the length of an LCS of the two subsequences.
     *
     * @param subseqX subseqY  the two subsequences.
     * @returns int[][] b
     */

    private static String[][] lengthLCS(String subseqX, String subseqY) {
        char[] b = subseqX.toCharArray();
        char[] a= subseqY.toCharArray();
        int m = b.length;
        int n = a.length;


        //mke the c table
        int[][]LCS = new int[m +1][n +1];
        // make the b table
        String[][] opt = new String[m + 1][n + 1];

        //fill in LCS aka the c table
        for(int i = 0; i <= m; i++){
            LCS[i][0] = 0;
            opt[i][0] = "0";
        }
        for(int i = 0; i <= n; i++){
            LCS[0][i] = 0;
            opt[0][i] = "0";
        }

        //fill opt aka the b table
        for (int i =  1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (b[i-1] == a[j-1]) {
                    LCS[i][j] = LCS[i-1][j-1]+1;
                    opt[i][j] = "diagonal";
                }
                else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j-1]);  //deciding whether to use the entry above or diagonal
                    if(LCS[i][j] == LCS[i-1][j]) {
                        opt[i][j] = "up";
                    }
                    else //so when LCS[i][j] = LCS[i][j-1]
                        opt[i][j] = "to the left";
                    }
                }
            }
//        //prints out the tables like in the book
//        for (int i = 0; i <= m; i++) {
//            for (int j = 0; j <= n; j++) {
//                System.out.print(" " + LCS[i][j]);
//            }
//            System.out.println();
//        }

        //return the table
        return opt;
    }


    /**
     * printLCS prints out an LCS of two subsequences in the proper,
     * forward order
     *
     * @param opt,    the table
     * @param subseqX
     * @param subseqY
     */
    private static String printLCS(String[][] opt, String subseqX, String subseqY, int i,int j){

        String x = opt[i][j];
        char[] m = subseqX.toCharArray();

        if(i == 0 || j ==0)
            return "";

            if (opt[i][j] == "diagonal") {
                answer = m[i - 1] + answer;
                printLCS(opt, subseqX, subseqY, i-1, j-1);
            } else if (opt[i][j] == "to the left") {
                printLCS(opt, subseqX, subseqY, i, j-1);
            } else if (opt[i][j] == "up") {
                printLCS(opt, subseqX, subseqY, i-1, j);
            }
            x = opt[i][j];

        return answer;
    }



    public static void main(String[] args) {
        String subseqX = "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";
        String subseqY = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";

        String x = "AGGCATT";
        String y = "GACGAT";


        String w = "ACCAT";
        String q = "ACGAT";

        System.out.println("Substring 1: " + subseqX  + "\n" + "substring 2: " + subseqY);
        //finds the LCS
        String[][] check0 = lengthLCS(subseqX, subseqY);
        //prints the LCS
        String lcs= printLCS(check0, subseqX, subseqY, subseqX.length(), subseqY.length());
        System.out.println(lcs);
        System.out.println("LCS Length is: " + lcs.length());

//        /**
//         * Confirming bench checks
//         */
//        System.out.println("Substring 1: " + x + "\n" + "substring 2: " + y);
//        //finds the LCS
//        String[][] check = lengthLCS(x, y);
//        //prints the LCS
//        printLCS(check, x, y, x.length(), y.length());
//
//        System.out.println("-----------------------");
//        System.out.println("Substring 1: " + w  + "\n" + "substring 2: " + q);
//        //finds the LCS
//        String[][] check2 = lengthLCS(w, q);
//        //prints the LCS
//        printLCS(check2, w, q, w.length(), q.length());


    }
}
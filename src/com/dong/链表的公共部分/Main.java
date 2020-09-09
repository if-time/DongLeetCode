package com.dong.链表的公共部分;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) {
        while (in.hasNext()) {
//            int n = Integer.valueOf(in.nextLine());
            int n = in.nextInt();
//            String nVal = in.nextLine().replace(" ", "");
            int[] nVal = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                nVal[i] = in.nextInt();
            }
//            int m = Integer.valueOf(in.nextLine());
            int m = in.nextInt();
//            String mVal = in.nextLine().replace(" ", "");
            int[] mVal = new int[m + 1];
            for (int i = 0; i <= m; i++) {
                mVal[i] = in.nextInt();
            }
            int[][] matrix = new int[n + 1][m + 1];
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < m + 1; j++) {
                    matrix[i][j] = 0;
                }
            }
            int[][] path = new int[n + 1][m + 1];
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
//                    if (nVal.charAt(i - 1) == mVal.charAt(j - 1)) {
                    if (nVal[i - 1] == mVal[j - 1]) {
                        matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    } else if (matrix[i - 1][j] >= matrix[i][j - 1]) {
                        matrix[i][j] = matrix[i - 1][j];
                        path[i][j] = 1;
                    } else {
                        matrix[i][j] = matrix[i][j - 1];
                        path[i][j] = -1;
                    }
                }
            }
            printPath(path, nVal.toString(), n, m);
        }
        in.close();
    }

    static boolean first = true;

    private static void printPath(int[][] path, String nVal, int n, int m) {
        if (n == 0 || m == 0) {
            return;
        }
        if (path[n][m] == 0) {
            printPath(path, nVal, n - 1, m - 1);
            if (first) {
                System.out.print(nVal.charAt(n - 1));
                first = false;
            } else {
                System.out.print(" " + nVal.charAt(n - 1));
            }

        } else if (path[n][m] == 1) {
            printPath(path, nVal, n - 1, m);
        } else {
            printPath(path, nVal, n, m - 1);
        }
    }
}
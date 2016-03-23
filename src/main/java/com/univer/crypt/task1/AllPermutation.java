package com.univer.crypt.task1;

import java.util.ArrayList;
import java.util.Scanner;

public class AllPermutation {
    ArrayList<int[]> ans = new ArrayList<int[]>();
    Scanner console = new Scanner(System.in);

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    boolean IsCorrect(int[] arr) {
        int first = arr[0];
        int temp = first;
        int i = 0;
        while (i < arr.length && first != arr[temp - 1]) {
            i++;
            temp = arr[temp - 1];
        }
        return i == arr.length - 1 && first == arr[temp - 1];
    }

    void gen(int k, int n, int[] arr) {
        if (k == n) {
            if (IsCorrect(arr)) {
                ans.add((int[]) arr.clone());
            }
        } else {
            for (int j = k; j < arr.length; j++) {
                swap(arr, k, j);
                gen(k + 1, n, arr);
                swap(arr, k, j);
            }
        }
    }

    void run() {
        int n = Integer.parseInt(console.nextLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        gen(0, n, arr);

        String[] stringAns = new String[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < n; j++) {
                temp.append(ans.get(i)[j]);
                temp.append(' ');
            }
            stringAns[i] = temp.toString();
        }
    }


    public static void main(String[] args) {
        new AllPermutation().run();
    }
}

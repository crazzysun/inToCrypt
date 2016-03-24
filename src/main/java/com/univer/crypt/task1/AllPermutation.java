package com.univer.crypt.task1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AllPermutation {
    ArrayList<int[]> permutations = new ArrayList<int[]>();
    Scanner console = new Scanner(System.in);
    Scanner file;
    PrintWriter out;

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
                permutations.add(arr.clone());
            }
        } else {
            for (int j = k; j < arr.length; j++) {
                swap(arr, k, j);
                gen(k + 1, n, arr);
                swap(arr, k, j);
            }
        }
    }

    void run() throws FileNotFoundException {
        out = new PrintWriter(new FileOutputStream("messages.txt"));

        int n = Integer.parseInt(console.nextLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        gen(0, n, arr);

        file = new Scanner(new FileInputStream("cipher_text.txt"));

        String s = "";
        while (file.hasNext()) {
            s += file.nextLine().replaceAll(" ", "");
        }
        while (s.length() % n != 0) {
            s += Character.toLowerCase(s.charAt(0));
        }

        for (int[] permutation : permutations) {
            crypt(s, permutation);
        }
    }

    void crypt(String s, int[] key) {
        for (int i = 0; i < key.length; i++) {
            key[i]--;
            System.out.print(key[i] + " ");
        }
        System.out.println();

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i += key.length) {
            for (int j = 0; j < key.length; j++) {
                res.append(s.charAt(i + key[j]));
            }
        }
        out.print(res);
        System.out.println(res);
        out.flush();
    }


    public static void main(String[] args) throws FileNotFoundException {
        new AllPermutation().run();
    }
}

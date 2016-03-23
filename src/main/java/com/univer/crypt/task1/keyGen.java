package com.univer.crypt.task1;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class keyGen {
    int keyLen;
    int[] key;
    Scanner in;

    void run() throws FileNotFoundException {
        in = new Scanner(System.in);

        System.out.println("Input key lenght:");
        keyLen = in.nextInt();
        key = new int[keyLen];
        genKey();
    }

    void genKey() throws FileNotFoundException {
        int[] arr = new int[keyLen];
        for (int i = 0; i < keyLen - 1; i++) {
            arr[i] = i + 1;
        }
        Random r = new Random();
        for (int i = 0; i < keyLen - 1; i++) {
            swap(arr, i, r.nextInt(keyLen - 1));
        }
        arr[keyLen - 1] = 0;
        key[0] = arr[0];
        for (int i = 1; i < keyLen; i++) {
            key[arr[i - 1]] = arr[i];
        }

        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("key.txt")));

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < keyLen; i++) {
            res.append(key[i]).append(" ");
        }

        out.print(res);
        System.out.println(res.toString());
        out.close();
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new keyGen().run();
    }
}

package com.univer.crypt.task2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class task21 {
    Scanner console = new Scanner(System.in);
    Scanner file1;
    Scanner file2;
    Scanner file3;
    PrintWriter out1;
    PrintWriter out2;
    PrintWriter out3;

    void run() throws FileNotFoundException {
        file1 = new Scanner(new FileInputStream("textRand.txt"));
        file2 = new Scanner(new FileInputStream("textEng.txt"));
        file3 = new Scanner(new FileInputStream("textRus.txt"));
        out1 = new PrintWriter(new FileOutputStream("resultIdx.txt"));
        out2 = new PrintWriter(new FileOutputStream("resultIdxSovp.txt"));
        String s1 = file1.nextLine();
        String t1 = file1.nextLine();

        String s2 = file2.nextLine();
        String t2 = file2.nextLine();

        String s3 = file3.nextLine();
        String t3 = file3.nextLine();


        out1.printf("%.8f %.8f %.8f\n", getI(s1, t1), getI(s2, t2), getI(s3, t3));
        out2.printf("%.8f %.8f %.8f\n", getIS(s1, t1, 1), getIS(s2, t2, 2), getIS(s3, t3, 3));


        out1.close();
        out2.close();
        out3.close();
    }

    double getIS(String s, String t, int f) {
        int n = (f == 3) ? 32 : 26;

        Map<Character, Integer> map1 = new HashMap<Character, Integer>();
        Map<Character, Integer> map2 = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            if (!map1.containsKey(s.charAt(i))) {
                map1.put(s.charAt(i), 0);
            }

            map1.put(s.charAt(i), map1.get(s.charAt(i)) + 1);

            if (!map2.containsKey(t.charAt(i))) {
                map2.put(t.charAt(i), 0);
            }

            map2.put(t.charAt(i), map2.get(t.charAt(i)) + 1);
        }

        double sum = 0;

        for (char i = 'a'; i <= 'z'; i++) {
            double tmp1 = 0;
            double tmp2 = 0;
            if (map1.containsKey(i)) {
                tmp1 = map1.get(i) / 100.0;
            }

            if (map2.containsKey(i)) {
                tmp2 = map2.get(i) / 100.0;
            }

            sum += tmp1 * tmp2;
        }

        return sum;
    }

    double getI(String s, String t) {
        s = prep(s.toLowerCase());
        t = prep(t.toLowerCase());

        double n = s.length();

        int cur = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(i)) cur++;
        }

        return cur / n;
    }

    String prep(String s) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i))) res.append(s.charAt(i));
        }

        return res.toString().substring(0, 99);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new task21().run();
    }
}

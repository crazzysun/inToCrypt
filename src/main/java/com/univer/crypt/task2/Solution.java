package com.univer.crypt.task2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    Scanner console = new Scanner(System.in);
    Scanner file1;
    Scanner file2;
    Scanner file3;
    Scanner ini;
    PrintWriter out1;
    PrintWriter out2;
    PrintWriter out3;

    void run() throws FileNotFoundException {
        file1 = new Scanner(new FileInputStream("textRand.txt"));
        file2 = new Scanner(new FileInputStream("textEng.txt"));
        file3 = new Scanner(new FileInputStream("textRus.txt"));
        ini = new Scanner(new FileInputStream("ini.txt"));
        out1 = new PrintWriter(new FileOutputStream("resultIdx.txt"));
        out2 = new PrintWriter(new FileOutputStream("resultIdxSovp.txt"));
        out3 = new PrintWriter(new FileOutputStream("resultV.txt"));

        for (int i = 0; i < 4; i++) {


            String s1 = file1.nextLine();
            String t1 = file1.nextLine();

            String s2 = file2.nextLine();
            String t2 = file2.nextLine();

            String s3 = file3.nextLine();
            String t3 = file3.nextLine();

            out1.printf("%.8f %.8f %.8f\n", getI(s1, t1), getI(s2, t2), getI(s3, t3));
            out2.printf("%.8f %.8f %.8f\n", getIS(s1, t1, 1), getIS(s2, t2, 2), getIS(s3, t3, 3));
            vigenere(ini.nextLine(), ini.nextLine(), ini.nextLine(), ini.nextLine(), out3);

            out1.flush();
            out2.flush();
            out3.flush();
        }

        out1.close();
        out2.close();
        out3.close();
    }

    void vigenere(String alph, String key5, String key7, String message, PrintWriter out3) {
        alph = alph.toLowerCase();
        key5 = key5.toLowerCase();
        key7 = key7.toLowerCase();
        message = prep(message.toLowerCase());

        String[] s = new String[3];
        String[] ns = new String[3];
        s[0] = ns[0] = message;
        s[1] = ns[1] = encrypt(message, key5, alph);
        s[2] = ns[2] = encrypt(message, key7, alph);

        for (int j = 1; j < 15; j++) {
            for (int i = 0; i < 3; i++) {
                ns[i] = ns[i].substring(1) + ns[i].substring(0, 1);
                out3.printf("%.3f ", (getI(s[i], ns[i])));
            }

            out3.println();
            out3.flush();
        }
        out3.println();

    }

    String encrypt(String s, String key, String alph) {
      //  System.err.println(alph);
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = key.charAt(i % key.length());
            //System.err.println(a + "  " + b);

            res.append(alph.charAt((alph.indexOf(a) + alph.indexOf(b)) % alph.length()));
        }

        return res.toString();
    }

    double getIS(String s, String t, int f) {
        Map<Character, Integer> map1 = new HashMap<Character, Integer>();
        Map<Character, Integer> map2 = new HashMap<Character, Integer>();

        s = prep(s);
        t = prep(t);

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

        char start = 'a';
        char end = 'z';

        if (f == 3) {
            start = 'а';
            end = 'я';
        }

        //   System.out.println();
        for (char i = start; i <= end; i++) {
            //    System.out.print(i);
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
        //    System.out.println();

        return sum;
    }


    double getI(String s, String t) {
        s = prep(s.toLowerCase());
        t = prep(t.toLowerCase());

        int n = Math.min(s.length(), t.length());

        int cur = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == t.charAt(i)) cur++;
        }

        return cur / (n * 1.0);
    }

    String prep(String s) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i))) res.append(s.charAt(i));
        }

        String r = res.toString();
        if (r.length() > 100)
            r = r.substring(0, 100);

        return r;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Solution().run();
    }
}

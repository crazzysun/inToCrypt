package com.univer.crypt.task1;

import java.io.FileNotFoundException;

public class Solution {
    void run() throws FileNotFoundException {
        new keyGen().run();
        new GetCipherText().run();
        new KasiskiTest().run();
        new AllPermutation().run();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Solution().run();
    }
}

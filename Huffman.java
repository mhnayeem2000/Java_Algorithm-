package com.mycompany.huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Scanner;

class HuffmanNode {
    int item;
    char c;
    HuffmanNode left;
    HuffmanNode right;
}

class ImplementComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.item - y.item;
    }
}

public class Huffman {
    public static void printCode(HuffmanNode root, String s, Map<Character, String> encodingMap) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + "   |  " + s);
            encodingMap.put(root.c, s);
            return;
        }
        printCode(root.left, s + "0", encodingMap);
        printCode(root.right, s + "1", encodingMap);
    }

    public static String fixedLengthEncoding(String huffmanCode) {
        int length = 4; 
        if (huffmanCode.length() >= length) {
            return huffmanCode.substring(0, length);
        } else {
            StringBuilder sb = new StringBuilder(huffmanCode);
            while (sb.length() < length) {
                sb.append("0");
            }
            return sb.toString();
        }
    }

    public static int calculateOriginalBits(char[] charArray, int[] charfreq, Map<Character, String> encodingMap) {
        int originalBits = 0;
        for (int i = 0; i < charArray.length; i++) {
            originalBits += charfreq[i] * fixedLengthEncoding(encodingMap.get(charArray[i])).length();
        }
        return originalBits;
    }

    public static int calculateHuffmanBits(Map<Character, String> encodingMap, char[] charArray, int[] charfreq) {
        int huffmanBits = 0;
        for (int i = 0; i < charArray.length; i++) {
            String huffmanCode = encodingMap.get(charArray[i]);
            huffmanBits += huffmanCode.length() * charfreq[i];
        }
        return huffmanBits;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of characters: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        char[] charArray = new char[n];
        int[] charfreq = new int[n];

        System.out.println("Enter the characters and their frequencies:");

        for (int i = 0; i < n; i++) {
            System.out.print("Character " + (i + 1) + ": ");
            charArray[i] = scanner.nextLine().charAt(0);

            System.out.print("Frequency: ");
            charfreq[i] = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        }

        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new ImplementComparator());

        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();

            hn.c = charArray[i];
            hn.item = charfreq[i];
            hn.left = null;
            hn.right = null;

            q.add(hn);
        }

        HuffmanNode root = null;

        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();

            HuffmanNode y = q.peek();
            q.poll();

            HuffmanNode f = new HuffmanNode();

            f.item = x.item + y.item;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;

            q.add(f);
        }

        System.out.println("\nChar | Huffman code");
        System.out.println("--------------------");

        Map<Character, String> encodingMap = new HashMap<>();
        printCode(root, "", encodingMap);

        System.out.println("\nFixed Length Encoding:");
        for (int i = 0; i < n; i++) {
            String huffmanCode = encodingMap.get(charArray[i]);
            String fixedLengthCode = fixedLengthEncoding(huffmanCode);
            System.out.println(charArray[i] + "   |  " + fixedLengthCode);
        }

        int originalBits = calculateOriginalBits(charArray, charfreq, encodingMap);
        int huffmanBits = calculateHuffmanBits(encodingMap, charArray, charfreq);

        int bitsSaved = originalBits - huffmanBits;
        System.out.println("\nNumber of bits saved by Huffman encoding: " + bitsSaved);
    }
}

package com.company;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;

public class BloomFilter {

    private final int numHashes;
    private int capacity;
    private int bitsPerElement;
    private ArrayList<Hash> listOfHashes = new ArrayList<Hash>();
    private BitSet bitArray;
    private ArrayList<Integer> keys = new ArrayList<>();

    public BloomFilter(int k, int b, int n) {
        numHashes = k;
        bitsPerElement = b;
        capacity = n;

        bitArray = new BitSet(n);

        // generate the k different hashes needed
        for (int i = 0; i < numHashes; i++) {
            listOfHashes.add(new Hash(n));
        }
    }

    public void add(int j) {
        for (Hash h : listOfHashes) {
            int index = h.getIndex((j%capacity));
            this.bitArray.set(index);
        }
        this.keys.add(j);
    }

    public boolean contains(int j) {
        for (Hash h : listOfHashes) {
            int index = h.getIndex(j);
            if (!bitArray.get(index)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return keys.toString();
    }

    // used to authoritatively determine if a key is in the filter
    public boolean defThere(int j) {
        return this.keys.contains(j);
    }


    private class Hash {
        private HashMap<Integer, Integer> map;


        public Hash(int n) {
            map = new HashMap<Integer, Integer>();
            for (int i = 0; i < n; i++) {
                int value = (int) (Math.random() * 100);
                while (map.values().contains(value) || (value > n))
                    value = (int) (Math.random() * 100);
                map.put(i, value);
            }
            //System.out.println(map); used for debugging
        }

        public int getIndex(int i) {
            return this.map.get(i);
        }


    }
}




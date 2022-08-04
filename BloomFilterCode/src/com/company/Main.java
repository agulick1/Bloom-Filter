package com.company;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Creates a bloom filter with parameters
        // and says that we will be adding m elements to it
        int n = 20;
        int b = 16;
        int k = 8;
        int m = 6; // must be <= than 0.5*n

        int falsePositives = 0;


        // 64 bit array, 8 hashes
        BloomFilter bf = new BloomFilter(k, b, n);

        // I think it works! Pog
        // for our testing:
        // create m = (a number that meets the criteria) unique random items
        // add those m to the bloom filter
        // and also create a list of m different unique random items

        ArrayList<Integer> lst = new ArrayList<>();
        ArrayList<Integer> lst2 = new ArrayList<>();

        for(int i = 0; i<m; i++){
            int value = (int)(Math.random()*100);
            while(lst.contains(value) || (value >= n))
                value = (int)(Math.random()*100);
            lst.add(value);
        }

        for(int i = 0; i<m; i++){
            int value = (int)(Math.random()*100);
            while(lst.contains(value) || lst2.contains(value) || (value >= n))
                value = (int)(Math.random()*100);
            lst2.add(value);
        }

        System.out.println("List to be added: "+lst);
        System.out.println("List that will not be added: "+lst2);
        System.out.println();

        for(int i = 0; i < m; i++){
            bf.add(lst.get(i));
            System.out.println("The bloom filter at this point contains: "+bf);
            System.out.println("(Should be true) bloom filter contains "+lst.get(i)+
                    " is "+bf.contains(lst.get(i)));//should be true
            System.out.println("(Should be false) bloom filter contains "+lst2.get(i)+
                    " is "+bf.contains(lst2.get(i)));//should be false
            if(bf.contains(lst2.get(i)))
                falsePositives++;
            System.out.println("--------------");
        }
        System.out.println("Total number of False Positives: " + falsePositives);
        double FPR = (double)falsePositives/m;
        System.out.println("False Positive Rate: " + FPR);



        //double FPR = (double)falsePositives/m;



        // for testing          ^
        // for experimentation  v

        /*
        // Creates a bloom filter with parameters
        // and says that we will be adding m elements to it

        int n = 32;     // capacity
        int b = 16;     // bits per element
        //int k;        // num hashes
        int m = 10;     // must be <= than 0.5*n

        int falsePositives = 0;

        // create a loop that changes the value of k


        for(int k = 1; k < 32; k++) {
            // 32 bit array, k hashes
            BloomFilter bf = new BloomFilter(k, b, n);


            // creates two lists of length m, all unique random values
            // places the elements of lst into the bloom filter
            ArrayList<Integer> lst = new ArrayList<>();
            ArrayList<Integer> lst2 = new ArrayList<>();

            for(int i = 0; i<m; i++){
                int value = (int)(Math.random()*100);
                while(lst.contains(value) || (value >= n))
                    value = (int)(Math.random()*100);
                lst.add(value);
                bf.add(value);
            }

            for(int i = 0; i<m; i++){
                int value = (int)(Math.random()*100);
                while(lst.contains(value) || lst2.contains(value) || (value >= n))
                    value = (int)(Math.random()*100);
                lst2.add(value);
            }


            // loop goes through and counts the number of false positives
            for(int i = 0; i < m; i++){
                if(bf.contains(lst2.get(i)))
                    falsePositives++;
            }


            // k = [1,31]
            double FPR = (double)falsePositives/m;
            System.out.println(FPR);

            // reset for next time through loop
            falsePositives = 0;

        }
*/


    }
}

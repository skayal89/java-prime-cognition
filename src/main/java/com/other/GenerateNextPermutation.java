package com.other;

import java.util.Arrays;

public class GenerateNextPermutation {
    public int[] getNextNumber(int[] a){
        int n = a.length, i = n-1;

        while(i > 0 && a[i] <= a[i-1]){
            i--;
        }
        if(i <= 0){
            return a;
        }
        i--;
        int j = n-1;
        while(j >=0 && a[j] < a[i]){
            j--;
        }
        swap(a, i, j);

        Arrays.sort(a, i+1, n);
        return a;
    }

    public void swap(int a[], int i, int j){
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    public static void main(String[] args) {
        GenerateNextPermutation t = new GenerateNextPermutation();
//        Arrays.stream(t.getNextNumber(new int[]{1, 2, 3, 6, 5, 4})).forEach(System.out::println);
//        Arrays.stream(t.getNextNumber(new int[]{1,2,3})).forEach(System.out::println);
//        Arrays.stream(t.getNextNumber(new int[]{3,5,1})).forEach(System.out::println);
//        Arrays.stream(t.getNextNumber(new int[]{1,5,3})).forEach(System.out::println);

        System.out.println(Arrays.toString(t.getNextNumber(new int[]{1, 2, 3, 6, 5, 4})));
    }
}

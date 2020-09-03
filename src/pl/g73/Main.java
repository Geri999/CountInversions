package pl.g73;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter an array, I'll count inversions");

        Scanner sc = new Scanner(System.in);

        String[] tempArrayOfStrings = sc.nextLine().split(" ");
        int[] array = new int[tempArrayOfStrings.length];
        for (int i = 0; i < array.length; i++) {
            array[i]=Integer.parseInt(tempArrayOfStrings[i]);
        }

        long counter = mergeSort(array, 0, array.length);
        System.out.println("count inversions:"+counter);
    }

    private static long mergeSort(int[] array, int leftIncludeIndex, int rightExcludeIndex) {
        long counter = 0;

        if (rightExcludeIndex - leftIncludeIndex <= 1) return 0;
        int middle = (leftIncludeIndex + rightExcludeIndex) >> 1;
        counter += mergeSort(array, leftIncludeIndex, middle);
        counter += mergeSort(array, middle, rightExcludeIndex);
        counter += merge(array, leftIncludeIndex, middle, rightExcludeIndex);
        return counter;
    }

    private static long merge(int[] array, int leftInclude, int middle, int rightExclude) {
        int i = leftInclude;
        int j = middle;
        int k = 0; // index in tempArray
        int[] tempArray = new int[rightExclude - leftInclude];
        long counter = 0;

        while (i < middle && j < rightExclude) {
            if (array[i] <= array[j]) {  // correct order
                tempArray[k] = array[i];
                i++;
            } else {
                tempArray[k] = array[j];
                counter += middle - i;
                j++;
            }
            k++;
        }

        while (i < middle) {
            tempArray[k++] = array[i++];
        }
        while (j < rightExclude) {
            tempArray[k++] = array[j++];
        }
        System.arraycopy(tempArray, 0, array, leftInclude, tempArray.length);
        return counter;
    }
}
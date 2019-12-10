package com.piyush.geeksforgeeks;

import java.util.Arrays;

public class MergeSort {

  private static int[] sort(int[] arr, int start, int end){

    if(start == end){
      return new int[]{arr[start]};
    }

    int mid = start + (end-start)/2;

    int[] left = sort(arr, start, mid);
    int[] right = sort(arr, mid+1, end);

    return merge(left, right);
  }

  private static int[] merge(int[] left, int[] right){
    int leftIndex = 0;
    int rightIndex = 0;
    int minLen = Math.min(left.length, right.length);
    int[] result = new int[left.length + right.length];
    int index = 0;

    while (leftIndex < minLen && rightIndex < minLen){
      if(left[leftIndex] <= right[rightIndex]){
        result[index++] = left[leftIndex++];
      } else {
        result[index++] = right[rightIndex++];
      }
    }

    while (leftIndex < left.length){
      result[index++] = left[leftIndex++];
    }

    while (rightIndex < right.length){
      result[index++] = right[rightIndex++];
    }

    return result;

  }

  public static void main(String args[])
  {
    int arr[] = {12, 11, 13, 5, 6, 7};

    System.out.println("Given Array");
    System.out.println(Arrays.toString(arr));

    MergeSort ob = new MergeSort();
    arr = ob.sort(arr, 0, arr.length-1);

    System.out.println("\nSorted array");
    System.out.println(Arrays.toString(arr));
  }

}

package com.piyush.geeksforgeeks;

import java.util.Arrays;

public class HeapSort {

  private static int[] sort(int[] arr){
    int N = arr.length;

    // build heap
    for(int i = N/2 - 1; i >= 0; i--){
      heapify(arr, N, i);
    }

    for(int i = N - 1; i >= 0; i--){
      swap(arr, 0, i);
      heapify(arr, i, 0);
    }

    return arr;
  }

  private static void heapify(int[] arr, int N, int i){
    int root = arr[i];
    int l = 2*i + 1;
    int r = 2*i + 2;

    int largest = i;

    if(l < N && arr[l] > arr[largest]){
      largest = l;
    }

    if(r < N && arr[r] > arr[largest]){
      largest = r;
    }

    if(largest != i){
      swap(arr, i, largest);

      heapify(arr, N, largest);
    }
  }

  private static void swap(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String args[])
  {
    int arr[] = {12, 11, 13, 5, 6, 7};

    System.out.println("Given Array");
    System.out.println(Arrays.toString(arr));

    HeapSort ob = new HeapSort();
    arr = ob.sort(arr);

    System.out.println("\nSorted array");
    System.out.println(Arrays.toString(arr));
  }

}

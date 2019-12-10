package com.piyush.geeksforgeeks;

public class BinarySearchInRotatedArray {

  private static int solution(int[] arr, int n, int searchValue){
    return binarySearch(arr, 0, n-1, searchValue);
  }

  static int binarySearch(int[] arr, int start, int end, int searchValue){
    int mid = start + (end-start)/2;
    int smallIndex, bigIndex;

    // Left of mid.
    if(arr[start] < arr[mid]){
      smallIndex = start;
      bigIndex = mid;
    } else {
      smallIndex = mid;
      bigIndex = start;
    }

    if(searchValue > arr[smallIndex] && searchValue < arr[bigIndex]){
      return binarySearch(arr, start, mid, searchValue);
    } else if(searchValue == arr[smallIndex]){
      return smallIndex;
    } else if(searchValue == arr[bigIndex]){
      return bigIndex;
    }

    // Right of mid
    if(arr[mid+1] < arr[end]){
      smallIndex = mid + 1;
      bigIndex = end;
    } else {
      smallIndex = end;
      bigIndex = mid+1;
    }

    if(searchValue > arr[smallIndex] && searchValue < arr[bigIndex]){
      return binarySearch(arr, start, mid, searchValue);
    } else if(searchValue == arr[smallIndex]){
      return smallIndex;
    } else if(searchValue == arr[bigIndex]){
      return bigIndex;
    }

    return -1;
  }

  public static void main(String args[])
  {
    // Let us search 3 in below array
    int arr1[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
    int n = arr1.length;
    int key = 3;
    // System.out.println("Index of the element is : " + solution(arr1, n, key));

    arr1 = new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3};
    System.out.println("Index of the element is : " + solution(arr1, arr1.length, 6));
  }

}

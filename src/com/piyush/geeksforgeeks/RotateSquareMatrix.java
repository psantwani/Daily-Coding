package com.piyush.geeksforgeeks;

public class RotateSquareMatrix {

  private static void rotateMatrix(int N, int mat[][]) {

	for (int x = 0; x < N / 2; x++)
	{
	  for (int y = x; y < N-x-1; y++)
	  {
		// store current cell in temp variable
		int temp = mat[x][y];

		// move values from right to top
		mat[x][y] = mat[y][N-1-x];

		// move values from bottom to right
		mat[y][N-1-x] = mat[N-1-x][N-1-y];

		// move values from left to bottom
		mat[N-1-x][N-1-y] = mat[N-1-y][x];

		// assign temp to left
		mat[N-1-y][x] = temp;
	  }
	}
  }

  private static void displayMatrix(int N, int mat[][]) {
    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++){
        System.out.print(mat[i][j] + "\t");
	  }
	  System.out.println();
	}
  }

  public static void main(String[] args) {

	// Test Case 1
	int mat[][] = new int[][] {
		{1, 2, 3, 4},
		{5, 6, 7, 8},
		{9, 10, 11, 12},
		{13, 14, 15, 16}
	};

	rotateMatrix(4, mat);
	displayMatrix(4, mat);


	// Tese Case 2
	mat = new int[][] {
		{1, 2, 3},
		{4, 5, 6},
		{7, 8, 9}
	};

	rotateMatrix(3, mat);
	displayMatrix(3, mat);

	// Tese Case 3
	mat = new int[][] {
		{1, 2},
		{4, 5}
	};

	rotateMatrix(2, mat);
	displayMatrix(2, mat);
  }

}

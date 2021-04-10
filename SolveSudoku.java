import java.util.Scanner;

public class SolveSudoku {
	
	public static boolean SudokuSolver(int[][]ar ,boolean[][] fixedCells,int row,int col,int n) {
		if(row==n) {
			return true;
		}
		if(col==n) {
			return SudokuSolver(ar, fixedCells, row+1, 0, n);
		}
		if(fixedCells[row][col]) {
			return SudokuSolver(ar, fixedCells, row, col+1, n);
		}
		for(int num = 1; num<=n; num++) {
			boolean canSet = setNum(ar, row, col, n, num);
			if(canSet) {
				ar[row][col] = num;
				boolean solveRest = SudokuSolver(ar, fixedCells, row, col+1, n);
				if(solveRest) {
					return true;
				}
				else {
					ar[row][col] = 0;
				}
			}
		}
		return false;
	}
	
	public static boolean setNum(int[][]ar ,int row,int col,int n,int num) {
		for(int i=0;i<n;i++) 
		{
			if(ar[i][col]==num) {
				return false;
			}
			if(ar[row][i]==num) {
				return false;
			}
		}
		
		int SqrtN = (int)Math.sqrt(n);
		int MiniCol = (col/SqrtN)*SqrtN;
		int MiniRow = (row/SqrtN)*SqrtN;
		for(int i=MiniRow;i<MiniRow+SqrtN;i++) {
			for(int j=MiniCol;j<MiniCol+SqrtN;j++) {
				if(ar[i][j]==num) return false;
			}
		}
		return true;
	}
	
	public static void SetFixedCells(int[][]ar ,boolean[][] fixedCells,int n) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(ar[i][j]!=0) {
					fixedCells[i][j]=true;
				}
			}
		}
	}
	
	public static void printResult(int[][]ar,int n) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(ar[i][j]+" ");
				}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int[][] ar = {
//						 {5,3,0,0,7,0,0,0,0},
//						 {6,0,0,1,9,5,0,0,0},
//						 {0,9,8,0,0,0,0,6,0},
//						 {8,0,0,0,6,0,0,0,3},
//						 {4,0,0,8,0,3,0,0,1},
//						 {7,0,0,0,2,0,0,0,6},
//						 {0,6,0,0,0,0,2,8,0},
//						 {0,0,0,4,1,9,0,0,5},
//						 {0,0,0,0,8,0,0,7,9}
//						 };
		int n = 9;
		int[][] ar = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++)
				ar[i][j] = sc.nextInt();
		}
		boolean[][] fixedCells= new boolean[n][n];
		SetFixedCells(ar, fixedCells, n);
		boolean ans = SudokuSolver(ar, fixedCells, 0, 0, n);
		if(ans) {
			printResult(ar,n);
		}
		else {
			System.out.println("No Result Found");
		}
		

	}

}

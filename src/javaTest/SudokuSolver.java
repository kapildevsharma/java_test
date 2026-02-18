package javaTest;

public class SudokuSolver {

	 // Function to check if it's safe to place 'num' at mat[row][col]
    static boolean isSafe(int[][] mat, int row, int col, int num) {
        // Check if 'num' exists in the current row
        for (int x = 0; x < 9; x++) {
            if (mat[row][x] == num) {
            	System.out.println("mat["+row+"]["+x+"]: "+ mat[row][x]);
                return false;
            }
        }

        // Check if 'num' exists in the current column
        for (int x = 0; x < 9; x++) {
            if (mat[x][col] == num) {
            	System.out.println("mat["+x+"]["+col+"]: "+ mat[x][col]);
                return false;
            }
        }

        // Check if 'num' exists in the 3x3 sub-matrix
        int startRow = row - (row % 3);
        int startCol = col - (col % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mat[i + startRow][j + startCol] == num) {
                	System.out.println("mat["+i+" + startRow]["+j+" + "+startCol+"]: "+ mat[i + startRow][j + startCol]);
                    return false;
                }
            }
        }
        return true;
    }

    // Function to solve the Sudoku problem using backtracking
    static boolean solveSudokuRec(int[][] mat, int row, int col) {
        // Base case: If we've reached the end of the board (last column of last row)
        if (row == 8 && col == 9) {
            return true;
        }

        // If we've reached the end of the current row, move to the next row
        if (col == 9) {
            row++;
            col = 0;
        }

        // If the current cell is already filled, move to the next cell
        if (mat[row][col] != 0) {
            return solveSudokuRec(mat, row, col + 1);
        }

        // Try placing numbers from 1 to 9 in the current empty cell
        for (int num = 1; num <= 9; num++) {
            if (isSafe(mat, row, col, num)) {
                mat[row][col] = num; // Place the number
                if (solveSudokuRec(mat, row, col + 1)) {
                    return true; // If placing this number leads to a solution
                }
                mat[row][col] = 0; // Backtrack: remove the number if it doesn't lead to a solution
            }
        }
        return false; // No number can be placed in this cell
    }

    
    // Main function to initiate the Sudoku solving process
    static void solveSudoku(int[][] mat) {
        solveSudokuRec(mat, 0, 0);
    }
    
	public static void main(String[] args) {
        int[][] puzzle = {
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0},
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        System.out.println("Original Sudoku puzzle:");
        printBoard(puzzle);

        if (solveSudokuRec(puzzle, 0, 0)) {
            System.out.println("\nSolved Sudoku puzzle:");
            printBoard(puzzle);
        } else {
            System.out.println("\nNo solution exists for the given Sudoku puzzle.");
        }
    }

    // Helper function to print the Sudoku board
    static void printBoard(int[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(board[r][d] + " ");
            }
            System.out.println();
        }
    }

}

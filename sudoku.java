import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class sudoku {
     public static void main(String[] args) {
        List<List<Integer>> sudokuBoard = Arrays.asList(
           Arrays.asList(5, 3, 4, 6, 7, 8, 9, 1, 2),
            Arrays.asList(6, 7, 2, 1, 9, 5, 3, 4, 8),
            Arrays.asList(1, 9, 8, 3, 4, 2, 5, 6, 7),
            Arrays.asList(8, 5, 9, 7, 6, 1, 4, 2, 3),
            Arrays.asList(4, 2, 6, 8, 5, 3, 7, 9, 1),
            Arrays.asList(7, 1, 3, 9, 2, 4, 8, 5, 6),
            Arrays.asList(9, 6, 1, 5, 3, 7, 2, 8, 4),
            Arrays.asList(2, 8, 7, 4, 1, 9, 6, 3, 5),
            Arrays.asList(3, 4, 5, 2, 8, 6, 1, 7, 9)
        );

        int result = checkSudoku(sudokuBoard);
        if (result == 1) {
            System.out.println("The Sudoku is valid.");
        } else {
            System.out.println("The Sudoku is invalid.");
        }
    }

    public static int checkSudoku(List<List<Integer>> sudoku) {
        for (int i = 0; i < 9; i++) {
            HashSet<Integer> row = new HashSet<>();
            HashSet<Integer> column = new HashSet<>();
            HashSet<Integer> block = new HashSet<>();

            for (int j = 0; j < 9; j++) {
                int numRow = sudoku.get(i).get(j);
                int numColumn = sudoku.get(j).get(i);

                // Check if the number is within the valid range and if it's not already present in the row/column
                if (numRow <= 0 || numRow > 9 || !row.add(numRow) || numColumn <= 0 || numColumn > 9 || !column.add(numColumn)) {
                    return 0;
                }

                // Calculate the start of the 3x3 block
                int rowIndex = 3 * (i / 3);
                int columnIndex = 3 * (i % 3);

                // Get the number in the current block
                int numBlock = sudoku.get(rowIndex + j / 3).get(columnIndex + j % 3);

                // Check if the number is within the valid range and if it's not already present in the block
                if (numBlock <= 0 || numBlock > 9 || !block.add(numBlock)) {
                    return 0;
                }
            }
        }
        // If all checks pass, the sudoku is valid
        return 1;
    }
}


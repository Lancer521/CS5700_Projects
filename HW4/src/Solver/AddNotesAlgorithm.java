package Solver;

import Puzzle.Puzzle;

/**
 * Created by Ty on 11/16/2016.
 * Add notes to every "empty" cell in the puzzle
 * Technically, adding notes means removing known row/column/block values from an empty cell's possible values list
 *
 * IMPORTANT: This algorithm differs from the others in that it will do the entire board at once, rather than focusing on one cell at a time
 */
public class AddNotesAlgorithm extends SudokuAlgorithm {

  @Override
  public boolean applyMethod(Puzzle puzzle, int currRow, int currCol) {
    int blockSize = ((Double) Math.sqrt(puzzle.gridSize)).intValue();

    for (int i = 0; i < puzzle.gridSize; i++) {
      updatePossibleValuesInRow(puzzle, i);
      updatePossibleValuesInColumn(puzzle, i);
    }
    for (int i = 0; i < puzzle.gridSize; i += blockSize) {
      for (int j = 0; j < puzzle.gridSize; j += blockSize) {
        updatePossibleValuesInBlock(puzzle, i, j);
      }
    }
    return true;
  }
}

package Solver;

import Puzzle.Puzzle;

/**
 * Created by Ty on 11/16/2016.
 * Search for a Locked Candidate within a block.
 * If found, remove from possible values of the other cells in that row/column outside of the block
 */
public class LockedCandidateRowColAlgorithm extends SudokuAlgorithm {

  @SuppressWarnings("Duplicates")
  @Override
  /* Always returns false */
  public boolean applyMethod(Puzzle puzzle, int currRow, int currCol) {

    boolean didSomething = false;

    //Find the top left square of the block to which this cell belongs
    int blockRow = puzzle.calculateBlockIndex(currRow);
    int blockCol = puzzle.calculateBlockIndex(currCol);

    for (Character c : puzzle.cells[currRow][currCol].possibleValues) {
      if (isLockedRow(puzzle, currRow, currCol, c)) {
        //Remove from other cells in row
        for (int col = 0; col < puzzle.gridSize; col++) {
          boolean canRemove = true;
          for (int i = blockCol; i < blockCol + puzzle.blockSize; i++) {
            if (col == i) {
              canRemove = false;
              break;
            }
          }
          if (canRemove && puzzle.cells[currRow][col].possibleValues.remove(c)) didSomething = true;
        }
        //TODO: update
      } else if (isLockedColumn(puzzle, currRow, currCol, c)) {
        //Remove from other cells in column
        for (int row = 0; row < puzzle.gridSize; row++) {
          boolean canRemove = true;
          for (int i = blockRow; i < blockRow + puzzle.blockSize; i++) {
            if (row == i) {
              canRemove = false;
              break;
            }
          }
          if (canRemove && puzzle.cells[row][currCol].possibleValues.remove(c)) didSomething = true;
        }
        //TODO: update
      }
    }
    return didSomething;
  }

  /**
   * Check for search value in possible values of each row in a block that does not contain the current cell.
   *
   * @param puzzle      Puzzle to be examined
   * @param currRow     Row of the current cell
   * @param currCol     Column of the current cell
   * @param searchValue Value to be searched for
   * @return false if the value is found in another row, else true
   */
  @SuppressWarnings("Duplicates")
  private boolean isLockedRow(Puzzle puzzle, int currRow, int currCol, char searchValue) {
    //TODO: appears to be looking beyond block - Puzzle Ten, 3rd LockedCandidate (HS, NS, LC), cell[4][0] possval 4
    int blockRow = puzzle.calculateBlockIndex(currRow);
    int blockCol = puzzle.calculateBlockIndex(currCol);

    for (int row = blockRow; row < blockRow + puzzle.blockSize; row++) {
      if (row != currRow) {
        for (int col = blockCol; col < blockCol + puzzle.blockSize; col++) {
          if (puzzle.cells[row][col].possibleValues.contains(searchValue)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Check for search value in possible values of each column in a block that does not contain the current cell.
   *
   * @param puzzle      Puzzle to be examined
   * @param currRow     Row of the current cell
   * @param currCol     Column of the current cell
   * @param searchValue Value to be searched for
   * @return false if the value is found in another column, else true
   */
  @SuppressWarnings("Duplicates")
  private boolean isLockedColumn(Puzzle puzzle, int currRow, int currCol, Character searchValue) {
    int blockRow = puzzle.calculateBlockIndex(currRow);
    int blockCol = puzzle.calculateBlockIndex(currCol);

    for (int col = blockCol; col < blockCol + puzzle.blockSize; col++) {
      if (col != currCol) {
        for (int row = blockRow; row < blockRow + puzzle.blockSize; row++) {
          if (puzzle.cells[row][col].possibleValues.contains(searchValue)) {
            return false;
          }
        }
      }
    }
    return true;
  }
}

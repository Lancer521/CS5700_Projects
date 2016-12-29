package Solver;

import Puzzle.Puzzle;

import java.util.List;

/**
 * Created by Ty on 11/16/2016.
 * If two cells in a row, column, or block have exactly 2 possible values, and they are the same 2 values for both cells,
 * then remove those values from anywhere else in that row or column, and the block.
 */
public class NakedPairsAlgorithm extends SudokuAlgorithm {

  @Override
  public boolean applyMethod(Puzzle puzzle, int currRow, int currCol) {

    boolean didSomething = false;

    if (puzzle.cells[currRow][currCol].possibleValues.size() != 2) return false;
    List<Character> list = puzzle.cells[currRow][currCol].possibleValues;

    //Check row and column
    for (int index = 0; index < puzzle.gridSize; index++) {
      if (index != currCol && puzzle.cells[currRow][index].possibleValues.equals(list)) {
        if(removePairValuesFromRow(puzzle, currRow, list)){
          didSomething = true;
        }
        break;
      }
      if (index != currRow && puzzle.cells[index][currCol].possibleValues.equals(list)) {
        if(removePairValuesFromColumn(puzzle, currCol, list)){
          didSomething = true;
        }
        break;
      }
    }

    //Check block
    int blockRow = puzzle.calculateBlockIndex(currRow);
    int blockCol = puzzle.calculateBlockIndex(currCol);
    for (int row = blockRow; row < blockRow + puzzle.blockSize && row < puzzle.gridSize; row++) {
      for (int col = blockCol; col < blockCol + puzzle.blockSize && col < puzzle.gridSize; col++) {
        if (!(row == currRow && col == currCol) && puzzle.cells[row][col].possibleValues.equals(list)) {
          if(removePairValuesFromBlock(puzzle, currRow, currCol, list)){
            didSomething = true;
          }
        }
      }
    }
    return didSomething;
  }

  @SuppressWarnings("Duplicates")
  private boolean removePairValuesFromRow(Puzzle puzzle, int currRow, List<Character> list) {
    boolean didSomething = false;
    for (int i = 0; i < puzzle.gridSize; i++) {
      if (!puzzle.cells[currRow][i].possibleValues.equals(list)) {
        if(puzzle.cells[currRow][i].possibleValues.remove(list.get(0))) didSomething = true;
        if(puzzle.cells[currRow][i].possibleValues.remove(list.get(1))) didSomething = true;
      }
    }
    return didSomething;
  }

  @SuppressWarnings("Duplicates")
  private boolean removePairValuesFromColumn(Puzzle puzzle, int currCol, List<Character> list) {
    boolean didSomething = false;
    for (int i = 0; i < puzzle.gridSize; i++) {
      if (!puzzle.cells[i][currCol].possibleValues.equals(list)) {
        if(puzzle.cells[i][currCol].possibleValues.remove(list.get(0))) didSomething = true;
        if(puzzle.cells[i][currCol].possibleValues.remove(list.get(1))) didSomething = true;
      }
    }
    return didSomething;
  }

  @SuppressWarnings("Duplicates")
  private boolean removePairValuesFromBlock(Puzzle puzzle, int currRow, int currCol, List<Character> list) {
    boolean didSomething = false;
    int blockRow = puzzle.calculateBlockIndex(currRow);
    int blockCol = puzzle.calculateBlockIndex(currCol);
    for (int row = blockRow; row < blockRow + puzzle.blockSize && row < puzzle.gridSize; row++) {
      for (int col = blockCol; col < blockCol + puzzle.blockSize && col < puzzle.gridSize; col++) {
        if (!puzzle.cells[row][col].possibleValues.equals(list)) {
          if (puzzle.cells[row][col].possibleValues.remove(list.get(0))) didSomething = true;
          if (puzzle.cells[row][col].possibleValues.remove(list.get(1))) didSomething = true;
        }
      }
    }
    return didSomething;
  }
}

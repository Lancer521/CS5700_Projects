package Solver;

import Puzzle.Puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ty on 11/16/2016.
 * *
 */
public class Solver {

  List<SudokuAlgorithm> algorithms;

  public void solve(Puzzle puzzle) {

    new AddNotesAlgorithm().applyMethod(puzzle, 0, 0);

    if (!isValidPuzzle(puzzle)) {
      puzzle.result = Puzzle.Result.BAD_PUZZLE;
      return;
    }
    if (!isSolvablePuzzle(puzzle)) {
      puzzle.result = Puzzle.Result.UNSOLVABLE;
      return;
    }

    initializeAlgorithmsList();

    int count = 1;
    while (!isSolved(puzzle)) {

      if(!algorithms.get(0).apply(puzzle)){
        if(!algorithms.get(1).apply(puzzle)){
          if(!algorithms.get(2).apply(puzzle)){
            if(!algorithms.get(3).apply(puzzle)){
              if(!algorithms.get(4).apply(puzzle)) {
                puzzle.result = Puzzle.Result.MULTIPLE_SOLUTIONS;
                return;
              }
            }
          }
        }
      }
    }
    puzzle.result = Puzzle.Result.SOLVED;
  }

  /**
   * Add to list in order of preference (most preferred algorithm first)
   */
  private void initializeAlgorithmsList() {
    algorithms = new ArrayList<>();
    algorithms.add(new SinglesAlgorithm());
    algorithms.add(new HiddenSinglesAlgorithm());
    algorithms.add(new NakedPairsAlgorithm());
    algorithms.add(new LockedCandidateRowColAlgorithm());
    algorithms.add(new LockedCandidateBlockAlgorithm());
  }

  public boolean isSolved(Puzzle puzzle) {
    return puzzle.isFull() && isLegalState(puzzle);
  }

  /**
   * A puzzle is not valid if it:
   * a) is not formatted correctly
   * b) doesn't contain the provided symbols
   *
   * @param puzzle Puzzle to be validated
   * @return true if parts a, b, and c are satisfied, else false
   */
  public boolean isValidPuzzle(Puzzle puzzle) {
    return isFormattedCorrectly(puzzle) && hasCorrectSymbols(puzzle);
  }

  /**
   * A puzzle is not solvable if:
   * a) it is received in an explicitly illegal state
   * b) it will inevitably arrive at an illegal state
   *
   * @param puzzle Puzzle to be examined
   * @return true if it is in a legal state and all empty spaces have possible values
   */
  public boolean isSolvablePuzzle(Puzzle puzzle) {
    if (!isLegalState(puzzle)) return false;

    for (int i = 0; i < puzzle.gridSize; i++) {
      for (int j = 0; j < puzzle.gridSize; j++) {
        if (!puzzle.cells[i][j].hasValue() && puzzle.cells[i][j].possibleValues.size() == 0) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Check for correct dimensions
   * Acceptable dimensions are 4x4, 9x9, 16x16, 25x25, and 36x36
   *
   * @param puzzle Puzzle to be validated
   * @return true if dimensions are correct
   */
  public boolean isFormattedCorrectly(Puzzle puzzle) {
    return puzzle.gridSize == 4 || puzzle.gridSize == 9 || puzzle.gridSize == 16 || puzzle.gridSize == 25 || puzzle.gridSize == 36;
  }

  /**
   * Check whether the symbol in each set cell value is in the provided list of symbols
   *
   * @param puzzle Puzzle to be validated
   * @return true if all set cell values are in the provided list of symbols
   */
  public boolean hasCorrectSymbols(Puzzle puzzle) {
    for (int i = 0; i < puzzle.gridSize; i++) {
      for (int j = 0; j < puzzle.gridSize; j++) {
        if (puzzle.cells[i][j].hasValue() && !puzzle.symbols.contains(puzzle.cells[i][j].getValue())) {
          return false;
        }
      }
    }
    return true;
  }

  @SuppressWarnings("Duplicates")
  public boolean isLegalState(Puzzle puzzle) {
    List<Character> rowList = new ArrayList<>();
    List<Character> colList = new ArrayList<>();
    for (int i = 0; i < puzzle.gridSize; i++) {
      for (int j = 0; j < puzzle.gridSize; j++) {
        //Check for repeated instances in a row
        if (puzzle.cells[i][j].hasValue()) {
          char val = puzzle.cells[i][j].getValue();
          if (rowList.contains(val)) {
            return false;
          }
          rowList.add(val);
        }
        //Check for repeated instances in a column
        if (puzzle.cells[j][i].hasValue()) {
          char val = puzzle.cells[j][i].getValue();
          if (colList.contains(val)) {
            return false;
          }
          colList.add(val);
        }
      }
      rowList.clear();
      colList.clear();
    }

    List<Character> blockList = new ArrayList<>();
    //Iterate to each block
    for (int i = 0; i < puzzle.gridSize; i += puzzle.blockSize) {
      for (int j = 0; j < puzzle.gridSize; j += puzzle.blockSize) {
        //Iterate cells within each block
        for (int rowIndex = i; rowIndex < i + puzzle.blockSize && rowIndex < puzzle.gridSize; rowIndex++) {
          for (int colIndex = j; colIndex < j + puzzle.blockSize && rowIndex < puzzle.gridSize; colIndex++) {
            if (puzzle.cells[rowIndex][colIndex].hasValue()) {
              char val = puzzle.cells[rowIndex][colIndex].getValue();
              if (blockList.contains(val)) {
                return false;
              }
              blockList.add(val);
            }
          }
        }
        blockList.clear();
      }
    }
    return true;
  }
}

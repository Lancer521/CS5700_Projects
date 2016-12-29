package Solver;

import Puzzle.Puzzle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ty on 12/9/2016.
 * Search for a Locked Candidate within a row/col.
 * If found, remove from possible values of the other cells in that block excluding the Locked Candidate's row or column
 */
public class LockedCandidateBlockAlgorithm extends SudokuAlgorithm {

  @SuppressWarnings("Duplicates")
  @Override
  public boolean applyMethod(Puzzle puzzle, int currRow, int currCol) {
    boolean didSomething = false;

    /**
     * Look at same row, blocks above and/or below
     * * If !list.empty(), remove from other cells in other rows in block
     * Look at same col, blocks to the left and/or right
     * * If !list.empty(), remove from other cells in other columns in block
     */

    //Find the top left square of the block to which this cell belongs
    int blockRow = puzzle.calculateBlockIndex(currRow);
    int blockCol = puzzle.calculateBlockIndex(currCol);

    List<Character> list = new ArrayList<>(puzzle.cells[currRow][currCol].possibleValues);

    for (int row = 0; row < puzzle.gridSize; row += puzzle.blockSize) {
      if (row == blockRow) continue;
      for (int i = row; i < row + puzzle.blockSize; i++) {
        for (Iterator<Character> iterator = list.iterator(); iterator.hasNext(); ) {
          char c = iterator.next();
          if (puzzle.cells[i][currCol].possibleValues.contains(c)) {
            iterator.remove();
          }
        }
      }
    }
    if (!list.isEmpty()) {
      if(removeFromRowsInBlock(puzzle, list, currRow, blockRow, blockCol)) didSomething = true;
      if(list.size() == 1) return didSomething;
    }

    list = new ArrayList<>(puzzle.cells[currRow][currCol].possibleValues);

    for (int col = 0; col < puzzle.gridSize; col += puzzle.blockSize) {
      if (col == blockCol) continue;
      for (int i = col; i < col + puzzle.blockSize; i++) {
        for (Iterator<Character> iterator = list.iterator(); iterator.hasNext(); ) {
          char c = iterator.next();
          if (puzzle.cells[currRow][i].possibleValues.contains(c)) {
            iterator.remove();
          }
        }
      }
    }
    if (!list.isEmpty()) {
      if(removeFromColsInBlock(puzzle, list, currRow, blockRow, blockCol)) didSomething = true;
    }

    return didSomething;
  }

  @SuppressWarnings("Duplicates")
  private boolean removeFromColsInBlock(Puzzle puzzle, List<Character> list, int currCol, int blockRow, int blockCol) {

    boolean didSomething = false;

    for(int col = blockCol; col < blockCol + puzzle.blockSize; col++){
      if(col == currCol) continue;
      for(int row = blockRow; row < blockRow + puzzle.blockSize; row++){
        for(Character c : list){
          if(puzzle.cells[row][col].possibleValues.remove(c)){
            didSomething = true;
          }
        }
      }
    }

    return didSomething;
  }

  @SuppressWarnings("Duplicates")
  private boolean removeFromRowsInBlock(Puzzle puzzle, List<Character> list, int currRow, int blockRow, int blockCol){

    boolean didSomething = false;

    for(int row = blockRow; row < blockRow + puzzle.blockSize; row++){
      if(row == currRow) continue;
      for(int col = blockCol; col < blockCol + puzzle.blockSize; col++){
        for(Character c : list){
          if(puzzle.cells[row][col].possibleValues.remove(c)){
            didSomething = true;
          }
        }
      }
    }
    return didSomething;
  }
}

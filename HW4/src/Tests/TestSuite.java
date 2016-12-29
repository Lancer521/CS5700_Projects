package Tests;


import Puzzle.Cell;
import Puzzle.Puzzle;
import Puzzle.PuzzleIO;
import Solver.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ty on 11/17/2016.
 * *
 */
public class TestSuite {

  @Test
  public void testPuzzleIOGetPuzzle4x4() {
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-4x4-0001.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertTrue(puzzle.cells[2][1].getValue() == '1');
  }

  @Test
  public void testPuzzleIOOutputPuzzle() {
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-4x4-0001.txt");
    Assert.assertTrue(puzzle != null);
    PuzzleIO.outputPuzzle(puzzle, "src/Tests/test.txt");
    Puzzle newPuzzle = PuzzleIO.getPuzzle("src/Tests/test.txt");
    Assert.assertTrue(newPuzzle != null);
    Assert.assertTrue(puzzle.equals(newPuzzle));
  }

  @Test
  public void testPuzzleEquals(){
    Puzzle p1 = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-4x4-0001.txt");
    Puzzle p2 = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-4x4-0001.txt");
    Puzzle p3 = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-4x4-0002.txt");

    Assert.assertTrue(p1 != null && p2 != null && p3 != null);
    Assert.assertTrue(p1.equals(p2));
    Assert.assertFalse(p1.equals(p3));
  }

  @Test
  public void testPuzzleIsFull(){
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-4x4-0001-SOLVED.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertTrue(puzzle.isFull());

    puzzle.cells[3][3].setValue('-');
    Assert.assertFalse(puzzle.isFull());

    puzzle.cells[3][3].setValue('2');
    Assert.assertTrue(puzzle.isFull());
  }

  @Test
  public void testPuzzleCalculateBlockIndex(){
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-4x4-0001.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertTrue(puzzle.calculateBlockIndex(0) == 0);
    Assert.assertTrue(puzzle.calculateBlockIndex(1) == 0);
    Assert.assertTrue(puzzle.calculateBlockIndex(2) == 2);
    Assert.assertTrue(puzzle.calculateBlockIndex(3) == 2);

    puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-9x9-0001.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertTrue(puzzle.calculateBlockIndex(0) == 0);
    Assert.assertTrue(puzzle.calculateBlockIndex(1) == 0);
    Assert.assertTrue(puzzle.calculateBlockIndex(2) == 0);
    Assert.assertTrue(puzzle.calculateBlockIndex(3) == 3);
    Assert.assertTrue(puzzle.calculateBlockIndex(4) == 3);
    Assert.assertTrue(puzzle.calculateBlockIndex(5) == 3);
    Assert.assertTrue(puzzle.calculateBlockIndex(6) == 6);
    Assert.assertTrue(puzzle.calculateBlockIndex(7) == 6);
    Assert.assertTrue(puzzle.calculateBlockIndex(8) == 6);

    Assert.assertFalse(puzzle.calculateBlockIndex(1) == 1);
    Assert.assertFalse(puzzle.calculateBlockIndex(5) == 6);
  }

  @Test
  public void testCellHasValue(){
    Cell cell = new Cell(new ArrayList<>());
    cell.setValue('-');
    Assert.assertFalse(cell.hasValue());

    cell.setValue('3');
    Assert.assertTrue(cell.hasValue());

    cell.setValue('-');
    Assert.assertFalse(cell.hasValue());
  }

  @Test
  public void testSolverIsSolved(){
    Solver solver = new Solver();
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-4x4-0001-SOLVED.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertTrue(solver.isSolved(puzzle));

    puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-4x4-0001.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertFalse(solver.isSolved(puzzle));

    puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-9x9-0202-SOLVED.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertTrue(solver.isSolved(puzzle));
  }

  @Test
  public void testSolverIsValidPuzzle() {
    Solver solver = new Solver();
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/myPuzzle-BadSymbols-4x4.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertFalse(solver.isValidPuzzle(puzzle));
    puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-9x9-0001.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertTrue(solver.isValidPuzzle(puzzle));
    puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-4x4-0001.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertTrue(solver.isValidPuzzle(puzzle));
    puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/myPuzzle-9x9.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertTrue(solver.isValidPuzzle(puzzle));
  }

  @Test
  public void testSolverIsSolvablePuzzle() {
    Solver solver = new Solver();
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/myPuzzle-IllegalState-9x9.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertFalse(solver.isSolvablePuzzle(puzzle));

    puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-9x9-0001.txt");
    Assert.assertTrue(puzzle != null);
    AddNotesAlgorithm notes = new AddNotesAlgorithm();
    SinglesAlgorithm singles = new SinglesAlgorithm();
    notes.apply(puzzle);
    singles.apply(puzzle);
    singles.apply(puzzle);
    singles.apply(puzzle);
    Assert.assertTrue(new Solver().isSolvablePuzzle(puzzle));
  }

  @Test
  public void testSolverIsFormattedCorrectly(){
    Solver solver = new Solver();
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/myPuzzle-IncorrectFormat-9x9.txt");
    Assert.assertTrue(puzzle != null);
    Assert.assertFalse(solver.isFormattedCorrectly(puzzle));
  }

  @Test
  public void testSolverHasCorrectSymbols() {
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/myPuzzle-BadSymbols-4x4.txt");
    Assert.assertTrue(puzzle != null);
    Solver solver = new Solver();
    Assert.assertFalse(solver.hasCorrectSymbols(puzzle));
  }

  @Test
  public void testSolverIsLegalState() {
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/myPuzzle-IllegalState-9x9.txt");
    Assert.assertTrue(puzzle != null);
    Solver solver = new Solver();
    Assert.assertFalse(solver.isLegalState(puzzle));
  }

  @Test
  public void testAddNotesAlgorithmMyPuzzle() {
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/myPuzzle-9x9.txt");
    Assert.assertTrue(puzzle != null);
    AddNotesAlgorithm algorithm = new AddNotesAlgorithm();
    algorithm.apply(puzzle);
    List<Character> list = new ArrayList<>();
    list.add('2');
    list.add('7');
    list.add('8');
    list.add('9');
    Assert.assertTrue(puzzle.cells[5][0].possibleValues.equals(list));
    list.clear();
    list.add('2');
    list.add('6');
    list.add('7');
    Assert.assertFalse(puzzle.cells[5][0].possibleValues.equals(list));
    Assert.assertTrue(puzzle.cells[5][7].possibleValues.equals(list));
    list.clear();
    list.add('2');
    list.add('5');
    list.add('7');
    list.add('8');
    Assert.assertFalse(puzzle.cells[5][7].possibleValues.equals(list));
    Assert.assertTrue(puzzle.cells[8][0].possibleValues.equals(list));
  }

  @Test
  public void testAddNotesAlgorithm9x9_0001() {
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-9x9-0001.txt");
    Assert.assertTrue(puzzle != null);

    AddNotesAlgorithm notes = new AddNotesAlgorithm();
    notes.apply(puzzle);
    List<Character> list = new ArrayList<>();
    list.add('2');
    Assert.assertTrue(puzzle.cells[0][2].possibleValues.equals(list));
    Assert.assertTrue(puzzle.cells[8][5].possibleValues.equals(list));
    list.clear();
    list.add('5');
    Assert.assertTrue(puzzle.cells[0][7].possibleValues.equals(list));
    Assert.assertTrue(puzzle.cells[8][1].possibleValues.equals(list));
    list.clear();
    list.add('1');
    Assert.assertTrue(puzzle.cells[1][6].possibleValues.equals(list));
    Assert.assertTrue(puzzle.cells[2][1].possibleValues.equals(list));
    list.clear();
    list.add('8');
    Assert.assertTrue(puzzle.cells[4][3].possibleValues.equals(list));
    list.clear();
    list.add('9');
    Assert.assertTrue(puzzle.cells[4][4].possibleValues.equals(list));
    Assert.assertTrue(puzzle.cells[5][0].possibleValues.equals(list));
    list.clear();
    list.add('7');
    Assert.assertTrue(puzzle.cells[6][4].possibleValues.equals(list));
    list.clear();
    list.add('4');
    Assert.assertTrue(puzzle.cells[6][6].possibleValues.equals(list));
  }

  @Test
  public void testSinglesAlgorithm4x4() {
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-4x4-0001.txt");
    Assert.assertTrue(puzzle != null);
    AddNotesAlgorithm notes = new AddNotesAlgorithm();
    SinglesAlgorithm singles = new SinglesAlgorithm();
    notes.apply(puzzle);
    singles.apply(puzzle);
    Assert.assertTrue(puzzle.cells[0][1].getValue() == '4');
    Assert.assertTrue(puzzle.cells[1][2].getValue() == '2');
    Assert.assertTrue(puzzle.cells[2][3].getValue() == '2');
    Assert.assertTrue(puzzle.cells[3][0].getValue() == '4');
  }

  @Test
  public void testSinglesAlgorithm9x9() {
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/Puzzle-9x9-0001.txt");
    Assert.assertTrue(puzzle != null);
    AddNotesAlgorithm notes = new AddNotesAlgorithm();
    SinglesAlgorithm singles = new SinglesAlgorithm();
    notes.apply(puzzle);
    singles.apply(puzzle);
    Assert.assertTrue(puzzle.cells[1][6].getValue() == '1');
    Assert.assertTrue(puzzle.cells[2][1].getValue() == '1');
    Assert.assertTrue(puzzle.cells[0][2].getValue() == '2');
    Assert.assertTrue(puzzle.cells[8][5].getValue() == '2');
    Assert.assertTrue(puzzle.cells[6][6].getValue() == '4');
    Assert.assertTrue(puzzle.cells[0][7].getValue() == '5');
    Assert.assertTrue(puzzle.cells[8][1].getValue() == '5');
    Assert.assertTrue(puzzle.cells[6][4].getValue() == '7');
    Assert.assertTrue(puzzle.cells[4][3].getValue() == '8');
    Assert.assertTrue(puzzle.cells[4][4].getValue() == '9');
    Assert.assertTrue(puzzle.cells[5][0].getValue() == '9');
  }

  @Test
  public void testHiddenSinglesAlgorithm9x9() {
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/myPuzzle-HiddenSingle9x9.txt");
    Assert.assertTrue(puzzle != null);
    AddNotesAlgorithm notes = new AddNotesAlgorithm();
    HiddenSinglesAlgorithm hidden = new HiddenSinglesAlgorithm();
    notes.apply(puzzle);
    hidden.apply(puzzle);
    Assert.assertTrue(puzzle.cells[2][3].getValue() == '6');
  }

  @Test
  public void testLockedCandidateRowColAlgorithm9x9() {
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/myPuzzle-LockedCandidateRowCol9x9.txt");
    Assert.assertTrue(puzzle != null);
    AddNotesAlgorithm notes = new AddNotesAlgorithm();
    LockedCandidateRowColAlgorithm locked = new LockedCandidateRowColAlgorithm();
    notes.apply(puzzle);

    List<Character> list = new ArrayList<>();
    list.add('3');
    list.add('4');
    list.add('5');
    list.add('9');
    Assert.assertTrue(puzzle.cells[0][0].possibleValues.equals(list));
    list.clear();
    list.add('4');
    list.add('5');
    list.add('9');
    Assert.assertTrue(puzzle.cells[1][0].possibleValues.equals(list));
    list.clear();
    list.add('4');
    list.add('5');
    list.add('6');
    list.add('8');
    list.add('9');
    Assert.assertTrue(puzzle.cells[4][0].possibleValues.equals(list));
    list.clear();
    list.add('5');
    list.add('8');
    list.add('9');
    Assert.assertTrue(puzzle.cells[5][0].possibleValues.equals(list));
    list.clear();
    list.add('3');
    list.add('5');
    list.add('7');
    Assert.assertTrue(puzzle.cells[7][0].possibleValues.equals(list));
    list.clear();
    list.add('4');
    list.add('5');
    list.add('6');
    list.add('8');
    Assert.assertTrue(puzzle.cells[8][0].possibleValues.equals(list));

    locked.apply(puzzle);

    list.clear();
    list.add('4');
    list.add('5');
    list.add('9');
    Assert.assertTrue(puzzle.cells[0][0].possibleValues.equals(list));
    list.clear();
    list.add('4');
    list.add('5');
    list.add('9');
    Assert.assertTrue(puzzle.cells[1][0].possibleValues.equals(list));
    list.clear();
    list.add('4');
    list.add('6');
    list.add('8');
    list.add('9');
    Assert.assertTrue(puzzle.cells[4][0].possibleValues.equals(list));
    list.clear();
    list.add('8');
    list.add('9');
    Assert.assertTrue(puzzle.cells[5][0].possibleValues.equals(list));
    list.clear();
    list.add('3');
    list.add('7');
    Assert.assertTrue(puzzle.cells[7][0].possibleValues.equals(list));
    list.clear();
    list.add('4');
    list.add('6');
    list.add('8');
    Assert.assertTrue(puzzle.cells[8][0].possibleValues.equals(list));
  }

  @Test
  public void testNakedPairsAlgorithm() {
    Puzzle puzzle = PuzzleIO.getPuzzle("src/SamplePuzzles/myPuzzle-NakedPairs9x9.txt");
    Assert.assertTrue(puzzle != null);
    new AddNotesAlgorithm().apply(puzzle);

    List<Character> list = new ArrayList<>();
    list.add('3');
    list.add('7');
    list.add('8');
    Assert.assertTrue(puzzle.cells[5][1].possibleValues.equals(list));
    list.clear();
    list.add('1');
    list.add('5');
    list.add('8');
    Assert.assertTrue(puzzle.cells[4][6].possibleValues.equals(list));
    Assert.assertTrue(puzzle.cells[4][7].possibleValues.equals(list));

    NakedPairsAlgorithm naked = new NakedPairsAlgorithm();
    naked.apply(puzzle);

    list.clear();
    list.add('3');
    list.add('7');
    Assert.assertTrue(puzzle.cells[5][1].possibleValues.equals(list));
    list.clear();
    list.add('1');
    list.add('5');
    Assert.assertTrue(puzzle.cells[4][6].possibleValues.equals(list));
    Assert.assertTrue(puzzle.cells[4][7].possibleValues.equals(list));
  }
}
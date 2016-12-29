import Output.Log;
import Puzzle.Puzzle;
import Puzzle.PuzzleIO;
import Solver.Solver;

import java.util.Scanner;

/**
 * Created by Ty on 11/15/2016 at 4:24 PM.
 * *
 */
public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the filepath to the puzzle you wish to solve, or press enter to solve the 30 tests provided: ");
    String inputFile = scanner.nextLine();

    if (!inputFile.isEmpty()) {
      System.out.print("Enter the filepath to the file you wish to save the solution to, or press enter to save to \"src\\Output\\defaultOutput.txt\"");
      String outputFile = scanner.nextLine();
      if (outputFile.isEmpty()) {
        outputFile = "HW4\\src\\Output\\defaultOutput.txt";
      }
      Puzzle puzzle = PuzzleIO.getPuzzle(inputFile);
      new Solver().solve(puzzle);
      PuzzleIO.outputPuzzle(puzzle, outputFile);
    } else {
      Puzzle p1 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-4x4-0001.txt");
      Puzzle p2 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-4x4-0002.txt");
      Puzzle p3 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-4x4-0901.txt");
      Puzzle p4 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-4x4-0902.txt");
      Puzzle p5 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-4x4-0903.txt");
      Puzzle p6 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-4x4-0904.txt");
      Puzzle p7 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0001.txt");
      Puzzle p8 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0002.txt");
      Puzzle p9 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0101.txt");
      Puzzle p10 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0201.txt");
      Puzzle p11 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0202.txt");
      Puzzle p12 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0203.txt");
      Puzzle p13 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0301.txt");
      Puzzle p14 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0302.txt");
      Puzzle p15 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0304.txt");
      Puzzle p16 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0305.txt");
      Puzzle p17 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0306.txt");
      Puzzle p18 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0307.txt");
      Puzzle p19 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0308.txt");
      Puzzle p20 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-9x9-0401.txt");
      Puzzle p21 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-16x16-0001.txt");
      Puzzle p22 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-16x16-0101.txt");
      Puzzle p23 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-16x16-0201.txt");
      Puzzle p24 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-16x16-0301.txt");
      Puzzle p25 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-16x16-0401.txt");
      Puzzle p26 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-16x16-0901.txt");
      Puzzle p27 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-25x25-0201.txt");
      Puzzle p28 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-25x25-0301.txt");
      Puzzle p29 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-25x25-0401.txt");
      Puzzle p30 = PuzzleIO.getPuzzle("HW4/src/SamplePuzzles/Puzzle-25x25-0901.txt");

      Solver solver = new Solver();
      solver.solve(p1);
      PuzzleIO.outputPuzzle(p1, "HW4/src/Output/puzzle1.txt");

      solver.solve(p2);
      PuzzleIO.outputPuzzle(p2, "HW4/src/Output/puzzle2.txt");

      solver.solve(p3);
      PuzzleIO.outputPuzzle(p3, "HW4/src/Output/puzzle3.txt");

      solver.solve(p4);
      PuzzleIO.outputPuzzle(p4, "HW4/src/Output/puzzle4.txt");

      solver.solve(p5);
      PuzzleIO.outputPuzzle(p5, "HW4/src/Output/puzzle5.txt");

      solver.solve(p6);
      PuzzleIO.outputPuzzle(p6, "HW4/src/Output/puzzle6.txt");

      solver.solve(p7);
      PuzzleIO.outputPuzzle(p7, "HW4/src/Output/puzzle7.txt");

      solver.solve(p8);
      PuzzleIO.outputPuzzle(p8, "HW4/src/Output/puzzle8.txt");

      solver.solve(p9);
      PuzzleIO.outputPuzzle(p9, "HW4/src/Output/puzzle9.txt");

      solver.solve(p10);
      PuzzleIO.outputPuzzle(p10, "HW4/src/Output/puzzle10.txt");

      solver.solve(p11);
      PuzzleIO.outputPuzzle(p11, "HW4/src/Output/puzzle11.txt");

      solver.solve(p12);
      PuzzleIO.outputPuzzle(p12, "HW4/src/Output/puzzle12.txt");

      solver.solve(p13);
      PuzzleIO.outputPuzzle(p13, "HW4/src/Output/puzzle13.txt");

      solver.solve(p14);
      PuzzleIO.outputPuzzle(p14, "HW4/src/Output/puzzle14.txt");

      solver.solve(p15);
      PuzzleIO.outputPuzzle(p15, "HW4/src/Output/puzzle15.txt");

      solver.solve(p16);
      PuzzleIO.outputPuzzle(p16, "HW4/src/Output/puzzle16.txt");

      solver.solve(p17);
      PuzzleIO.outputPuzzle(p17, "HW4/src/Output/puzzle17.txt");

      solver.solve(p18);
      PuzzleIO.outputPuzzle(p18, "HW4/src/Output/puzzle18.txt");

      solver.solve(p19);
      PuzzleIO.outputPuzzle(p19, "HW4/src/Output/puzzle19.txt");

      solver.solve(p20);
      PuzzleIO.outputPuzzle(p20, "HW4/src/Output/puzzle20.txt");

      solver.solve(p21);
      PuzzleIO.outputPuzzle(p21, "HW4/src/Output/puzzle21.txt");

      solver.solve(p22);
      PuzzleIO.outputPuzzle(p22, "HW4/src/Output/puzzle22.txt");

      solver.solve(p23);
      PuzzleIO.outputPuzzle(p23, "HW4/src/Output/puzzle23.txt");

      solver.solve(p24);
      PuzzleIO.outputPuzzle(p24, "HW4/src/Output/puzzle24.txt");

      solver.solve(p25);
      PuzzleIO.outputPuzzle(p25, "HW4/src/Output/puzzle25.txt");

      solver.solve(p26);
      PuzzleIO.outputPuzzle(p26, "HW4/src/Output/puzzle26.txt");

      solver.solve(p27);
      PuzzleIO.outputPuzzle(p27, "HW4/src/Output/puzzle27.txt");

      solver.solve(p28);
      PuzzleIO.outputPuzzle(p28, "HW4/src/Output/puzzle28.txt");

      solver.solve(p29);
      PuzzleIO.outputPuzzle(p29, "HW4/src/Output/puzzle29.txt");

      solver.solve(p30);
      PuzzleIO.outputPuzzle(p30, "HW4/src/Output/puzzle30.txt");

      System.out.println();
      System.out.println("OVERALL REPORT:");
      System.out.println("1 : " + p1.getPuzzleResultString());
      System.out.println("2 : " + p2.getPuzzleResultString());
      System.out.println("3 : " + p3.getPuzzleResultString());
      System.out.println("4 : " + p4.getPuzzleResultString());
      System.out.println("5 : " + p5.getPuzzleResultString());
      System.out.println("6 : " + p6.getPuzzleResultString());
      System.out.println("7 : " + p7.getPuzzleResultString());
      System.out.println("8 : " + p8.getPuzzleResultString());
      System.out.println("9 : " + p9.getPuzzleResultString());
      System.out.println("10: " + p10.getPuzzleResultString());
      System.out.println("11: " + p11.getPuzzleResultString());
      System.out.println("12: " + p12.getPuzzleResultString());
      System.out.println("13: " + p13.getPuzzleResultString());
      System.out.println("14: " + p14.getPuzzleResultString());
      System.out.println("15: " + p15.getPuzzleResultString());
      System.out.println("16: " + p16.getPuzzleResultString());
      System.out.println("17: " + p17.getPuzzleResultString());
      System.out.println("18: " + p18.getPuzzleResultString());
      System.out.println("19: " + p19.getPuzzleResultString());
      System.out.println("20: " + p20.getPuzzleResultString());
      System.out.println("21: " + p21.getPuzzleResultString());
      System.out.println("22: " + p22.getPuzzleResultString());
      System.out.println("23: " + p23.getPuzzleResultString());
      System.out.println("24: " + p24.getPuzzleResultString());
      System.out.println("25: " + p25.getPuzzleResultString());
      System.out.println("26: " + p26.getPuzzleResultString());
      System.out.println("27: " + p27.getPuzzleResultString());
      System.out.println("28: " + p28.getPuzzleResultString());
      System.out.println("29: " + p29.getPuzzleResultString());
      System.out.println("30: " + p30.getPuzzleResultString());

    }
    Log.getInstance().writeToFile();
  }
}

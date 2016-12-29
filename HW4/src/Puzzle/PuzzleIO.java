package Puzzle;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ty on 11/15/2016 at 4:26 PM.
 * Controls the reading-in/parsing and saving of puzzles
 */
public class PuzzleIO {

  public static Puzzle getPuzzle(String inputFile) {

    Puzzle puzzle = null;
    String line;
    String rawPuzzle = "";
    try {
      BufferedReader br = new BufferedReader(new FileReader(inputFile));

      line = br.readLine();
      if (line == null) return null;
      if (StringUtils.isAlpha(line) || line.isEmpty()){
        line = br.readLine(); // Discard if the first line is text (ie puzzle result from output file)
      }
      int size = Integer.parseInt(line);

      line = br.readLine();
      if (line == null) return null;
      List<Character> list = new ArrayList<>();
      for (char c : line.toCharArray()) {
        if (c != ' ') {
          list.add(c);
        }
      }

      puzzle = new Puzzle(size, list);

      while ((line = br.readLine()) != null) {
        String[] array = line.split(" ");
        for (String str : array) {
          rawPuzzle += str;
        }
      }
      br.close();

      char[] rawPuzzleCharacters = rawPuzzle.toCharArray();
      int charIndex = 0;

      for (int i = 0; i < puzzle.gridSize; i++) {
        for (int j = 0; j < puzzle.gridSize; j++) {
          puzzle.cells[i][j].setValue(rawPuzzleCharacters[charIndex++]);
        }
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return puzzle;
  }

  public static void outputPuzzle(Puzzle solvedPuzzle, String outputFile) {

    String parsedPuzzle = solvedPuzzle.getPuzzleResultString() + "\n";

    parsedPuzzle += solvedPuzzle.gridSize + "\n";
    for (char c : solvedPuzzle.symbols) {
      parsedPuzzle += c + " ";
    }
    parsedPuzzle += "\n";
    for (int i = 0; i < solvedPuzzle.gridSize; i++) {
      for (int j = 0; j < solvedPuzzle.gridSize; j++) {
        parsedPuzzle += solvedPuzzle.cells[i][j].getValue() + " ";
      }
      parsedPuzzle += "\n";
    }
    try {
      PrintWriter writer = new PrintWriter(outputFile);
      writer.print(parsedPuzzle);
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}

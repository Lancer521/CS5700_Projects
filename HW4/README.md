# CS5700_HW4 / CS5700_HW5



Justification of Design Decisions:

- I found that every single algorithm was iterating over the entire puzzle (among more complex/specific iterating), so
I decided to use the template pattern to let a template method iterate over each cell in the puzzle.  This meant writing
each algorithm so that it was only concerned with one cell at a time, allowing the template method to make sure each
algorithm was applied across the board (rather than each algorithm having to do it on its own).

- It was difficult to decide where to put the puzzle validation logic.  I put it in the solver because that's where it's used, and a solver should know its
criteria for being able to solve a puzzle.  But one could argue that a puzzle should be the one to know how to tell its own validity, and that the solver
shouldn't need to understand how to look through and interpret the puzzle.  It could also be argued that there should be an algorithm/set of algorithms to
deal with this, but they would serve such a different purpose from the other SudokuAlgorithms that it would feel awkward for them all to extend the same
superclass.  In the end, I decided to let the Solver contain this logic, because perhaps in the future the Solver's abilities could be changed/improved, and
it can adjust its own criteria for valid puzzles rather than making the puzzles change.

- I had a similar difficulty with deciding where the logic for updating the puzzle should be.  It could be argued that the puzzle should know how to update
itself, but I decided to let the SudokuAlgorithm know this.  In my mind, it makes sense for the Puzzle to be able to inspect and report on its state, but the
 SudokuAlgorithms are the ones that know how to manipulate and change the data in the Puzzle in meaningful ways.



** If you run the program such that it solves all 30 of the test puzzles, it will save them to src/Output/[puzzlename].txt **



Things learned in my revision:
- Using and traversing Strings instead of Lists for the 'possibleValues' property of each cell actually MORE THAN DOUBLED the time required to solve all 30
puzzles.  Unfortunately it took a few hours of refactoring to realize that.  Fortunately though, IntelliJ quickly reverted these uncommitted changes for me
(yay version control).

- Writing test cases actually helped me identify a few methods that weren't properly named, ie. the 'getBlockIndex' method implies that it is a simple
getter to a property, however the method actually contains logic that figures out the top-left index of that block containing the given cell.  It is much
more appropriate to call it 'puzzle.calculateBlockIndex'

- Testing can be a bit complicated because there are methods within certain classes that are marked as private or package protected which I may still want to
 test.  However, I don't feel like it's good practice to just make everything public for the sake of testing.  I'm not really sure what the work around is,
 so I tried to be wise in identifying which methods would be ok to make public (few) for testing, and which methods would be ok without unit tests because
 they are indirectly tested through other methods/algorithms.




 PERFORMANCE OBSERVATIONS/ENHANCEMENTS:

 Originally (in HW4) I was able to get these results:

Number of method calls:
 LockedCandidateRowColAlgorithm: 4067
 NakedPairsAlgorithm: 4067
 HiddenSinglesAlgorithm: 4067
 SinglesAlgorithm: 4067

Total time spent in method (nanoseconds):
 LockedCandidateRowColAlgorithm: 339764783
 NakedPairsAlgorithm: 139391844
 HiddenSinglesAlgorithm: 600585038
 SinglesAlgorithm: 27922230

Total time spent in all algorithms: 1107663895

 Initially, I tried to change the data types used in storing possible values of a cell (from List to String), but this actually doubled my runtime (as
 discussed above).

 I decided that rather than running through every method in a loop, I should give the more common/useful algorithms priority.  To do this, I decided to let
 each algorithm return a boolean indicating whether it had made any changes. If it had, I would go back to the most common algorithm; if it hadn't, I would
 continue on to the next available algorithm.  This meant drastically reducing the number of unnecessary algorithm calls by only calling them when needed.

 After this refactor, I got these results:

Number of method calls:
 LockedCandidateRowColAlgorithm: 9
 NakedPairsAlgorithm: 16
 HiddenSinglesAlgorithm: 71
 SinglesAlgorithm: 243

Total time spent in method (nanoseconds):
 LockedCandidateRowColAlgorithm: 4461052
 NakedPairsAlgorithm: 5990531
 HiddenSinglesAlgorithm: 25462837
 SinglesAlgorithm: 22975075

Total time spent in all algorithms: 58889495

This refactoring resulted in a 95% reduction of the time elapsed before refactoring. (58,889,495 / 1,107,663,895 = .0532)
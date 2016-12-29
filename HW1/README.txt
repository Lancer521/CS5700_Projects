Tylen Smith
CS5700
HW1

A few important things to note:

- JSON "__type" field should change to "Type", with simply "Adult" or "Child" as options
- XML "ArrayOfPersons" should change to "ListOfPerson"
- XML cannot have namespace information or xsi:type attribute
    - Instead use <Type>Adult</Type> or <Type>Child</Type>
- XML parser is very unorthodox; I had many issues with deserializing a list of subclasses, so
  I instead opted for converting it to JSON, which I could easily parse. NOT perfect by any means,
  but as Dr. Clyde said, parsing isn't the purpose of the assignment
- Input is taken through the console.  Existing input file paths are commented at the top of Main
- I have included a folder with .vpp and .png files of my diagrams in the same directory as this README

Any other questions feel free to message me via email or canvas (email is best).

Thanks!
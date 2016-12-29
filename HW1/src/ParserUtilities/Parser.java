package ParserUtilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import Person.Person;

/**
 * Created by Ty on 9/5/2016.
 */
public abstract class Parser {

    public abstract List<Person> parseInput(String fileName);

    protected String getInput(String fileName) {
        String line;
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}

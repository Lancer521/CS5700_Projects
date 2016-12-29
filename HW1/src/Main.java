import MatchBehavior.*;
import ParserUtilities.JSONParser;
import ParserUtilities.XMLParser;
import Person.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ty on 9/3/2016.
 *
 * All of the file paths for easy access:
 *
 * C:\\Users\\Ty\\IdeaProjects\\CS5700_HW1\\src\\TestData\\PersonTestSet_11.xml
 * C:\\Users\\Ty\\IdeaProjects\\CS5700_HW1\\src\\TestData\\PersonTestSet_12.xml
 * C:\\Users\\Ty\\IdeaProjects\\CS5700_HW1\\src\\TestData\\PersonTestSet_13.xml
 * C:\\Users\\Ty\\IdeaProjects\\CS5700_HW1\\src\\TestData\\PersonTestSet_14.xml
 * C:\\Users\\Ty\\IdeaProjects\\CS5700_HW1\\src\\TestData\\PersonTestSet_15.xml
 * C:\\Users\\Ty\\IdeaProjects\\CS5700_HW1\\src\\TestData\\PersonTestSet_01.json
 * C:\\Users\\Ty\\IdeaProjects\\CS5700_HW1\\src\\TestData\\PersonTestSet_02.json
 * C:\\Users\\Ty\\IdeaProjects\\CS5700_HW1\\src\\TestData\\PersonTestSet_03.json
 * C:\\Users\\Ty\\IdeaProjects\\CS5700_HW1\\src\\TestData\\PersonTestSet_04.json
 * C:\\Users\\Ty\\IdeaProjects\\CS5700_HW1\\src\\TestData\\PersonTestSet_05.json
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter input type: ");
        String inputType = scanner.nextLine();
        System.out.print("Enter the file path for the data file: ");
        String inFilePath = scanner.nextLine();
        System.out.print("Enter the file path for the output file: ");
        String outFilePath = scanner.nextLine();

        List<Person> personList;

        if (inputType.toLowerCase().equals("xml")) {
            XMLParser xmlParser = new XMLParser();
            personList = xmlParser.parseInput(inFilePath);
        } else if (inputType.equals("json")) {
            JSONParser jsonParser = new JSONParser();
            personList = jsonParser.parseInput(inFilePath);
        } else {
            System.out.print("Invalid input type");
            return;
        }

        if (personList == null) {
            System.out.print("Failed to parse input. Check your input file and try again");
            return;
        }
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(outFilePath));
            writer.println("Matching pairs:");
            System.out.println("Matching pairs:");
            for (int matcherIndex = 0; matcherIndex < personList.size(); matcherIndex++) {
                Person matcher = personList.get(matcherIndex);
                for (int matcheeIndex = matcherIndex + 1; matcheeIndex < personList.size(); matcheeIndex++) {
                    Person matchee = personList.get(matcheeIndex);
                    PersonMatcher personMatcher = findBestMatcher(matcher, matchee);
                    matcher.setPersonMatcher(personMatcher);
                    if (matcher.performMatch(matchee)) {
                        System.out.println("(" + matcher.ObjectId + "," + matchee.ObjectId + ")");
                        writer.println("(" + matcher.ObjectId + "," + matchee.ObjectId + ")");
                    }

                }
            }
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.print("Done");
    }

    private static PersonMatcher findBestMatcher(Person a, Person b) {

        if (a instanceof Child && b instanceof Child) {
            Child matcher = (Child) a;
            Child matchee = (Child) b;
            boolean motherNameMatchable = areMatchable(matcher.MotherFirstName, matchee.MotherFirstName) &&
                    areMatchable(matcher.MotherLastName, matchee.MotherLastName);
            if (motherNameMatchable && areMatchable(matcher.BirthCounty, matchee.BirthCounty)) {
                if (areMatchable(matcher.NewBornScreeningNumber, matchee.NewBornScreeningNumber)) {
                    return new VerboseChildMatcher();
                } else return new NameDateGenderPlaceMotherChildMatcher();
            } else return new NameDateGenderMatcher();
        } else if (a instanceof Adult && b instanceof Adult) {
            Adult matcher = (Adult) a;
            Adult matchee = (Adult) b;
            if (areMatchable(matcher.SocialSecurityNumber, matchee.SocialSecurityNumber) && areMatchable(matcher.Phone1, matchee.Phone1)) {
                return new VerboseAdultMatcher();
            } else return new NameDateGenderMatcher();
        } else {
            if (areMatchable(a.StateFileNumber, b.StateFileNumber)) {
                return new NameDateGenderStateFilePersonMatcher();
            }
            return new NameDateGenderMatcher();
        }
    }

    private static boolean areMatchable(String matcherfield, String matcheeField) {
        return !(matcherfield == null || matcheeField == null);
    }
}

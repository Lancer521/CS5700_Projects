package MatchBehavior;

import Person.Person;
import Person.Child;

/**
 * Created by Ty on 9/3/2016.
 */
public abstract class PersonMatcher {

    public abstract boolean match(Person matcher, Person matchee);

    protected boolean isNull(String a, String b) {
        return a == null || b == null;
    }

    protected boolean isGarbageString(String string) {
        // How to detect a garbage string?
        return false;
    }

    protected boolean isBasicInfoMatch(Person matcher, Person matchee) {
        return isBirthDateMatch(matcher, matchee) &&
                isFullNameMatch(matcher, matchee) &&
                isGenderMatch(matcher, matchee);
    }

    protected boolean isBirthDateMatch(Person matcher, Person matchee) {
        return matcher.BirthDay == matchee.BirthDay &&
                matcher.BirthMonth == matchee.BirthMonth &&
                matcher.BirthYear == matchee.BirthYear;
    }

    // Middle name isn't essential to know; if it's not known, assume it's true.
    protected boolean isFullNameMatch(Person matcher, Person matchee) {
        return !isNull(matcher.FirstName, matchee.FirstName) && matcher.FirstName.equals(matchee.FirstName) &&
                (isNull(matcher.MiddleName, matchee.MiddleName) || matcher.MiddleName.equals(matchee.MiddleName)) &&
                !isNull(matcher.LastName, matchee.LastName) && matcher.LastName.equals(matchee.LastName);
    }

    // If all of the other criteria match, chances are its the same person.
    // Therefore, gender isn't essential to know; if it's not known, assume it's true.
    protected boolean isGenderMatch(Person matcher, Person matchee) {
        return isNull(matcher.Gender, matchee.Gender) || matcher.Gender.equals(matchee.Gender);
    }

    protected boolean isMotherFullNameMatch(Child matcher, Child matchee) {
        return matcher.MotherFirstName.equals(matchee.MotherFirstName) &&
                (isNull(matcher.MotherMiddleName, matchee.MotherMiddleName) || matcher.MotherMiddleName.equals(matchee.MotherMiddleName)) &&
                matcher.MotherLastName.equals(matchee.MotherLastName);
    }

    protected boolean isChildBirthInfoMatch(Child matcher, Child matchee) {
        return matcher.NewBornScreeningNumber.equals(matchee.NewBornScreeningNumber) &&
                matcher.IsPartOfMultipleBirth == matchee.IsPartOfMultipleBirth &&
                matcher.BirthOrder == matchee.BirthOrder &&
                matcher.BirthCounty.equals(matchee.BirthCounty);
    }
}

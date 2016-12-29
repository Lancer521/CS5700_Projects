package MatchBehavior;

import Person.Person;

/**
 * Created by Ty on 9/12/2016.
 */
public class NameDateGenderStateFilePersonMatcher extends PersonMatcher {
    @Override
    public boolean match(Person matcher, Person matchee) {
        return isBasicInfoMatch(matcher, matchee) &&
                matcher.StateFileNumber.equals(matchee.StateFileNumber);
    }
}

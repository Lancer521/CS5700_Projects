package MatchBehavior;

import Person.Person;
import Person.Child;

/**
 * Created by Ty on 9/14/2016.
 */
public class NameDateGenderPlaceMotherChildMatcher extends PersonMatcher {
    @Override
    public boolean match(Person matcher, Person matchee) {
        return isBasicInfoMatch(matcher, matchee) &&
                isMotherFullNameMatch((Child) matcher, (Child) matchee) &&
                ((Child) matcher).BirthCounty.equals(((Child) matchee).BirthCounty);
    }
}

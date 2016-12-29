package MatchBehavior;

import Person.*;

/**
 * Created by Ty on 9/14/2016.
 * Assumes that all fields have been populated, and that the Persons are instances of Child
 */
public class VerboseChildMatcher extends PersonMatcher {
    @Override
    public boolean match(Person matcher, Person matchee) {
        if(matcher instanceof Adult || matchee instanceof Adult){
            return false;
        }
        return isBasicInfoMatch(matcher, matchee) &&
                isMotherFullNameMatch((Child) matcher, (Child) matchee) &&
                isChildBirthInfoMatch((Child) matcher, (Child) matchee);
    }
}

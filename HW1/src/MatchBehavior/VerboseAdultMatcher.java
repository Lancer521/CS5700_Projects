package MatchBehavior;

import Person.*;

/**
 * Created by Ty on 9/16/2016.
 */
public class VerboseAdultMatcher extends PersonMatcher{
    @Override
    public boolean match(Person matcher, Person matchee) {
        if(matcher instanceof Child || matchee instanceof Child){
            return false;
        }
        return isBasicInfoMatch(matcher, matchee) &&
                matcher.SocialSecurityNumber.equals(matchee.SocialSecurityNumber) &&
                ((Adult) matcher).Phone1.equals(((Adult) matchee).Phone1);
    }
}

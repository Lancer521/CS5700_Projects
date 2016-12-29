package Tests;

import MatchBehavior.NameDateGenderMatcher;
import MatchBehavior.NameDateGenderStateFilePersonMatcher;
import MatchBehavior.VerboseAdultMatcher;
import MatchBehavior.VerboseChildMatcher;
import org.junit.Assert;
import org.junit.Test;
import Person.*;

/**
 * Created by Ty on 9/16/2016.
 */
public class TestSuite {

    @Test
    public void testVerboseAdultMatcher(){
        Person adult = new Adult();
        Person adult2 = new Adult();
        adult.FirstName = adult2.FirstName = "Tom";
        adult.MiddleName = adult2.MiddleName = "Lee";
        adult.LastName = adult2.LastName = "Jones";
        adult.BirthDay = adult2.BirthDay = 5;
        adult.BirthMonth = adult2.BirthMonth = 5;
        adult.BirthYear = 2001;
        adult2.BirthYear = 2002;
        adult.setPersonMatcher(new VerboseAdultMatcher());
        Assert.assertFalse(adult.performMatch(adult2));
    }

    @Test
    public void testVerboseAdultMatcherWrongType(){
        Person adult = new Adult();
        Person child = new Child();
        adult.FirstName = child.FirstName = "Tom";
        adult.MiddleName = child.MiddleName = "Lee";
        adult.LastName = child.LastName = "Jones";
        adult.BirthDay = child.BirthDay = 5;
        adult.BirthMonth = child.BirthMonth = 5;
        adult.BirthYear = child.BirthYear = 2001;
        adult.setPersonMatcher(new VerboseAdultMatcher());
        Assert.assertFalse(adult.performMatch(child));
    }

    @Test
    public void testVerboseChildMatcher(){
        Child child = new Child();
        Child child2 = new Child();
        child.FirstName = child2.FirstName = "Tom";
        child.MiddleName = child2.MiddleName = "Lee";
        child.LastName = child2.LastName = "Jones";
        child.BirthDay = child2.BirthDay = 5;
        child.BirthMonth = child2.BirthMonth = 5;
        child.BirthYear = 2001;
        child2.BirthYear = 2002;
        child.NewBornScreeningNumber = child2.NewBornScreeningNumber = "23423";
        child.BirthCounty = child2.BirthCounty = "Cache";
        child.setPersonMatcher(new VerboseChildMatcher());
        Assert.assertFalse(child.performMatch(child2));
    }

    @Test
    public void testVerboseChildMatcherWrongType(){
        Person child = new Child();
        Person adult = new Adult();
        child.FirstName = adult.FirstName = "Tom";
        child.MiddleName = adult.MiddleName = "Lee";
        child.LastName = adult.LastName = "Jones";
        child.BirthDay = adult.BirthDay = 5;
        child.BirthMonth = adult.BirthMonth = 5;
        child.BirthYear = adult.BirthYear = 2001;
        child.setPersonMatcher(new VerboseChildMatcher());
        Assert.assertFalse(child.performMatch(adult));
    }

    @Test
    public void testNameDateGenderMatcherWrongGender(){
        Person adult = new Adult();
        Person child = new Child();
        adult.FirstName = child.FirstName = "Tom";
        adult.MiddleName = child.MiddleName = "Lee";
        adult.LastName = child.LastName = "Jones";
        adult.BirthDay = child.BirthDay = 5;
        adult.BirthMonth = child.BirthMonth = 5;
        adult.BirthYear = child.BirthYear = 2001;
        adult.Gender = "male";
        child.Gender = "female";
        adult.setPersonMatcher(new NameDateGenderMatcher());
        Assert.assertFalse(adult.performMatch(child));
    }

    @Test
    public void testNameDateGenderMatcherUnknownGender(){
        Person adult = new Adult();
        Person child = new Child();
        adult.FirstName = child.FirstName = "Tom";
        adult.MiddleName = child.MiddleName = "Lee";
        adult.LastName = child.LastName = "Jones";
        adult.BirthDay = child.BirthDay = 5;
        adult.BirthMonth = child.BirthMonth = 5;
        adult.BirthYear = child.BirthYear = 2001;
        adult.Gender = "male";
        child.Gender = null;
        adult.setPersonMatcher(new NameDateGenderMatcher());
        Assert.assertTrue(adult.performMatch(child));
    }

    @Test
    public void testNameDateGenderStateFilePersonMatcher_FileMismatch(){
        Person adult = new Adult();
        Person child = new Child();
        adult.FirstName = child.FirstName = "Tom";
        adult.MiddleName = child.MiddleName = "Lee";
        adult.LastName = child.LastName = "Jones";
        adult.BirthDay = child.BirthDay = 5;
        adult.BirthMonth = child.BirthMonth = 5;
        adult.BirthYear = child.BirthYear = 2001;
        adult.Gender = "male";
        child.Gender = null;
        adult.StateFileNumber = "12345";
        child.StateFileNumber = "12344";
        adult.setPersonMatcher(new NameDateGenderStateFilePersonMatcher());
        Assert.assertFalse(adult.performMatch(child));
    }

    @Test
    public void testNameDateGenderStateFilePersonMatcher_FileUnknown(){
        Person adult = new Adult();
        Person child = new Child();
        adult.FirstName = child.FirstName = "Tom";
        adult.MiddleName = child.MiddleName = "Lee";
        adult.LastName = child.LastName = "Jones";
        adult.BirthDay = child.BirthDay = 5;
        adult.BirthMonth = child.BirthMonth = 5;
        adult.BirthYear = child.BirthYear = 2001;
        adult.Gender = "male";
        child.Gender = null;
        adult.StateFileNumber = "12345";
        child.StateFileNumber = null;
        adult.setPersonMatcher(new NameDateGenderStateFilePersonMatcher());
        Assert.assertFalse(adult.performMatch(child));
    }
}

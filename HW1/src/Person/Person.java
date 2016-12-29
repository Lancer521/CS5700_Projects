package Person;

import MatchBehavior.NameDateGenderMatcher;
import MatchBehavior.PersonMatcher;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by Ty on 9/3/2016.
 */
@XmlSeeAlso({Adult.class, Child.class})
public class Person {

    private PersonMatcher matcher;

    public String Type;
    public int ObjectId;
    public String StateFileNumber;
    public String SocialSecurityNumber;

    public String FirstName;
    public String MiddleName;
    public String LastName;
    public int BirthYear;
    public int BirthMonth;
    public int BirthDay;
    public String Gender;

    public Person(){
        matcher = new NameDateGenderMatcher();
    }

    public boolean performMatch(Person matchee){
        return matcher.match(this, matchee);
    }

    public void setPersonMatcher(PersonMatcher pm){
        matcher = pm;
    }
}

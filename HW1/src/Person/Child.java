package Person;

/**
 * Created by Ty on 9/13/2016.
 */
public class Child extends Person{

    public Child(){
        this.Type = "Child";
    }

    //Additional Properties for Children
    public String NewBornScreeningNumber;
    public boolean IsPartOfMultipleBirth;
    public int BirthOrder;
    public String BirthCounty;
    public String MotherFirstName;
    public String MotherMiddleName;
    public String MotherLastName;
}

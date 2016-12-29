package ParserUtilities;

import com.google.gson.*;

import Person.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ty on 9/12/2016.
 */
public class JSONParser extends Parser{

    @Override
    public List<Person> parseInput(String fileName) {
        List<Person> personList = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(getInput(fileName)).getAsJsonArray();
        Gson gson = new Gson();
        for(JsonElement element : array) {
            Person person = gson.fromJson(element, Person.class);
            if(person.Type.equals("Adult")){
                personList.add(gson.fromJson(element, Adult.class));
            } else {
                personList.add(gson.fromJson(element, Child.class));
            }
        }
        return personList;
    }
}

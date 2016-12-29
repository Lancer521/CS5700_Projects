package ParserUtilities;

import Person.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

/**
 * Created by Ty on 9/12/2016.
 */
public class XMLParser extends Parser {

    @Override
    public List<Person> parseInput(String fileName) {

        Gson gson = new Gson();
        List<Person> personList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JSONObject obj = XML.toJSONObject(getInput(fileName));
        try {
            JsonNode root = mapper.readTree(obj.toString());
            JsonNode ListOfPersonNode = root.get("ListOfPerson");
            JsonNode personListNode = ListOfPersonNode.get("Person");
            JSONArray array = new JSONArray(personListNode.toString());
            for(Object object : array) {
                Person person = gson.fromJson(object.toString(), Person.class);
                if(person.Type.equals("Adult")){
                    personList.add(gson.fromJson(object.toString(), Adult.class));
                } else {
                    personList.add(gson.fromJson(object.toString(), Child.class));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return personList;
    }
}

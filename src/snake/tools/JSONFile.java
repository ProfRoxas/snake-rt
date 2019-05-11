package snake.tools;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map; 
import java.util.Iterator;

public class JSONFile
{
    private String name;

    public JSONFile(String name) {
        this.name = name;
    }

    public void writeToJSON(ArrayList<PersonScore> highScores) {
        JSONObject obj = new JSONObject();
        for(int i = 0; i < highScores.size(); i++) {
            obj.put(highScores.get(i).getName(), highScores.get(i).getScore());
        }

        try (FileWriter file = new FileWriter(name)) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PersonScore> readFromJSON() {
        JSONParser parser = new JSONParser();
        ArrayList<PersonScore> highScores = new ArrayList<>();

        try {

            Object obj = parser.parse(new FileReader(name));

            JSONObject jsonObject = (JSONObject) obj;

            Iterator<Map.Entry<String, String>> iterator = jsonObject.entrySet().iterator(); 
            while(iterator.hasNext()) 
            { 
                Map.Entry<String, String> entry = iterator.next(); 
                highScores.add(new PersonScore(entry.getKey(), Integer.parseInt(entry.getValue())));
            }
            

        } catch (FileNotFoundException e) {
            highScores.add(new PersonScore("Peter", 1000));
            highScores.add(new PersonScore("ASDF", 999));
            highScores.add(new PersonScore("IDDQD", 500));
            highScores.add(new PersonScore("420420", 400));
            highScores.add(new PersonScore("Boss", 300));
            highScores.add(new PersonScore("lethal_bacon", 200));
            highScores.add(new PersonScore("MyLittlePwny", 100));
            highScores.add(new PersonScore("ragingpuma", 50));
            highScores.add(new PersonScore("username_copied", 20));
            highScores.add(new PersonScore("dontkillme", 10));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return highScores;
    }
}
package snake.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFile
{
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    public void write(ArrayList<PersonScore> highScores) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(name, false));
            for(int i = 0; i < highScores.size(); i++) {
                String name = highScores.get(i).getName();
                name = name.replace(":","ˇ");
                pw.println(name + ":" +highScores.get(i).getScore());
            }
            pw.close();
        }
        catch(FileNotFoundException e) {

        }
    }

    public ArrayList<PersonScore> read() {
        ArrayList<PersonScore> highScores = new ArrayList<>();
        File file = new File(name);
        try {
            Scanner read = new Scanner(file);
            while(read.hasNextLine()) {
                String line = read.nextLine();
                String person[] = line.split(":");
                person[0] = person[0].replace("ˇ",":");
                highScores.add(new PersonScore(person[0], Integer.parseInt(person[1])));
            }
            read.close();
        }
        catch(FileNotFoundException e) {}
        return highScores;
    }
}
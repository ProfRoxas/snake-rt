package snake.tools;

import java.util.ArrayList;

public class HighScores
{
    private static ArrayList<PersonScore> highScores;

    static {
        highScores = new TextFile("./HighScore.hsc").read();
    }

    public static ArrayList<PersonScore> getHighScores() {
        return highScores;
    }

    public static void addNewHighScore(PersonScore player) {
        if(highScores.size() < 10) {
            highScores.add(player);
            save();
        }
        else {
        int i = 9;
            if(player.getScore() > highScores.get(i).getScore()) {
                while(i-1 >= 0 && player.getScore() > highScores.get(i-1).getScore()) {
                    i--;
                }
                highScores.add(i, player);
                highScores.remove(10);
                save();
            }
        }
    }

    public static void save() {
        new TextFile("./HighScore.hsc").write(highScores);
    }
}
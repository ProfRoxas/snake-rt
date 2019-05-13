package snake.tools;

import java.util.ArrayList;

public class HighScores
{
    private ArrayList<PersonScore> highScores;

    public HighScores() {
        highScores = new TextFile("./HighScore.hsc").read();
    }

    public ArrayList<PersonScore> getHighScores() {
        return highScores;
    }

    public void addNewHighScore(PersonScore player) {
        if(highScores.size() < 10) {
            highScores.add(player);
        }
        else {
        int i = 9;
            if(player.getScore() > highScores.get(i).getScore()) {
               while(i-1 >= 0 && player.getScore() > highScores.get(i-1).getScore()) {
                    i--;
               }
             highScores.add(i, player);
                highScores.remove(10);
            }
        }
    }

    public void save() {
        new TextFile("./HighScore.hsc").write(highScores);
    }
}
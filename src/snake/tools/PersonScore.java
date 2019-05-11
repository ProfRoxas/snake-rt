package snake.tools;

public class PersonScore
{
    private String name;
    private int score;

    public PersonScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
}
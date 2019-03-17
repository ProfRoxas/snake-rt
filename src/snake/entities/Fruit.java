package snake.entities;

public class Fruit implements Entity {
    private FruitTypes __type;
    
    public static enum FruitTypes {
        Basic
    }
    
    public Fruit(FruitTypes ft) {
        __type = ft;
    }

    @Override
    public void tick() {

    }

    @Override
    public String toString() {
        return __type.toString();
    }
}
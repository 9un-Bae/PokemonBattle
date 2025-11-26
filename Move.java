import java.util.Random;

/** Pokemon move with a name, elemental type, base power, and accuracy */
public class Move {
    private final String name;
    private final Type type;
    private final int power;
    private final int accuracy;

    /*
    Creates a new move with the given properties
      name - display name of the move
      type - elemental type of the move
      power - base power used in the damage formula
      accuracy - chance to hit from 0–100
    */

    public Move(String name, Type type, int power, int accuracy) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
    }

    /*
    Overloaded constructor that defaults accuracy to 100%
      name - display name of the move
      type - elemental type
      power - base power used in the damage formula
    */
  
    public Move(String name, Type type, int power) {
        this(name, type, power, 100); // constructor overloading + this()
    }

    public String getName() { return this.name; }
    public Type getType() { return this.type; }
    public int getPower() { return this.power; }
    public int getAccuracy() { return this.accuracy; }

    /*
    Determines whether the move hits based on its accuracy
      rng random number generator
      true if the move hits, false otherwise
    */
  
    public boolean hits(Random rng) {
        return rng.nextInt(100) < this.accuracy;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.type + ", " + this.power + " PWR, " + this.accuracy + "% ACC)";
    }
}

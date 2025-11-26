import java.util.ArrayList;
import java.util.List;

/* Pokemon with stats, current HP, and a set of moves */
public class Pokemon {
    private final String name;
    private final Type type;
    private int level;
    private final int maxHp;
    private int hp;
    private final int attack;
    private final int defense;
    private final List<Move> moves = new ArrayList<>();

    /*
    Creates a Pokemon with all of its core stats defined
      name - display name
      type - elemental type
      level - level of the Pokemon
      maxHp - maximum hit points
      attack - attack stat
      defense - defense stat
    */
    public Pokemon(String name, Type type, int level, int maxHp, int attack, int defense) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
    }

    /*
    Convenience constructor that defaults the level to 50
      name - display name
      type - elemental type
      maxHp - maximum hit points
      attack - attack stat
      defense - defense stat
    */
    public Pokemon(String name, Type type, int maxHp, int attack, int defense) {
        this(name, type, 50, maxHp, attack, defense);
    }

    public String getName() { return this.name; }
    public Type getType() { return this.type; }
    public int getLevel() { return this.level; }
    public int getHp() { return this.hp; }
    public int getMaxHp() { return this.maxHp; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public List<Move> getMoves() { return this.moves; }

    /*
    Updates the Pokemon's level
      level - new level
    */
    public void setLevel(int level) {
        this.level = level;
    }

    /*
    Adds an existing move object to this Pokemon's moveset
      move - move to learn
    */
    public void learn(Move move) {
        if (this.moves.size() < 4) {
            this.moves.add(move);
        }
    }

    /*
    Overloaded helper to create and learn a move from raw values
      name - move name
      type - move type
      power - move power
      accuracy - move accuracy
    */
    public void learn(String name, Type type, int power, int accuracy) {
        Move move = new Move(name, type, power, accuracy);
        this.learn(move);
    }

    /*
    Reduces HP by the given amount and returns the actual damage dealt
      amount - attempted damage
      damage - that was actually applied
    */
    public int takeDamage(int amount) {
        int dealt = Math.max(0, Math.min(amount, this.hp));
        this.hp -= dealt;
        return dealt;
    }

    /*
    True if the Pokemon has fainted (HP <= 0)
    */
    public boolean isFainted() {
        return this.hp <= 0;
    }

    /*
    Restores HP back to the maximum value
    */
    public void healFull() {
        this.hp = this.maxHp;
    }
}

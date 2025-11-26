import java.util.Random;

/* Runs a single battle between the player's Pokemon and an opponent Pokemon */
public class Battle {
    private final Pokemon player;
    private final Pokemon opponent;
    private final Random rng;

    // Static variable to track how many Battle instances have been created
    private static int totalBattles = 0;

    /*
    Creates a new Battle instance
      player - the player's Pokémon
      opponent - the opposing Pokémon
      seed - random seed for reproducible battles
    */
    public Battle(Pokemon player, Pokemon opponent, long seed) {
        this.player = player;
        this.opponent = opponent;
        this.rng = new Random(seed);
        Battle.totalBattles++;
    }

    /*
    Returns the number of Battle objects that have been created
    */
    public static int getTotalBattles() {
        return Battle.totalBattles;
    }

    /*
    Computes the type effectiveness multiplier for a given move type
      against a defending Pokemon's type
    */
    private double typeMultiplier(Type moveType, Type targetType) {
        if (moveType == Type.FIRE && targetType == Type.GRASS)   return 2.0;
        if (moveType == Type.WATER && targetType == Type.FIRE)   return 2.0;
        if (moveType == Type.GRASS && targetType == Type.WATER)  return 2.0;
        if (moveType == Type.ELECTRIC && targetType == Type.WATER) return 2.0;

        if (moveType == Type.FIRE && targetType == Type.WATER)   return 0.5;
        if (moveType == Type.WATER && targetType == Type.GRASS)  return 0.5;
        if (moveType == Type.GRASS && targetType == Type.FIRE)   return 0.5;
        if (moveType == Type.ELECTRIC && targetType == Type.GRASS) return 0.5;

        return 1.0;
    }

    /* Damage formula including STAB, type effectiveness and randomness */
    private int computeDamage(Pokemon attacker, Pokemon defender, Move move) {
        double stab = (attacker.getType() == move.getType()) ? 1.5 : 1.0;
        double typeEff = typeMultiplier(move.getType(), defender.getType());
        double rand = 0.85 + this.rng.nextDouble() * 0.15;
        double ratio = Math.max(0.25, (double) attacker.getAttack() / Math.max(1, defender.getDefense()));

        double raw = move.getPower() * ratio * stab * typeEff * rand;
        return Math.max(1, (int) Math.floor(raw));
    }

    /* Lets the opponent choose a random move from its moveset */
    private Move opponentChoice() {
        var moves = this.opponent.getMoves();
        return moves.get(this.rng.nextInt(moves.size()));
    }

    /*
    Executes one full turn of combat: the player moves first,
      then the opponent if still conscious
    playerMoveIndex - index of the player's chosen move
    */
    public String takeTurn(int playerMoveIndex) {
        StringBuilder log = new StringBuilder();

        Move playerMove = this.player.getMoves().get(playerMoveIndex);
        log.append(useMove(this.player, this.opponent, playerMove)).append("\n");
        log.append(statusLine()).append("\n");

        if (!this.opponent.isFainted()) {
            Move oppMove = opponentChoice();
            log.append(useMove(this.opponent, this.player, oppMove)).append("\n");
            log.append(statusLine()).append("\n");
        }

        return log.toString();
    }

    /* Executes a single move from one Pokemon to another and returns a summary */
    private String useMove(Pokemon attacker, Pokemon defender, Move move) {
        if (!move.hits(this.rng)) {
            return attacker.getName() + " used " + move.getName() + "… but it missed!";
        }

        int dmg = computeDamage(attacker, defender, move);
        int dealt = defender.takeDamage(dmg);

        double eff = typeMultiplier(move.getType(), defender.getType());

        StringBuilder sb = new StringBuilder();
        sb.append(attacker.getName()).append(" used ").append(move.getName())
          .append("! It dealt ").append(dealt).append(" damage.");

        if (eff > 1.0) sb.append(" It's super effective!");
        if (eff < 1.0) sb.append(" It's not very effective…");

        return sb.toString();
    }

    /*
    Builds a one-line HP status string for both Pokemon
      Returns status line with current HP values
    */
    public String statusLine() {
        return this.player.getName() + " " + this.player.getHp() + "/" + this.player.getMaxHp()
            + " | " + this.opponent.getName() + " " + this.opponent.getHp() + "/" + this.opponent.getMaxHp();
    }

    /* True if either Pokemon has fainted */
    public boolean isOver() {
        return this.player.isFainted() || this.opponent.isFainted();
    }

    /* Display winner of the battle */
    public String winner() {
        if (this.player.isFainted() && this.opponent.isFainted()) return "It's a tie!";
        if (this.opponent.isFainted()) return "You win!";
        if (this.player.isFainted()) return "You lose!";
        return "Battle continues…";
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
Start Pokemon battle simulation
User picks a Pokemon and then runs a single battle
*/
public class Game {

    // Prebuilt moves shared by all Pokemon (static variables)
    private static final Move THUNDERBOLT = new Move("Thunderbolt", Type.ELECTRIC, 90, 100);
    private static final Move QUICK_ATTACK = new Move("Quick Attack", Type.NORMAL, 40, 100);
    private static final Move FLAMETHROWER = new Move("Flamethrower", Type.FIRE, 90, 100);
    private static final Move TACKLE = new Move("Tackle", Type.NORMAL, 40, 100);
    private static final Move WATER_PULSE = new Move("Water Pulse", Type.WATER, 60, 100);
    private static final Move VINE_WHIP = new Move("Vine Whip", Type.GRASS, 45, 100);

    /* Builds the list of available Pokemon the player or opponent can use */
    private static List<Pokemon> roster() {
        List<Pokemon> mons = new ArrayList<>();

        Pokemon pikachu  = new Pokemon("Pikachu", Type.ELECTRIC, 50, 130, 95, 60);
        pikachu.learn(THUNDERBOLT);
        pikachu.learn(QUICK_ATTACK);
        pikachu.learn(TACKLE);
        mons.add(pikachu);

        Pokemon charizard = new Pokemon("Charizard", Type.FIRE, 50, 170, 110, 85);
        charizard.learn(FLAMETHROWER);
        charizard.learn(TACKLE);
        charizard.learn(QUICK_ATTACK);
        mons.add(charizard);

        Pokemon blastoise = new Pokemon("Blastoise", Type.WATER, 50, 180, 95, 110);
        blastoise.learn(WATER_PULSE);
        blastoise.learn(TACKLE);
        blastoise.learn(QUICK_ATTACK);
        mons.add(blastoise);

        Pokemon venusaur = new Pokemon("Venusaur", Type.GRASS, 50, 180, 100, 100);
        venusaur.learn(VINE_WHIP);
        venusaur.learn(TACKLE);
        venusaur.learn(QUICK_ATTACK);
        mons.add(venusaur);

        return mons;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Pokemon> mons = roster();
        Random rng = new Random();

        System.out.println("Welcome to Pocket Battle: Campus Showdown!");
        System.out.println("Choose your Pokémon:");
        for (int i = 0; i < mons.size(); i++) {
            Pokemon p = mons.get(i);
            System.out.printf("[%d] %s (Type: %s, HP %d, ATK %d, DEF %d)%n",
                    i + 1, p.getName(), p.getType(), p.getMaxHp(), p.getAttack(), p.getDefense());
        }
        System.out.print("> ");

        int pick = sc.nextInt();
        if (pick < 1) pick = 1;
        if (pick > mons.size()) pick = mons.size();
        Pokemon player = mons.get(pick - 1);

        // Pick a random opponent that's not the player
        Pokemon opponent;
        while (true) {
            int idx = rng.nextInt(mons.size());
            opponent = mons.get(idx);
            if (opponent != player) break;
        }

        // Ensure full health at start
        player.healFull();
        opponent.healFull();

        Battle battle = new Battle(player, opponent, 42L);

        System.out.println();
        System.out.println("You chose " + player.getName() + "!");
        System.out.println("Your opponent sends out " + opponent.getName() + "!");
        System.out.println(battle.statusLine());

        // Main battle loop
        while (!battle.isOver()) {
            System.out.println();
            System.out.println("Choose a move:");
            List<Move> moves = player.getMoves();
            for (int i = 0; i < moves.size(); i++) {
                System.out.printf("[%d] %s%n", i + 1, moves.get(i).toString());
            }
            System.out.print("> ");

            int moveChoice = sc.nextInt();
            if (moveChoice < 1) moveChoice = 1;
            if (moveChoice > moves.size()) moveChoice = moves.size();

            System.out.println();
            String log = battle.takeTurn(moveChoice - 1);
            System.out.print(log);
        }

        System.out.println(battle.winner());
        System.out.println("Total battles created: " + Battle.getTotalBattles());

        sc.close();
    }
}

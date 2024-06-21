import java.util.Random;

public class BattlePractice {
    private static final int PLAY_COUNT = 10;

    public static void main(String[] args) {

        Player player = new Player();

        while (player.getWinPoint() < PLAY_COUNT) {
            Opponent opponent = Opponent.getInstance(new Random().nextInt(3));
            opponent.showUp();
            if (player.winTheBattle()) {
                player.win(opponent);
            } else {
                player.lose(opponent);
            }
            player.ShowStatus();
        }
        player.endGame();
    }
}

class Player {
    private int winPoint = 0;
    private int losePoint = 0;

    public int getWinPoint() {
        return winPoint;
    }

    public void setWinPoint(int winPoint) {
        this.winPoint += winPoint;
    }

    public int getLosePoint() {
        return losePoint;
    }

    public void setLosePoint(int losePoint) {
        this.losePoint += losePoint;
    }

    public boolean winTheBattle() {
        boolean battleResult = new Random().nextInt(2) == 0;
        if (battleResult) {
            this.setWinPoint(1);
        } else {
            this.setLosePoint(1);
        }
        return battleResult;
    }

    public void win(Opponent opponent) {
        System.out.printf("Player win against %s!%n", opponent.getName());
    }

    public void lose(Opponent opponent) {
        System.out.printf("Player lose against %s!%n", opponent.getName());
    }

    public void ShowStatus() {
        System.out.printf("Player points are win [%s] and lose [%s]%n", this.getWinPoint(), this.getLosePoint());
    }

    public void endGame() {
        System.out.println("Game Over. Thank you for playing!");
    }
}

class Opponent {
    private String _name;

    protected void setName(String name) {
        this._name = name;
    }

    protected String getName() {
        return this._name;
    }

    public static Opponent getInstance(int opponentNumber) {
        return switch (opponentNumber) {
            case 0 -> new Bob();
            case 1 -> new Yamada();
            default -> new Mary();
        };
    }

    public void showUp() {
        System.out.printf("%s is showed up!%n", this.getName());
    }
}

class Bob extends Opponent {
    public Bob() {
        this.setName("Bob");
    }
}

class Yamada extends Opponent {
    public Yamada() {
        this.setName("Yamada");
    }
}

class Mary extends Opponent {
    public Mary() {
        this.setName("Mary");
    }
}
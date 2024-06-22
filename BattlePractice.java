import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BattlePractice {
    private static final int PLAY_COUNT = 10;

    public static void main(String[] args) {

        Player player = new Player();
        player.startGame();

        while (player.getWinPoint() < PLAY_COUNT) {
            Opponent opponent = Opponent.getInstance();
            opponent.showUp();
            if (player.winTheBattle()) {
                player.win(opponent);
            } else {
                player.lose(opponent);
            }
            player.showStatus();
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

    public void showStatus() {
        System.out.printf("Player points are here. win [%s], lose [%s]%n", this.getWinPoint(), this.getLosePoint());
    }

    public void startGame() {
        System.out.println("Game Start!");
    }

    public void endGame() {
        System.out.println("Game Over. Thank you for playing!");
    }
}

class Opponent {
    private String name;
    private static final List<Opponent> opponents = Collections.unmodifiableList(new ArrayList<Opponent>() {
        {
            add(new Bob());
            add(new Yamada());
            add(new Mary());
        }
    });

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Opponent getInstance() {
        return opponents.get(new Random().nextInt(opponents.size()));
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
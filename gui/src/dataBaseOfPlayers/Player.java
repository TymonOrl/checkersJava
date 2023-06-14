package dataBaseOfPlayers;

import javax.naming.Name;
import java.math.BigDecimal;

public class Player {
    String name;
    int gamesPlayed = 0;
    int wins = 0;
    int winRate = 0;

    public Player(String inName,
    int inGamesPlayed,
    int inWins){
        name = inName;
        gamesPlayed = inGamesPlayed;
        wins = inWins;
        coutWinRate();
    }


    public void coutWinRate(){
        float tmpFloat = (float)wins/gamesPlayed*100;
        winRate = (int)tmpFloat;
    }

    public void playerWon(){
        gamesPlayed++;
        wins++;
        coutWinRate();
    }

    public void playerLost(){
        gamesPlayed++;
        coutWinRate();
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getWinRate() {
        return winRate;
    }

    public Player(String inName){
        name = inName;
    }
}

package mancala.domain;

public class Player {

    public Player opponent;
    public boolean isTurn;
    public String name;


    public Player() {
        this.name = "Player1";
        this.isTurn = true;
        this.opponent = new Player(this);
    }

    public Player(Player opponent) {
        this.name = "Player2";
        this.isTurn = false;
        this.opponent = opponent;
    }

    public boolean isMyTurn() {
        return isTurn;
    }

    public void switchMyTurn() {
        if (isTurn == true) {
            isTurn = false;
            opponent.isTurn = true;
        } else {
            isTurn = true;
            opponent.isTurn = false;
        }
    }

    public String getName() {
        return this.name;
    }

    public Player getOpponent() {
        return this.opponent;
    }
}
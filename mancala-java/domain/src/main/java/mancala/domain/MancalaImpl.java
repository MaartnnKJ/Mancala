package mancala.domain;

public class MancalaImpl implements Mancala {

    Pit firstPit;

    public MancalaImpl() {
        this.firstPit = new Pit();
    }


    @Override
    public boolean isPlayersTurn(int player) {
        return true;
    }

    @Override
    public void playPit(int index) throws MancalaException {
        if (this.firstPit.getNextPit(index).getContents() > 0) {
            if ((index >= 0 && index < 6) || (index > 6 && index < 13)) {
                this.firstPit.getNextPit(index).play();
                this.isEndOfGame();
            }
        } else {
            throw new MancalaException("Incorrect Number");
        }
    }

    @Override
    public int getStonesForPit(int index) {
        if (index <= 13) {
            if (index == 0) {
                return this.firstPit.getContents();
            } else {
                return this.firstPit.getNextPit(index).getContents();
            }
        } else {
            return -1;
        }
    }

    @Override
    public boolean isEndOfGame() {
        return firstPit.gameOver2();
    }

    @Override
    public int getWinner() {
        int ScoreP1 = firstPit.scoreP1();
        int ScoreP2 = firstPit.scoreP2();
        if (ScoreP1 > ScoreP2) {
            return Mancala.PLAYER_ONE;
        } else if (ScoreP2 > ScoreP1) {
            return Mancala.PLAYER_TWO;
        } else {return Mancala.BOTH_PLAYERS;}
    }
}
package mancala.domain;

public class Pit {

    private int index;
    protected Pit nextPit;
    protected int contents;
    public Player owner;
    public String winner;

    public Pit() {
        this.owner = new Player();
        this.index = 0;
        this.contents = 4;
        this.nextPit = new Pit(4, ++index, this, this.owner);
    }

    public Pit(int contents, int index, Pit firstPit, Player player) {
        iOwnThis(index, player);
        this.index = index;
        if (index == 12 || index == 5) {
            this.contents = contents;
            this.nextPit = new Kalaha(0, ++index, firstPit, player);
        } else if (index < 12) {
            this.contents = contents;
            this.nextPit = new Pit(4, ++index, firstPit, player);
        } else {
            this.nextPit = firstPit;
        }
    }

    public void iOwnThis(int index, Player player) {
        if (index < 7) {
            this.owner = player;
        } else {
            this.owner = player.getOpponent();
        }
    }

    public Pit getNextPit() {
        return this.nextPit;
    }

    public Pit getNextPit(int pitAway) {
        if (pitAway == 1) {
            return this.nextPit;
        } else if (pitAway == 0) {
            return this;
        } else {
            return this.nextPit.getNextPit(pitAway - 1);
        }
    }

    public boolean allowedMove() {
        boolean allowedMove;
        if (this.owner.isTurn)
            allowedMove = true;
        else {
            allowedMove = false;
        }
        return allowedMove;
    }

    public int contentToPlay() {
        int contentToPlay = this.contents;
        return contentToPlay;
    }

    public int setContentToPlay(int newContentToPlay) {
        int contentToPlay = newContentToPlay;
        return contentToPlay;
    }

    public void addContent(int contentToPlay) {
        contentToPlay = contentToPlay - 1;
        this.contents = this.contents + 1;
        if (contentToPlay > 0) {
            this.nextPit.addContent(contentToPlay);
        } else if (contentToPlay == 0 ) {
            contentToSteal();
            this.owner.switchMyTurn();
        }
    }

    public void play() {
        if (this.owner.isMyTurn()) {
            int contentToPlay = this.contents;
            this.contents = 0;
            this.nextPit.addContent(contentToPlay);
        }
    }

    public void contentToSteal() {
        int contentToSteal = 0;
        if (getContents() == 1 && this.owner.isTurn && getOppositePit().getContents() != 0) {
            contentToSteal = getOppositePit().getContents() + 1;
            getOppositePit().setContents(0);
            setContents(0);
        }
        addStolenContent(contentToSteal);
    }

    public void addStolenContent(int contentToSteal) {
        this.nextPit.addStolenContent(contentToSteal);
    }

    public boolean isStealAllowed(int contentToPlay) {
        boolean isStealAllowed;
        if (contentToPlay == 1 && getContents() == 1) {
            isStealAllowed = true;
        } else {
            isStealAllowed = false;
        }
        return isStealAllowed;
    }

    public String winnerCheck2(Player owner) {
        int ScoreP1 = getKalaha().getContents();
        int ScoreP2 = getKalaha().getNextPit(7).getContents();

        for (int n = 1; n < 6; n++) {
            ScoreP2 += getKalaha().getNextPit(n).getContents();
            ScoreP1 += getKalaha().getNextPit(n + 7).getContents();
        }

        if (ScoreP1 > ScoreP2) {
            return this.winner = owner.getName();
        } else {
            return this.winner = owner.getOpponent().getName();
        }
    }

    public int scoreP1() {
        int ScoreP1 = getKalaha().getContents();
        for (int n = 1; n < 6; n++) {
            ScoreP1 += getKalaha().getNextPit(n+7).getContents();
        }
        return ScoreP1;
    }

    public int scoreP2() {
        int ScoreP2 = getKalaha().getNextPit(7).getContents();
        for (int n = 1; n < 6; n++) {
            ScoreP2 += getKalaha().getNextPit(n).getContents();
        }
        return ScoreP2;
    }

    public String winCheck(int ScoreP1, int ScoreP2, Player owner) {
        if (ScoreP1 > ScoreP2) {
            return this.winner = owner.getName();
        } else {
            return this.winner = owner.getOpponent().getName();
        }
    }

    public boolean gameOver2() {
        boolean gameOver2;
        int ContentsSideP2 = 0;
        int ContentsSideP1 = 0;

        for (int m = 1; m < 7; m++) {
            ContentsSideP2 += getKalaha().getNextPit(m).getContents();
            ContentsSideP1 += getKalaha().getNextPit(m + 7).getContents();
        }
        if (ContentsSideP2 == 0 || ContentsSideP1 == 0) {
            gameOver2 = true;
        } else gameOver2 = false;
        return gameOver2;
    }

    Kalaha getKalaha() {
        return nextPit.getKalaha();
    }

    public Pit getOppositePit() {
        return this.getNextPit(12 - 2 * (this.getIndex() % 7));
    }

    public int getContents() {
        return this.contents;
    }

    public void setContents(int newContents) {
        this.contents = newContents;
    }

    public int getIndex() {return this.index;}
}
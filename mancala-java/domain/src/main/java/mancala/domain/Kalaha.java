package mancala.domain;

public class Kalaha extends Pit {

    public Pit nextPit;
    public int contents;

    public Kalaha(int contents, int index, Pit firstPit, Player player) {
        super(contents, index, firstPit, player);
        if (index == 7) {
            this.contents = contents;
            this.nextPit = new Pit(4, ++index, firstPit, player);
        } else if (index == 14) {
            this.contents = contents;
            this.nextPit = firstPit;
        }
    }

    @Override
    public void addStolenContent(int contentToSteal) {
        if (this.owner.isTurn) {
            super.setContents(getContents() + contentToSteal);
        }
    }

    @Override
    Kalaha getKalaha() {
        return this;
    }
}
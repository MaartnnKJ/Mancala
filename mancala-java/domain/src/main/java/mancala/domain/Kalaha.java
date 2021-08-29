package mancala.domain;

public class Kalaha extends Pit {

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
    public void addContent(int contentToPlay) {
        if (this.owner.isMyTurn()) {
            contentToPlay = contentToPlay - 1;
            this.contents = this.contents + 1;
        }
        if (contentToPlay > 0) {
            nextPit.addContent(contentToPlay);
        }
    }

    @Override
    public void addStolenContent(int contentToSteal, Player player) {
        if (player == this.owner) {
            super.setContents(getContents() + contentToSteal);
        }
    }

    @Override
    Kalaha getKalaha() {
        return this;
    }
}

package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PitTest {

    @Test
    public void startingContent() {
        Pit newpit = new Pit();
        assertEquals(4, newpit.getContents());
    }

    @Test
    public void assertNextPit() {
        Pit newpit = new Pit();
        assertNotNull(newpit.getNextPit());
    }

    @Test
    public void assertXNextPits() {
        Pit newpit = new Pit();
        assertNotNull(newpit.getNextPit(4));
    }

    @Test
    public void kalahaStartingContent() {
        Pit newpit = new Pit();
        assertEquals(0, newpit.getNextPit(13).getContents());
    }

    @Test
    public void checkContentToPlay() {
        Pit newpit = new Pit();
        assertEquals(4, newpit.contentToPlay());
    }

    @Test
    public void checkContentAfterPlay() {
        Pit newpit = new Pit();
        newpit.play();
        assertEquals(0, newpit.getContents());
    }

    @Test
    public void checkNeighbourContentAfterPlay(){
        Pit newpit = new Pit();
        newpit.play();
        assertEquals(5, newpit.getNextPit().getContents());
    }

    @Test void checkKalahaContentAfterPlay(){
        Pit pit = new Pit();
        pit.getNextPit(5).play();
        assertEquals(1,pit.getNextPit(6).getContents());
    }
    @Test
    public void checkPlay() {
        Pit newpit = new Pit();
        //assertTrue(newpit.owner.isMyTurn());
        newpit.getNextPit(5).setContents(256);
        newpit.getNextPit(5).play();
//        newpit.getNextPit(7).play();
//        newpit.getNextPit(4).play();
        //newpit.getNextPit(4).play();
        assertEquals(19, newpit.getNextPit(6).getContents());
    }



    @Test
    public void checkOwner() {
        Pit newpit = new Pit();
        assertEquals(newpit.owner.getOpponent(), newpit.getNextPit(9).owner);
    }

    @Test
    public void checkGameOverP1() {
        Pit newpit = new Pit();
        newpit.getKalaha().setContents(8);
        newpit.getKalaha().getNextPit(1).setContents(0);
        newpit.getKalaha().getNextPit(2).setContents(0);
        newpit.getKalaha().getNextPit(3).setContents(0);
        newpit.getKalaha().getNextPit(4).setContents(0);
        newpit.getKalaha().getNextPit(5).setContents(0);
        newpit.getKalaha().getNextPit(6).setContents(0);
        newpit.getKalaha().getNextPit(7).setContents(8);
        assertEquals(true, newpit.getNextPit(5).gameOver2());
        assertEquals(8, newpit.getNextPit(13).getContents());
        assertEquals(4, newpit.getNextPit().getContents());
        assertEquals(8, newpit.getKalaha().getContents());
        assertEquals(8, newpit.getKalaha().getNextPit(7).getContents());
    }

    @Test
    public void checkGameOverP2() {
        Pit newpit = new Pit();
        newpit.getKalaha().setContents(8);
        newpit.getKalaha().getNextPit(1).setContents(1);
        newpit.getKalaha().getNextPit(2).setContents(0);
        newpit.getKalaha().getNextPit(3).setContents(0);
        newpit.getKalaha().getNextPit(4).setContents(0);
        newpit.getKalaha().getNextPit(5).setContents(0);
        newpit.getKalaha().getNextPit(6).setContents(0);
        newpit.getKalaha().getNextPit(7).setContents(8);
        newpit.getKalaha().getNextPit(8).setContents(0);
        newpit.getKalaha().getNextPit(9).setContents(0);
        newpit.getKalaha().getNextPit(10).setContents(0);
        newpit.getKalaha().getNextPit(11).setContents(0);
        newpit.getKalaha().getNextPit(12).setContents(0);
        newpit.getKalaha().getNextPit(13).setContents(0);
        assertEquals(true, newpit.getNextPit(5).gameOver2());
    }

    @Test
    public void checkWinner() {
        Pit newpit = new Pit();
        newpit.getKalaha().setContents(9);
        newpit.getKalaha().getNextPit(8).setContents(6);
        newpit.getKalaha().getNextPit(7).setContents(10);

        assertEquals("Player1", newpit.winnerCheck2(newpit.owner));
    }

    @Test
    public void checkWinner2() {
        Pit newpit = new Pit();
        newpit.getKalaha().setContents(9);
        newpit.getKalaha().getNextPit(8).setContents(6);
        newpit.getKalaha().getNextPit(7).setContents(10);
        assertEquals("Player1", newpit.winnerCheck2(newpit.owner));
    }

    @Test
    public void winCheck() {
        Pit newpit = new Pit();
        newpit.getKalaha().setContents(9);
        newpit.getKalaha().getNextPit(8).setContents(9);
        newpit.getKalaha().getNextPit(7).setContents(10);
        assertEquals("Player1", newpit.winCheck(newpit.scoreP1(), newpit.scoreP2(), newpit.owner));
    }

    @Test
    public void testSteal() {
        Pit newpit = new Pit();
        newpit.setContents(1);
        newpit.contentToSteal();
        assertEquals(5, newpit.getKalaha().getContents());
        assertEquals(0, newpit.getOppositePit().getContents());
    }

    @Test
    public void getIndexOppositePit() {
        Pit newpit = new Pit();
        assertEquals(12, newpit.getNextPit(2).getIndex());
    }

    @Test
    public void basicMathsAreNotWorking() {
        Pit newpit = new Pit();
        int a = newpit.getIndex();
        int d = 13 - 2 * (newpit.getIndex() % 7);
        assertEquals(5, a);
    }

    @Test
    public void testOppositePit() {
        Pit newpit = new Pit();
        Player player = new Player();
        newpit.setContents(1);
        newpit.contentToSteal();
        assertEquals(4, newpit.getOppositePit().getContents());
    }

    @Test
    public void checkOwnerAndPlayer() {
        Pit newpit = new Pit();
        newpit.owner.switchMyTurn();
        assertEquals(newpit.owner.getOpponent().getName(), newpit.owner.getOpponent().getName());
    }

    @Test
    public void checkAllowedMove() {
        Pit newpit = new Pit();
        newpit.owner.switchMyTurn();
        assertEquals(true, newpit.getKalaha().getNextPit().allowedMove());
    }

    @Test
    public void isStealAllowed() {
        Pit newpit = new Pit();
        newpit.getKalaha().setContents(8);
        newpit.getKalaha().getNextPit(8).setContents(1);
        newpit.getKalaha().getNextPit(9).setContents(0);
        newpit.getKalaha().getNextPit(10).setContents(0);
        newpit.getKalaha().getNextPit(11).setContents(0);
        newpit.getKalaha().getNextPit(12).setContents(0);
        newpit.getKalaha().getNextPit(13).setContents(0);
        newpit.getKalaha().getNextPit(7).setContents(8);
        newpit.getKalaha().getNextPit(8).addContent(newpit.contentToPlay());
        assertEquals(false, newpit.getKalaha().getNextPit(9).isStealAllowed(newpit.getKalaha().getNextPit(9).getContents()));
        assertEquals(0, newpit.getKalaha().getNextPit(9).contentToPlay());
        assertEquals(0, newpit.getKalaha().getNextPit(9).getContents());
        assertEquals(false, newpit.getKalaha().getNextPit(6).isStealAllowed(newpit.getKalaha().getNextPit(6).contentToPlay()));
        assertEquals(false, newpit.getNextPit(5).gameOver2());
        assertEquals("Player2", newpit.winnerCheck2(newpit.owner));
    }
}

package mancala.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void isTurn() {
        Player player = new Player();
        assertEquals(true, player.isTurn);
    }

    @Test
    public void isMyTurn() {
        Player player = new Player();
        assertEquals(true, player.isMyTurn());
    }

    @Test
    public void isOpponentTurn() {
        Player player = new Player();
        assertEquals(false, player.getOpponent().isMyTurn());
    }

    @Test
    public void switchMyTurn() {
        Player player = new Player();
        player.switchMyTurn();
        assertEquals(false, player.isMyTurn());
    }

    @Test
    public void getPlayerName() {
        Player player = new Player();
        assertEquals("Player1", player.getName());
    }

    @Test
    public void getOpponentName() {
        Player player = new Player();
        assertEquals("Player2", player.getOpponent().getName());
    }
}

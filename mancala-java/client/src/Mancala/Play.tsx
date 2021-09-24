import React from "react";
import type { GameState } from "../gameState";
import "./Play.css";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState }: PlayProps) {

    async function playPit(e: React.FormEvent, pitindex){
    e.preventDefault();
      try {
        const response = await fetch('mancala/api/play', {
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({pitIndex: pitindex})
        });
        if (response.ok) {
        const gameState = await response.json();
        setGameState(gameState);
        } else {
        console.error(response.statusText);
        }
      } catch (error) {
        console.error(error.toString());
      }
      }

      function disableAllButtons() {
      var isDisabled;
      {gameState.gameStatus.endOfGame ? isDisabled = true : isDisabled = ""};
      return isDisabled;
      }

    return (
        <div>
            <p>{gameState.players[0].name} vs {gameState.players[1].name}</p>
                <p>
                {gameState.players[1].name}
                <button disabled className="KalahaP2">{gameState.players[1].pits[6].nrOfStones}</button>
                <button disabled={disableAllButtons()} className="PitPlayer2" onClick={(e) => playPit(e, 12)}>{gameState.players[1].pits[5].nrOfStones}</button>
                <button disabled={disableAllButtons()} className="PitPlayer2" onClick={(e) => playPit(e, 11)}>{gameState.players[1].pits[4].nrOfStones}</button>
                <button disabled={disableAllButtons()} className="PitPlayer2" onClick={(e) => playPit(e, 10)}>{gameState.players[1].pits[3].nrOfStones}</button>
                <button disabled={disableAllButtons()} className="PitPlayer2" onClick={(e) => playPit(e, 9)}>{gameState.players[1].pits[2].nrOfStones}</button>
                <button disabled={disableAllButtons()} className="PitPlayer2" onClick={(e) => playPit(e, 8)}>{gameState.players[1].pits[1].nrOfStones}</button>
                <button disabled={disableAllButtons()} className="PitPlayer2" onClick={(e) => playPit(e, 7)}>{gameState.players[1].pits[0].nrOfStones}</button>
                </p>

                <p>
                {gameState.players[0].name}
                <button disabled={disableAllButtons()} className="PitPlayer1" onClick={(e) => playPit(e, 0)}>{gameState.players[0].pits[0].nrOfStones}</button>
                <button disabled={disableAllButtons()} className="PitPlayer1" onClick={(e) => playPit(e, 1)}>{gameState.players[0].pits[1].nrOfStones}</button>
                <button disabled={disableAllButtons()} className="PitPlayer1" onClick={(e) => playPit(e, 2)}>{gameState.players[0].pits[2].nrOfStones}</button>
                <button disabled={disableAllButtons()} className="PitPlayer1" onClick={(e) => playPit(e, 3)}>{gameState.players[0].pits[3].nrOfStones}</button>
                <button disabled={disableAllButtons()} className="PitPlayer1" onClick={(e) => playPit(e, 4)}>{gameState.players[0].pits[4].nrOfStones}</button>
                <button disabled={disableAllButtons()} className="PitPlayer1" onClick={(e) => playPit(e, 5)}>{gameState.players[0].pits[5].nrOfStones}</button>
                <button disabled className="KalahaP1">{gameState.players[0].pits[6].nrOfStones}</button>
                </p>

                {gameState.gameStatus.endOfGame ? <div><p><button disabled className="EndOfTheGame">Gameover, {gameState.gameStatus.winner} is the winner!!!!!</button></p></div> : null}
        </div>
    )
}
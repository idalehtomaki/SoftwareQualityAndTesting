import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40" 
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	
	
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();
		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();	
	}
	@Test // 4-0 case 
	public void testTennisGame_Player1Wins40() throws TennisGameException {
		
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		
		assertEquals("Should be 30 - love", "30 - love", game.getScore());
		
		game.player1Scored();
		assertEquals("Should be 40 - love", "40 - love", game.getScore());
		game.player1Scored();
	
		assertEquals("Player1 should be the winner", "player1 wins", game.getScore());
	}
		
	@Test // 4-1 case 
	public void testTennisGame_Player1wins41() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();
		assertEquals("Player1 should be the winner", "player1 wins", score);
		
	}
	@Test // 3 - 5 case
	public void testTennisGame_Player2wins53() throws TennisGameException {
		
		TennisGame game = new TennisGame();

		game.player2Scored();
		game.player2Scored(); // 0 - 30
		game.player1Scored(); // 15 - 30
		game.player1Scored(); // 30 - 30
		game.player2Scored(); // 30 - 40
		game.player2Scored(); // player2 wins
		
		
		assertEquals("Player2 should be the winner", "player2 wins", game.getScore());
	}
	
	
	@Test // Player 1 has advantage case
	public void testTennisGame_PlayersHaveAdvantage() throws TennisGameException { 
		
		TennisGame game = new TennisGame();
		
		game.player1Scored(); //15 - love
		game.player1Scored(); //30 - love
		
		game.player2Scored(); // 30 - 15
		game.player2Scored(); // 30 - 30
		
		game.player1Scored(); // 40 - 30
		game.player2Scored(); // 40 - 40
		game.player1Scored(); // player1 has advantage
		
		assertEquals("Player1 should have advantage", "player1 has advantage", game.getScore());
		
	}
	
	@Test // Player 2 has advantage case
	public void testTennisGame_Player2HasAdvantage() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player2Scored(); // love - 15
		game.player2Scored(); // love - 30
		
		game.player1Scored(); // 15 - 30
		game.player1Scored(); // 30 - 30
		
		game.player2Scored(); // 30 - 40
		game.player1Scored(); // 40 - 40
		game.player2Scored(); // player2 has advantage
		assertEquals("Player2 should have advantage", "player2 has advantage", game.getScore());
	}
	
	@Test // 15 - 30 case and 40 - 30
	public void testTennisGame_GameHasNotEnded() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored(); // 15 - love
		
		game.player2Scored(); // 15 - 15
		game.player2Scored(); // 15 - 30
		
		assertEquals("Initial score incorrect", "15 - 30", game.getScore());
		
		game.player1Scored(); // 30 - 30
		game.player1Scored(); // 40 - 30
		
		assertEquals("Initial score incorrect", "40 - 30", game.getScore());	
		
	}
	
}

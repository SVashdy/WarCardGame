import javax.swing.*;
import java.util.ArrayList;

/*
The following code represents the course of the card game "War".
There are 2 players in the game - each get a half of a 52 card standard deck.
Every turn each player deals a card and the player with the highest value card gets a point.
The card with the lowest value is "Deuce" and the highest is "Ace".
In case there is a draw after 26 card (A players full deck) the game is reset until there's a victor.
There is a limit to 500 moves.
 */


public class Main {

    private static final int maxMoves = 500; // Maximum number of moves per game
    private static DeckOfCards mainDeck = new DeckOfCards();
    private static DeckOfCards deckPlayer1 = new DeckOfCards("player1");
    private static DeckOfCards deckPlayer2 = new DeckOfCards("player2");
    private static int moveCounter = 0; // Initializing the move counter to count up to maxMoves

    public static void main(String[] args) {

        DeckOfCards mainDeck = new DeckOfCards();
        DeckOfCards deckPlayer1 = new DeckOfCards("player1");
        DeckOfCards deckPlayer2 = new DeckOfCards("player2");

        System.out.println(deckPlayer1.getClass().getName());
        System.out.println(deckPlayer2.getClass().getName());

        setGame(mainDeck, deckPlayer1, deckPlayer2);
        JOptionPane.showConfirmDialog(null, mainDeck.toString());
        JOptionPane.showConfirmDialog(null, deckPlayer1.toString());
        JOptionPane.showConfirmDialog(null, deckPlayer2.toString());

    }

    // This methods represents a regular turn
    private static void turn(DeckOfCards deckPlayer1, DeckOfCards deckPlayer2) {
        Card cardPlayer1 = deckPlayer1.dealCard();
        Card cardPlayer2 = deckPlayer2.dealCard();
        moveCounter++;
        // In case both players have same value card - we go to WAR
        if (cardPlayer1.compare(cardPlayer2) == 0) {
            if (deckPlayer1.getSize() >= 3 && deckPlayer2.getSize() >= 3) { // Making sure each player has at least 3 card in deck
                moveCounter += 2;
                war(deckPlayer1, deckPlayer2);
            }

        }
        // regular cases - one card is greater in value than the other
        if (cardPlayer1.compare(cardPlayer2) == 1) {
            deckPlayer1.addCard(cardPlayer2); // adding player2's card to player1's deck
        }
        if (cardPlayer1.compare(cardPlayer2) == -1) {
            deckPlayer2.addCard(cardPlayer1); // adding player1's card to players2's deck
        }
    }

    // In case the players had same value card - 3 card will be drawn
    private static void war(DeckOfCards deckPlayer1, DeckOfCards deckPlayer2) {
        ArrayList<Card> tempDeck = new ArrayList<Card>(3); // deck for the winners to take
        tempDeck.add(deckPlayer1.dealCard());
        tempDeck.add(deckPlayer2.dealCard());
        tempDeck.add(deckPlayer1.dealCard());
        tempDeck.add(deckPlayer2.dealCard());

        Card cardPlayer1 = deckPlayer1.dealCard();
        Card cardPlayer2 = deckPlayer1.dealCard();
        if (cardPlayer1.compare(cardPlayer2) == 1) {
            deckPlayer1.addCard(cardPlayer2);
        }
        if (cardPlayer1.compare(cardPlayer2) == -1) {
            deckPlayer2.addCard(cardPlayer1);
        }
        if (cardPlayer1.compare(cardPlayer2) == 0) {
            war(deckPlayer1, deckPlayer2);
        }

    }

    private static void addDeck(DeckOfCards playersDeck) {
        int i = 0;
        while (playersDeck.dealCard() != null) {

        }
    }


    // Initializing a new game - Two players and two decks
    private static void setGame(DeckOfCards mainDeck, DeckOfCards player1, DeckOfCards player2) {
        mainDeck.shuffle(); // Before dealing card to each player - shuffle
        // Dealing Cards
        while (mainDeck.getSize() > 0) {
            player1.addCard(mainDeck.dealCard());
            player2.addCard(mainDeck.dealCard());
        }
    }

}

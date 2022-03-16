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


public class Game {

    private static final int maxMoves = 500; // Maximum number of moves per game
    private static final DeckOfCards deckPlayer1 = new DeckOfCards();
    private static final DeckOfCards deckPlayer2 = new DeckOfCards();
    private static final ArrayList<Card> pot = new ArrayList<>();
    private static int moveCounter = 0; // Initializing the move counter to count up to maxMoves

    public static void main(String[] args) {

        setGame();
//        JOptionPane.showConfirmDialog(null, mainDeck.toString());
//        JOptionPane.showConfirmDialog(null, deckPlayer1.toString());
//        JOptionPane.showConfirmDialog(null, deckPlayer2.toString());
        while (deckPlayer1.getSize() != 0 && deckPlayer2.getSize() != 0) {
            turn();
        }
        if (deckPlayer1.getSize() == 0 && deckPlayer2.getSize() == 0) {
            JOptionPane.showMessageDialog(null, "Tie!");
        } else if (deckPlayer1.getSize() == 0) {
            JOptionPane.showMessageDialog(null, "Player 2 won!");
        } else {
            JOptionPane.showMessageDialog(null, "Player 1 won!");
        }
    }

    // This methods represents a regular turn
    private static void turn() {
        Card cardPlayer1 = deckPlayer1.dealCard();
        Card cardPlayer2 = deckPlayer2.dealCard();
        showMove(cardPlayer1, cardPlayer2);
        moveCounter++;
        // In case both players have same value card - we go to WAR
        if (cardPlayer1.getValue() > cardPlayer2.getValue()) {
            deckPlayer1.addCardToBottom(cardPlayer1);
            deckPlayer1.addCardToBottom(cardPlayer2);
        } else if(cardPlayer1.getValue() < cardPlayer2.getValue()) {
            deckPlayer2.addCardToBottom(cardPlayer1);
            deckPlayer2.addCardToBottom(cardPlayer2);
        } else {
            war();
            pot.clear();
        }

    }

    // In case the players had same value card - 3 card will be drawn
    private static void war() {
        for (int i=0; i < 3; i++) {
            if (deckPlayer1.getSize() == 0 && deckPlayer2.getSize() == 0) {
                return;
            } else if (deckPlayer1.getSize() == 0) {
                return;
            } else if (deckPlayer2.getSize() == 0) {
                return;
            } else {
                Card cardPlayer1 = deckPlayer1.dealCard();
                Card cardPlayer2 = deckPlayer2.dealCard();
                showMove(cardPlayer1, cardPlayer2);
                pot.add(cardPlayer1);
                pot.add(cardPlayer2);
            }
            int idxLast1 = pot.size() - 2;
            int idxLast2 = pot.size() - 1;
            Card cardLast1 = pot.get(idxLast1);
            Card cardLast2 = pot.get(idxLast2);
            if (cardLast1.getValue() > cardLast2.getValue()) {
                deckPlayer1.addCardsToBottom(pot);
//                JOptionPane.showMessageDialog(null, "Player 1 has beaten Player 2 at war.");
                System.out.println("Player 1 has beaten Player 2 at war.");
            } else if (cardLast1.getValue() < cardLast2.getValue()) {
                deckPlayer2.addCardsToBottom(pot);
//                JOptionPane.showMessageDialog(null, "Player 2 has beaten Player 1 at war.");
                System.out.println("Player 2 has beaten Player 1 at war.");
            } else {
                war();
            }

        }


    }
    // Initializing a new game - Two players and two decks
    private static void setGame() {
        deckPlayer1.shuffle();
        deckPlayer2.shuffle();
    }

    private static void showMove(Card card1, Card card2) {
//        JOptionPane.showMessageDialog(null, "Player 1 has drawn the " + card1.toString() + "\n" +
//                "Player 2 has drawn the " + card2.toString());
        System.out.println(
                "Player 1 has drawn the " + card1.toString() + "\n" +
                "Player 2 has drawn the " + card2.toString());
    }

}

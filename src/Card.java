// This class represents a playing card

public class Card {
    private final String face;
    private final String suit;
    private int value;

    public Card(String cardFace, String cardSuit, int value) {
        this.face = cardFace; // Initialize face of card
        this.suit = cardSuit; // Initialize suit of card
        this.value = value; // Initialize value of card
    }

    public String toString() {
        return face + " of " + suit;
    }

    // The following method compares the value of cards for the game
    public int compare(Card card1) {
        final int BIGGER = 1;
        final int EQUAL = 0;
        final int SMALLER = -1;

        if (this.value > card1.value) {
            return BIGGER;
        }
        if (this.value < card1.value) {
            return SMALLER;
        }
        return EQUAL;
    }
}

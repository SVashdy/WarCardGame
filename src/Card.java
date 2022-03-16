// This class represents a playing card

public class Card {
    private final String face;
    private final String suit;
    private final int value;

    public Card(String cardFace, String cardSuit, int value) {
        this.face = cardFace; // Initialize face of card
        this.suit = cardSuit; // Initialize suit of card
        this.value = value; // Initialize value of card
    }

    public String toString() {
        return face + " of " + suit;
    }

    public int getValue() {
        return this.value;
    }
}

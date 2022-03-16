import java.security.SecureRandom;
import java.util.ArrayList;

public class DeckOfCards {
    // random number generator
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52; // constant # of Cards

    private final ArrayList<Card> deck = new ArrayList<Card>(NUMBER_OF_CARDS);
    private int currentCard = 0; // index of next Card to be dealt (0-51)

    // constructor fills deck of Cards
    public DeckOfCards() {
        String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        // populate deck with Card objects
        for (int count = 0; count < NUMBER_OF_CARDS; count++) {
            deck.add(new Card(faces[count % 13], suits[count / 13], count % 13 + 1));
        }
    }

    public int getSize() {
        return this.deck.size();
    }

    public void addCard(Card card) {
        this.deck.add(0, card);
    }
    public void addCardToBottom(Card card) {
        this.deck.add(this.deck.size(), card);
    }
    public void addCardsToBottom(ArrayList<Card> cards) {
        for (int i=0; i<cards.size(); i++) {
            this.deck.add(this.deck.size(), cards.get(i));
        }
    }


    // shuffle deck of Cards with one-pass algorithm
    public void shuffle() {
        // next call to method dealCard should start at deck[0] again
        currentCard = 0;

        // for each Card, pick another random Card (0-51) and swap them
        for (int first = 0; first < deck.size(); first++) {
            // select a random number between 0 and 51
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            // swap current Card with randomly selected Card
            Card temp = deck.get(first);
            deck.set(first, deck.get(second));
            deck.set(second, temp);
        }
    }

//    // Initializing an empty deck for player1 and player2
//    public DeckOfCards(String playerName) {
//    }

    public String toString() {
        return this.deck.toString() + this.getSize();
    }
    // deal one Card
    public Card dealCard() {
        // determine whether Cards remain to be dealt
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }
} 
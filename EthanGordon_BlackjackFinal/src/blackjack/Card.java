package blackjack;

/**
 * @author Ethan Gordon
 */
public class Card {

    private final int val;
    private final Suit suit;
    private final int id;

    /**
     * @param val value 1 to 10
     * @param id id 1 to 13
     * @param suit suit hearts, diamonds, spades, or clubs
     */
    public Card(int val, int id, Suit suit) {
        this.val = val;
        this.suit = suit;
        this.id = id;
    }

    /**
     * @return value
     */
    public int getVal() {
        return val;
    }

    /**
     * @return suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @return id
     */
    public int getID() {
        return id;
    }

    /**
     * @return string representation of card
     */
    @Override
    public String toString() {
        String s = "";
        switch (id) {
            case 1:
                s = "Ace ";
                break;
            case 2:
                s = "2 ";
                break;
            case 3:
                s = "3 ";
                break;
            case 4:
                s = "4 ";
                break;
            case 5:
                s = "5 ";
                break;
            case 6:
                s = "6 ";
                break;
            case 7:
                s = "7 ";
                break;
            case 8:
                s = "8 ";
                break;
            case 9:
                s = "9 ";
                break;
            case 10:
                s = "10 ";
                break;
            case 11:
                s = "Jack ";
                break;
            case 12:
                s = "Queen ";
                break;
            case 13:
                s = "King ";
                break;
        }
        String s2 = "";
        switch (suit) {
            case hearts:
                s2 = "of hearts";
                break;
            case spades:
                s2 = "of spades";
                break;
            case clubs:
                s2 = "of clubs";
                break;
            case diamonds:
                s2 = "of diamonds";
                break;
        }
        return s + s2;
    }
}

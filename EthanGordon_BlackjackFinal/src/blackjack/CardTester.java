package blackjack;

/**
 * @author Ethan Gordon
 */
public class CardTester {
    /**
     * test card
     * @param args 
     */
    public static void main(String args[]) {
        for (int i = 1; i <= 13; i++) {
            int val = i > 10 ? 10 : i;
            System.out.println(new Card(val, i, Suit.spades));
            System.out.println(new Card(val, i, Suit.hearts));
            System.out.println(new Card(val, i, Suit.diamonds));
            System.out.println(new Card(val, i, Suit.clubs));
        }
    }
}

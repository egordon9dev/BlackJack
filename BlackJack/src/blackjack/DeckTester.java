/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author ethan
 */
public class DeckTester {
    public static void main(String args[]) {
        Deck deck = new Deck();
        for(Card c : deck.getCards()) {
            System.out.println(c);
        }
    }
}
/*
OUTPUT

Ace of hearts
Ace of diamonds
Ace of spades
Ace of clubs
2 of hearts
2 of diamonds
2 of spades
2 of clubs
3 of hearts
3 of diamonds
3 of spades
3 of clubs
4 of hearts
4 of diamonds
4 of spades
4 of clubs
5 of hearts
5 of diamonds
5 of spades
5 of clubs
6 of hearts
6 of diamonds
6 of spades
6 of clubs
7 of hearts
7 of diamonds
7 of spades
7 of clubs
8 of hearts
8 of diamonds
8 of spades
8 of clubs
9 of hearts
9 of diamonds
9 of spades
9 of clubs
10 of hearts
10 of diamonds
10 of spades
10 of clubs
Jack of hearts
Jack of diamonds
Jack of spades
Jack of clubs
Queen of hearts
Queen of diamonds
Queen of spades
Queen of clubs
King of hearts
King of diamonds
King of spades
King of clubs

*/
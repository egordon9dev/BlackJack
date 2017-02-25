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
public class CardTester {
    public static void main(String args[]) {
        for(int i = 1; i <= 13; i++) {
            int val = i > 10 ? 10 : i;
            System.out.println(new Card(val, i, Suit.spades));
            System.out.println(new Card(val, i, Suit.hearts));
            System.out.println(new Card(val, i, Suit.diamonds));
            System.out.println(new Card(val, i, Suit.clubs));
        }
    }
}

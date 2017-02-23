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

public class Card {
    private final int val;
    private final Suit suit;
    /*
    @param val value
    @param suit suit
    */
    public Card(int val, Suit suit) { this.val = val; this.suit = suit; }
    public int getval() { return val; }
    public Suit getSuit() { return suit; }
}
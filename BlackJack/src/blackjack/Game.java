/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;
import java.util.Scanner;
/**
 *
 * @author ethan
 */

public class Game {
    private static Player player = new Player();
    private static Dealer dealer = new Dealer();
    private static Shoe shoe = new Shoe();
    public static void main(String args[]) {
        
        System.out.println("BlackJack");
        
        player.getHand().drawCard(shoe.drawCard());
        player.getHand().drawCard(shoe.drawCard());
        dealer.getHand().drawCard(shoe.drawCard());
        dealer.getHand().drawCard(shoe.drawCard());
        
        int in;
        Scanner scan = new Scanner(System.in);
        
        boolean stuffHappened = true;
        
        while(stuffHappened) {
            //player bust
            if(player.getHand().getVal() > 21) {
                System.out.println("player bust");
                break;
            }
            //dealer bust
            if(dealer.getHand().getVal() > 21) {
                System.out.println("dealer bust");
                break;
            }
            
            in = -1;
            stuffHappened = false;
            System.out.println("player: " + player.getHand().getVal());
            System.out.println("dealer: " + dealer.getHand().getVal());
            
            System.out.println("1: HIT\t2: STAND");
            in = scan.nextInt();
            
            switch(in) {
                case 1:
                    player.getHand().drawCard(shoe.drawCard());
                    stuffHappened = true;
                    break;
                case 2:
                    break;
            }
            if(dealer.getHand().getVal() <= 17) {
                dealer.getHand().drawCard(shoe.drawCard());
                stuffHappened = true;
            }
        }
        System.out.println("player: " + player.getHand().getVal());
        System.out.println("dealer: " + dealer.getHand().getVal());
            
        if(!player.getHand().isBust() && !dealer.getHand().isBust()) {
            if(player.getHand().getVal() > dealer.getHand().getVal())
                System.out.println("player wins");
            else if(player.getHand().getVal() < dealer.getHand().getVal())
                System.out.println("dealer wins");
            else if(player.getHand().getVal() > 21 && dealer.getHand().getVal() > 21)
                System.out.println("no winner");
            else
                System.out.println("tie");
        }
    }
}

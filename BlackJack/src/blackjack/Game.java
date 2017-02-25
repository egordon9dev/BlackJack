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
    private static Player player;
    private static Dealer dealer = new Dealer();
    private static Shoe shoe = new Shoe();
    public static void main(String args[]) {
        boolean repeat = true;
        Scanner scan = new Scanner(System.in);
        System.out.println("\t\t\tBlackJack\n\n");
        
        System.out.println("enter the amount of money you wish to have on you're account:");
        double playerMoney = 0;
        while(true){
            try {
                playerMoney = Double.parseDouble(scan.next());
                break;
            } catch(Exception e) {}
        }
        
        player = new Player(playerMoney);
        
        while(repeat && player.getMoney() > 0.0) {
            player.resetHand();
            dealer.resetHand();
            System.out.println("Account: $" + player.getMoney());
            System.out.println("How much would you like to bet?");
            double bet = 0;
            while(true) {
                try{
                    bet = Double.parseDouble(scan.next());
                    if(bet > player.getMoney()) {
                        System.out.println("invalid entry, you can't afford to bet that much");
                        continue;
                    }
                    break;
                } catch(Exception e) {}
            }
            player.bet(bet);
            
            player.getHand().drawCard(shoe.drawCard());
            player.getHand().drawCard(shoe.drawCard());
            dealer.getHand().drawCard(shoe.drawCard());
            dealer.getHand().drawCard(shoe.drawCard());

            int in;

            boolean playerDidStuff = true;
            boolean dealerDidStuff = true;
            boolean standing = false;
            while(playerDidStuff || dealerDidStuff) {
                Hand playerHand = player.getHand();
                Hand dealerHand = dealer.getHand();
                //player bust
                if(playerHand.getVal() > 21) {
                    System.out.println("player bust");
                    break;
                }
                //dealer bust
                if(dealerHand.getVal() > 21) {
                    System.out.println("dealer bust");
                    player.earn(2.0*bet);
                    break;
                }
                //player charlie
                if(playerHand.isCharlie()) {
                    System.out.println("player has a 5 Card Charlie!");
                    break;
                }
                //dealer charlie
                if(dealerHand.isCharlie()) {
                    System.out.println("dealer has a 5 Card Charlie!");
                    break;
                }
                //player blackjack
                if(playerHand.isBlackjack()) {
                    System.out.println("player has a blackjack!");
                    break;
                }
                //dealer blackjack
                if(dealerHand.isBlackjack()) {
                    System.out.println("dealer has a blackjack!");
                    break;
                }

                in = -1;
                //System.out.println("player: " + player.getHand().getVal());
                //System.out.println("dealer: " + dealer.getHand().getVal());

                System.out.println("player: "  + player.getHand());
                System.out.println("dealer: " + dealer.getHand());
                
                System.out.println("1: HIT\t2: Double\t3:STAND");
                if(standing) in = 3;
                else {
                    while(true){
                        try {
                            in = Integer.parseInt(scan.next());
                            if(in == 2 && player.getMoney() < bet) {
                                System.out.println("invalid entry, you can't afford to double down");
                                continue;
                            }
                            break;
                        } catch(Exception e) {}
                    }
                }
                
                switch(in) {
                    case 2:
                        player.bet(bet);
                        bet*=2.0;
                        standing = true;
                    case 1:
                        Card c = shoe.drawCard();
                        System.out.println("player drawing a " + c);
                        player.getHand().drawCard(c);
                        playerDidStuff = true;
                        break;
                    case 3:
                        playerDidStuff = false;
                        standing = true;
                        break;
                }
                if(dealer.getHand().getVal() <= 17) {
                    Card c = shoe.drawCard();
                    System.out.println("dealer drawing a " + c);
                    dealer.getHand().drawCard(c);
                    dealerDidStuff = true;
                } else {
                    dealerDidStuff = false;
                }
            }
            System.out.println("player: "  + player.getHand());
            System.out.println("dealer: " + dealer.getHand());

            Hand pHand = player.getHand();
            Hand dHand = dealer.getHand();

            
            if(!player.getHand().isBust() && !dealer.getHand().isBust()) {
                if(pHand.isBlackjack() || pHand.isCharlie()) {
                    System.out.println("player wins");
                    player.earn(2.0*bet);
                }
                else if(dHand.isBlackjack() || dHand.isCharlie())
                    System.out.println("dealer wins");
                else if(player.getHand().getVal() > dealer.getHand().getVal()) {
                    System.out.println("player wins");
                    player.earn(2.0*bet);
                }
                else if(player.getHand().getVal() < dealer.getHand().getVal())
                    System.out.println("dealer wins");
                else if(player.getHand().getVal() > 21 && dealer.getHand().getVal() > 21)
                    System.out.println("no winner");
                else {
                    System.out.println("tie");
                    player.earn(bet);
                }
            }
            System.out.println("\n\nagain?");
            if(scan.next().equals("y")) repeat = true;
            else repeat = false;
        }
        System.out.println("You've gone bankrupt!\nGAMEOVER");
    }
}

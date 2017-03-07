/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
/**
 *
 * @author ethan
 */
public class Player{
    private double money = 0.0;
    private double bet = 0.0;
    private double insBet = 0.0;
    private int focusedPlayer = 0;
    public double getMoney() { return money; }
    public void setMoney(double money) { this.money = money; }
    public double getBet() { return bet; }
    public void setBet(double bet) { this.bet = bet; }
    public double getInsBet() { return insBet; }
    public void setInsBet(double n) { insBet = n; }
    public int getFocusedPlayer() { return focusedPlayer; }
    public void incFocusedPlayer() {
        focusedPlayer++;
        if(focusedPlayer >= players.size()) focusedPlayer = 0;
    }
    public void earn(double earnings) { money += earnings; }
    public void betMoney(double n) { money -= n; }
    ArrayList<PartialPlayer> players = new ArrayList<PartialPlayer>();
    public ArrayList<PartialPlayer> getPlayers() { return players; }
    public boolean isBust() {
        for(PartialPlayer p : players) {
            if(!p.getHand().isBust()) return false;
        }
        return true;
    }
    public boolean isActive() {
        for(PartialPlayer p : players) {
            if(p.isActive()) return true;
        }
        return false;
    }
    public void reset() {
        bet= 0.0;
        insBet = 0.0;
        focusedPlayer = 0;
        players = new ArrayList<PartialPlayer>();
        players.add(new PartialPlayer());
    }
    public Player(double money) {
        this.money = money;
        players.add(new PartialPlayer());
    }
    public ArrayList<ArrayList<BufferedImage>> createCardClips() {
        ArrayList<ArrayList<BufferedImage>> imgs = new ArrayList<ArrayList<BufferedImage>>();
        for(int i = 0; i < players.size(); i++) {
            imgs.add(new ArrayList<BufferedImage>());
            ArrayList<Card> cards = players.get(i).getHand().getCards();
            for(int j = 0; j < cards.size(); j++) {
                int n = -1;
                switch(cards.get(j).getSuit()) {
                    case hearts:
                        n = 0;
                        break;
                    case diamonds:
                        n = 1;
                        break;
                    case clubs:
                        n = 2;
                        break;
                    case spades:
                        n = 3;
                        break;
                }
                imgs.get(i).add(GameGUI.cardSheet.getSubimage((cards.get(j).getID()-1)*44, n*63, 44, 63));
            }
        }
        return imgs;
    }
}

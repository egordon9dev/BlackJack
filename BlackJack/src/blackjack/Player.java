/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;
import java.util.ArrayList;
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
    public void setInsBet(double insBet) { this.insBet = insBet; }
    public int getFocusedPlayer() { return focusedPlayer; }
    public void incFocusedPlayer() {
        focusedPlayer++;
        if(focusedPlayer >= players.size()) focusedPlayer = 0;
    }
    public void earn(double earnings) { money += earnings; }
    public void betMoney(double n) { money -= n; }
    ArrayList<PartialPlayer> players = new ArrayList<PartialPlayer>();
    public ArrayList<PartialPlayer> getPlayers() { return players; }
    
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
}

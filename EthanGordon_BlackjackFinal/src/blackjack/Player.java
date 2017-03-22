package blackjack;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

/**
 * @author Ethan Gordon
 */
public class Player {
    
    protected double money = 0.0;
    protected double bet = 0.0;
    private double insBet = 0.0;
    private int focusedPlayer = 0;
    private boolean bankrupt = false;
    private ArrayList<PartialPlayer> players = new ArrayList<PartialPlayer>();

    /**
     * sets bankrupt flag
     *
     * @param b
     */
    public void setBankrupt(boolean b) {
        bankrupt = b;
    }

    /**
     * @return bankrupt flag
     */
    public boolean isBankrupt() {
        return bankrupt;
    }

    /**
     * @return Money
     */
    public double getMoney() {
        return money;
    }

    /**
     * sets money flag
     *
     * @param money
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * adds money to bankroll
     *
     * @param earnings
     */
    public void earn(double earnings) {
        money += earnings;
    }

    /**
     * subtracts money from bankroll
     *
     * @param n how much money to bet
     */
    public void betMoney(double n) {
        money -= n;
    }

    /**
     * @return bet
     */
    public double getBet() {
        return bet;
    }

    /**
     * sets bet
     *
     * @param bet
     */
    public void setBet(double bet) {
        this.bet = bet;
    }

    /**
     * @return insurance bet
     */
    public double getInsBet() {
        return insBet;
    }

    /**
     * sets insurance bet
     *
     * @param n
     */
    public void setInsBet(double n) {
        insBet = n;
    }

    /**
     * @return index of focused player
     */
    public int getFocusedPlayer() {
        return focusedPlayer;
    }

    /**
     * @return players
     */
    public ArrayList<PartialPlayer> getPlayers() {
        return players;
    }

    /**
     * draws a card to the focused player's hand
     *
     * @param c
     */
    public void drawCard(Card c) {
        players.get(focusedPlayer).getHand().drawCard(c);
    }

    /**
     * updates activity
     */
    public void updateActivity() {
        for (PartialPlayer p : players) {
            if (p.isStanding() || p.getHand().isCharlie() || p.getHand().isBust()
                    || players.get(focusedPlayer).getHand().isBlackjack() && players.size() == 1) {
                p.setActive(false);
            }
        }
    }

    /**
     * increments focused player
     */
    public void incFocusedPlayer() {
        if (!isActive()) {
            GameGUI.getAllP().incPlayerUp();
            return;
        }
        //updateActivity();
        while (true) {
            focusedPlayer++;
            if (focusedPlayer >= players.size()) {
                focusedPlayer = 0;
                GameGUI.getAllP().incPlayerUp();
                break;
            }
            if (players.get(focusedPlayer).isActive()) {
                break;
            }
        }
    }

    /**
     * @return whether or not the player has busted
     */
    public boolean isBust() {
        for (PartialPlayer p : players) {
            if (!p.getHand().isBust()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return whether or not the player is active
     */
    public boolean isActive() {
        for (PartialPlayer p : players) {
            if (p.isActive()) {
                return true;
            }
        }
        return false;
    }

    /**
     * reset the player
     */
    public void reset() {
        bet = 0.0;
        insBet = 0.0;
        focusedPlayer = 0;
        players = new ArrayList<PartialPlayer>();
        players.add(new PartialPlayer());
    }

    /**
     * create new player
     */
    public Player() {
        players.add(new PartialPlayer());
    }

    /**
     * @return separate images of each card as a clip from the sprite-sheet
     */
    public ArrayList<ArrayList<BufferedImage>> createCardClips() {
        ArrayList<ArrayList<BufferedImage>> imgs = new ArrayList<ArrayList<BufferedImage>>();
        for (int i = 0; i < players.size(); i++) {
            imgs.add(new ArrayList<BufferedImage>());
            ArrayList<Card> cards = players.get(i).getHand().getCards();
            for (int j = 0; j < cards.size(); j++) {
                int n = -1;
                switch (cards.get(j).getSuit()) {
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
                imgs.get(i).add(GameGUI.cardSheet.getSubimage((cards.get(j).getID() - 1) * 44, n * 63, 44, 63));
            }
        }
        return imgs;
    }
}
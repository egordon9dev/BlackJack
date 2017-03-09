/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author ethan
 */

public class GameGUI {

    public static BufferedImage cardSheet;
    private static ArrayList<ArrayList<BufferedImage>> dealerCardClips;
    private static JLabel labelDealerCards, labelDealer;
    private static JButton butHit, butStand, butDouble, butAgain, butSplit;
    private static boolean again = true;

    private static int playerUp = 0;
    private static final int NUM_PLAYERS = 4;

    private static int PLAYER_TURN = 14981;
    private static int DEALER_TURN = 98321;
    private static int turn = PLAYER_TURN;
    private static Player player[];
    private static Dealer dealer = new Dealer();
    private static Shoe shoe = new Shoe(5);

    private static pvar_t pvars[] = new pvar_t[NUM_PLAYERS];

    /**
     * PRECONDITION: it must be the player's turn
     */
    private static void updatePlayerUp() {
        int ctr = 0;
        while( (ctr <= NUM_PLAYERS && !player[playerUp].isActive())|| player[playerUp].isBankrupt() ) {
            playerUp++;
            if(playerUp >= NUM_PLAYERS) {
                playerUp = 0;
                turn = DEALER_TURN;
            }
            ctr++;
        }
    }
    private static void incPlayerUp() {
        playerUp++;
        if(playerUp >= NUM_PLAYERS) {
            playerUp = 0;
            turn = DEALER_TURN;
        }
        updatePlayerUp();
    }

    public static void setupGUI() {
        pvars[0] = new pvar_t();
        pvars[1] = new pvar_t();
        pvars[2] = new pvar_t();
        pvars[3] = new pvar_t();

        JFrame frame = new JFrame();
        MainPanel panel = new MainPanel();

        for(int i = 0; i < NUM_PLAYERS; i++) {
            pvars[i].textMoney = new JTextField(10);
            pvars[i].textBet = new JTextField(10);
            pvars[i].textIns = new JTextField(10);

            pvars[i].labelMoney = new JLabel("MONEY: ");
            pvars[i].labelBet = new JLabel("BET: ");
            pvars[i].labelIns = new JLabel("INSURANCE BET: ");

            pvars[i].textMoney.setFocusable(false);
            pvars[i].textIns.setText("0");
            pvars[i].textMoney.setBackground(MainPanel.color);
            pvars[i].textMoney.setBorder(BorderFactory.createLineBorder(MainPanel.color));
            pvars[i].textBet.setBackground(MainPanel.color);
            pvars[i].textBet.setBorder(BorderFactory.createLineBorder(MainPanel.color));
            pvar_t pvar = pvars[i];
            pvars[i].textBet.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                }

                @Override
                public void focusLost(FocusEvent fe) {
                    pvar.strBet = pvar.textBet.getText();
                }
            });
            pvars[i].textBet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    pvar.strBet = pvar.textBet.getText();
                }
            });
            pvars[i].textIns.setBackground(MainPanel.color);
            pvars[i].textIns.setBorder(BorderFactory.createLineBorder(MainPanel.color));
            pvars[i].textIns.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                }

                @Override
                public void focusLost(FocusEvent fe) {
                    pvar.strIns = pvar.textIns.getText();
                }
            });
            pvars[i].textIns.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    pvar.strIns = pvar.textIns.getText();
                }
            });
            pvars[i].northPanel = new SubPanel(new FlowLayout());
            pvars[i].northPanel.add(pvars[i].labelMoney);
            pvars[i].northPanel.add(pvars[i].textMoney);
            pvars[i].northPanel.add(pvars[i].labelBet);
            pvars[i].northPanel.add(pvars[i].textBet);
            pvars[i].northPanel.add(pvars[i].labelIns);
            pvars[i].northPanel.add(pvars[i].textIns);

            pvars[i].playerCardClips = new ArrayList<ArrayList<BufferedImage>>();
            pvars[i].labelPlayerCards = new JLabel();
            pvars[i].labelPlayer = new JLabel("Player "+i+": ");
        }
        Box northBox = Box.createVerticalBox();
        northBox.add(pvars[0].northPanel);
        northBox.add(pvars[1].northPanel);
        northBox.add(pvars[2].northPanel);
        northBox.add(pvars[3].northPanel);
        panel.add(northBox, BorderLayout.NORTH);

        butHit = new JButton("HIT");
        butDouble = new JButton("DOUBLE");
        butStand = new JButton("STAND");
        butSplit = new JButton("SPLIT");
        butAgain = new JButton("Again?");
        butHit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (turn == PLAYER_TURN) {
                    PartialPlayer p = player[playerUp].getPlayers().get(player[playerUp].getFocusedPlayer());
                    if (p.isActive()) {
                        Card c = shoe.drawCard();
                        player[playerUp].drawCard(c);
                    }
                    player[playerUp].incFocusedPlayer();
                    if (player[playerUp].getFocusedPlayer() == 0) {
                        incPlayerUp();
                    }
                }
            }
        });
        butDouble.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (turn == PLAYER_TURN) {
                    PartialPlayer p = player[playerUp].getPlayers().get(player[playerUp].getFocusedPlayer());
                    if (p.isActive()) {
                        Card c = shoe.drawCard();
                        player[playerUp].drawCard(c);
                        p.setDoubled();
                        player[playerUp].betMoney(player[playerUp].getBet());
                        p.setStanding(true);
                    }
                    player[playerUp].incFocusedPlayer();
                    if (player[playerUp].getFocusedPlayer() == 0) {
                        incPlayerUp();
                    }
                }
            }
        });
        butSplit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (turn == PLAYER_TURN) {
                    int fp = player[playerUp].getFocusedPlayer();
                    if (player[playerUp].getPlayers().get(fp).isActive()) {
                        Hand hand0 = player[playerUp].getPlayers().get(fp).getHand();
                        //if it's splitable and if no cards have been added since it's been updated
                        //to being splitable - making sure it's still splitable
                        if (hand0.isSplitable() && hand0.getCards().size() == 2
                                && player[playerUp].getMoney() >= player[playerUp].getBet()) {
                            player[playerUp].getPlayers().add(fp + 1, new PartialPlayer());
                            ArrayList<Card> cards0 = hand0.getCards();
                            ArrayList<Card> cards1 = player[playerUp].getPlayers().get(fp + 1).getHand().getCards();
                            cards1.add(cards0.get(1));
                            cards0.remove(1);
                            player[playerUp].betMoney(player[playerUp].getBet());
                        }
                    } else {
                        player[playerUp].incFocusedPlayer();
                        if (player[playerUp].getFocusedPlayer() == 0) {
                            incPlayerUp();
                        }
                    }
                }
            }
        });
        butStand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (turn == PLAYER_TURN) {
                    PartialPlayer p = player[playerUp].getPlayers().get(player[playerUp].getFocusedPlayer());
                    if (p.isActive()) {
                        p.setStanding(true);
                    }
                    player[playerUp].incFocusedPlayer();
                    if (player[playerUp].getFocusedPlayer() == 0) {
                        incPlayerUp();
                    }
                }
            }
        });
        butAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                again = true;
            }
        });
        SubPanel south = new SubPanel(new FlowLayout());
        butHit.setBackground(new Color(234, 227, 124));
        butHit.setBorder(BorderFactory.createLineBorder(new Color(193, 79, 34), 1));
        butHit.setPreferredSize(new Dimension(170, 50));
        butDouble.setBackground(new Color(234, 227, 124));
        butDouble.setBorder(BorderFactory.createLineBorder(new Color(193, 79, 34), 1));
        butDouble.setPreferredSize(new Dimension(170, 50));
        butSplit.setBackground(new Color(234, 227, 124));
        butSplit.setBorder(BorderFactory.createLineBorder(new Color(193, 79, 34), 1));
        butSplit.setPreferredSize(new Dimension(170, 50));
        butStand.setBackground(new Color(234, 227, 124));
        butStand.setBorder(BorderFactory.createLineBorder(new Color(193, 79, 34), 1));
        butStand.setPreferredSize(new Dimension(170, 50));
        south.add(butHit);
        south.add(butDouble);
        south.add(butSplit);
        south.add(butStand);
        panel.add(south, BorderLayout.SOUTH);

        butAgain.setBackground(new Color(136, 224, 123));
        butAgain.setBorder(BorderFactory.createLineBorder(new Color(51, 140, 37), 1));
        butAgain.setPreferredSize(new Dimension(100, 50));
        panel.add(butAgain, BorderLayout.EAST);

        dealerCardClips = new ArrayList<ArrayList<BufferedImage>>();
        try {
            cardSheet = ImageIO.read(new File("playingCards2.png"));
        } catch (IOException e) {
        }

        labelDealer = new JLabel("Dealer: ");
        labelDealerCards = new JLabel();
        Box westBox = Box.createVerticalBox();
        for(int i = 0; i < NUM_PLAYERS; i++) {
            SubPanel cardPane = new SubPanel(new FlowLayout());
            cardPane.setBackground(MainPanel.color);
            cardPane.add(pvars[i].labelPlayer);
            cardPane.add(pvars[i].labelPlayerCards);
            westBox.add(cardPane);
        }
        SubPanel dealerCardPane = new SubPanel(new FlowLayout());
        dealerCardPane.setBackground(MainPanel.color);
        dealerCardPane.add(labelDealer);
        dealerCardPane.add(labelDealerCards);
        westBox.add(dealerCardPane);
        panel.add(westBox, BorderLayout.WEST);

        frame.add(panel);
        frame.setSize(800, 650);
        frame.setResizable(false);
        frame.setTitle("Blackjack by Ethan Gordon");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.requestFocus();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }

    public static BufferedImage joinCards(ArrayList<ArrayList<BufferedImage>> imgs) {
        if(imgs.get(0).size() == 0) return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        //trim list
        for (int i = imgs.size() - 1; i >= 0; i--) {
            if (imgs.get(i).size() <= 0) {
                imgs.remove(i);
            }
        }
        //find dimensions
        int w = 0, h = imgs.size();
        for (ArrayList<BufferedImage> i : imgs) {
            if (i.size() > w) {
                w = i.size();
            }
        }
        int totalW = imgs.get(0).get(0).getWidth() * w;
        int totalH = imgs.get(0).get(0).getHeight() * h;
        //create new image
        BufferedImage newImage = new BufferedImage(totalW, totalH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setColor(oldColor);
        for (int y = 0; y < imgs.size(); y++) {
            for (int x = 0; x < imgs.get(y).size(); x++) {
                BufferedImage image;
                image = imgs.get(y).get(x);
                g2.drawImage(image, null, imgs.get(0).get(0).getWidth() * x, imgs.get(0).get(0).getHeight() * y);
            }
        }
        g2.dispose();
        return newImage;
    }
    public static boolean allPlayersBankrupt(int n) {
        if(n == 0) return player[n].isBankrupt();
        return player[n].isBankrupt() && allPlayersBankrupt(n-1);
    }
    public static boolean allPlayersNotActive(int n) {
        if(n == 0) return !player[n].isActive();
        return !player[n].isActive() && allPlayersNotActive(n-1);
    }
    public static boolean notAllPlayersBust(int n) {
        if(n == 0) return !player[n].isBust();
        return !player[n].isBust() || notAllPlayersBust(n-1);
    }
    public static void main(String args[]) {

        setupGUI();

        player = new Player[NUM_PLAYERS];
        for(int i = 0; i < NUM_PLAYERS; i++) player[i] = new Player(10000);

        while (!allPlayersBankrupt(NUM_PLAYERS-1)) {
            for(int i = 0; i < NUM_PLAYERS; i++) {
                pvars[i].textMoney.setText(String.valueOf(player[i].getMoney()));
            }
            if (again) {
                for(int i = 0; i < NUM_PLAYERS; i++) {
                    player[i].reset();
                    pvars[i].strIns = "invalid";
                    pvars[i].strBet = "invalid";
                    pvars[i].textBet.setText("invalid");
                    pvars[i].textIns.setText("invalid");
                    
                    if(player[i].isBankrupt()) {
                        for(PartialPlayer p : player[i].getPlayers()) {
                            p.setActive(false);
                        }
                        continue;
                    }
                    //get bet input
                    while (true) {
                        try {
                            while (pvars[i].strBet.equals("invalid")) {}
                            double bet = Double.parseDouble(pvars[i].strBet);
                            if (bet > player[i].getMoney() || bet < 0.0) {
                                continue;
                            }
                            player[i].betMoney(bet);
                            player[i].setBet(bet);
                            break;
                        } catch (Exception e) {}
                    }

                    PartialPlayer pp = player[i].getPlayers().get(0);
                    //setup hands
                    player[i].drawCard(shoe.drawCard());
                    player[i].drawCard(shoe.drawCard());
                }
                dealer.reset();
                playerUp = 0;
                turn = PLAYER_TURN;
                String strPlayer[] = new String[NUM_PLAYERS];

                dealer.getHand().drawCard(shoe.drawCard());
                dealer.getHand().drawCard(shoe.drawCard());
                
                while (!allPlayersNotActive(NUM_PLAYERS-1)) {
                    updatePlayerUp();
                    for(int q = 0; q < NUM_PLAYERS; q++) {
                        if(player[q].getPlayers().get(player[q].getFocusedPlayer()).isStanding() ||
                          player[q].getPlayers().get(player[q].getFocusedPlayer()).getHand().isCharlie() ||
                          player[q].getPlayers().get(player[q].getFocusedPlayer()).getHand().isBust()   ) {
                            player[q].getPlayers().get(player[q].getFocusedPlayer()).setActive(false);
                        }
                        if(q == playerUp) {
                            pvars[q].labelPlayer.setText("player "+q+": =========>>");
                            pvars[q].labelPlayer.setBackground(Color.GREEN);
                        } else {
                            pvars[q].labelPlayer.setText("player "+q+": ");
                            pvars[q].labelPlayer.setBackground(Color.BLACK);
                        }
                    }
                    Hand dealerHand = dealer.getHand();
                    //dealer bust
                    if (dealerHand.getVal() > 21) {
                        System.out.println("dealer bust");
                        break;
                    }
                    //dealer charlie
                    if (dealerHand.isCharlie()) {
                        System.out.println("dealer has a 5 Card Charlie!");
                        break;
                    }
                    for(int w = 0; w < NUM_PLAYERS; w++) {
                        strPlayer[w] = "player "+w+": ";
                        for (int i = 0; i < player[w].getPlayers().size(); i++) {
                            strPlayer[w] += player[w].getPlayers().get(i).toString() + "\n";
                        }
                        System.out.println(strPlayer[w]);
                        pvars[w].playerCardClips = player[w].createCardClips();
                        pvars[w].labelPlayerCards.setIcon(new ImageIcon(joinCards(pvars[w].playerCardClips)));
                    }
                    System.out.println("dealer: " + dealer);
                    dealerCardClips.clear();
                    dealerCardClips.add(dealer.createCardClips(true));
                    labelDealerCards.setIcon(new ImageIcon(joinCards(dealerCardClips)));

                    for(int w = 0; w < NUM_PLAYERS; w++) {
                        //insurance bet input
                        if (player[w].getInsBet() > -0.000001 && player[w].getInsBet() < 0.000001
                                && dealerHand.getCards().get(1).getID() == 1) {
                            double max = player[w].getBet() / 2.0 < player[w].getMoney() ? player[w].getBet() / 2.0 : player[w].getMoney();
                            while (true) {
                                try {
                                    while (pvars[w].strIns.equals("invalid")) {
                                    }
                                    Double insBet = Double.parseDouble(pvars[w].strIns);
                                    if (insBet > max || insBet < 0.0) {
                                        continue;
                                    }
                                    player[w].setInsBet(insBet);
                                    player[w].betMoney(insBet);
                                    break;
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                    //dealer blackjack
                    if (dealerHand.isBlackjack()) {
                        System.out.println("dealer has a blackjack!");
                        break;
                    }

                    while((turn == DEALER_TURN ||
                         allPlayersNotActive(NUM_PLAYERS-1)) &&
                         notAllPlayersBust(NUM_PLAYERS-1) &&
                         dealer.isActive()){

                        if (dealer.getHand().getVal() <= 16) {
                            Card c = shoe.drawCard();
                            //System.out.println("dealer drawing a " + c);
                            dealer.getHand().drawCard(c);
                            dealer.setActive(true);
                        } else {
                            dealer.setActive(false);
                        }
                        turn = PLAYER_TURN;
                        playerUp = 0;
                    }
                }

                Hand dHand = dealer.getHand();
                if (dHand.isBlackjack()) {
                    for(int w = 0; w < NUM_PLAYERS; w++) {
                        player[w].earn(3.0 * player[w].getInsBet());
                    }
                }
                if (dHand.isBust()) {
                    for(int w = 0; w < NUM_PLAYERS; w++) {
                        for (int i = 0; i < player[w].getPlayers().size(); i++) {
                            double mult = player[w].getPlayers().get(i).isDoubled() ? 2.0 : 1.0;
                            if (player[w].getPlayers().get(i).getHand().isBust()) {
                                player[w].earn(mult * player[w].getBet());
                            } else {
                                player[w].earn(mult * 2.0 * player[w].getBet());
                            }
                        }
                    }
                } else {
                    for(int w = 0; w < NUM_PLAYERS; w++) {
                        for (int i = 0; i < player[w].getPlayers().size(); i++) {
                            Hand pHand = player[w].getPlayers().get(i).getHand();
                            int n = -2; // -1:loss   0:tie   1:win
                            if (!pHand.isBust()) {
                                if (pHand.isCharlie()) {
                                    n = 1;
                                } else if (pHand.isBlackjack() && player[w].getPlayers().size() == 1) {
                                    if (dHand.isBlackjack()) {
                                        n = 0;
                                    } else {
                                        player[w].earn(0.5 * player[w].getBet());
                                        n = 1;
                                    }
                                } else if (pHand.getVal() > dHand.getVal()) {
                                    n = 1;
                                } else if (pHand.getVal() == dHand.getVal()) {
                                    n = 0;
                                } else {
                                    n = -1;
                                }
                            } else {
                                n = -1;
                            }

                            double mult = player[w].getPlayers().get(i).isDoubled() ? 2.0 : 1.0;
                            if (n != -1) {
                                if (n == 1) {
                                    mult *= 2;
                                }
                                player[w].earn(mult * player[w].getBet());
                            }
                        }
                    }
                }

                for(int w = 0; w < NUM_PLAYERS; w++) {
                    System.out.println("Money: " + player[w].getMoney());
                    pvars[w].textMoney.setText(String.valueOf(player[w].getMoney()));
                    System.out.println(strPlayer[w]);
                    System.out.println("dealer: " + dealer.getHand());
                    pvars[w].playerCardClips = player[w].createCardClips();
                    dealerCardClips.clear();
                    dealerCardClips.add(dealer.createCardClips(false));
                    pvars[w].labelPlayerCards.setIcon(new ImageIcon(joinCards(pvars[w].playerCardClips)));
                    labelDealerCards.setIcon(new ImageIcon(joinCards(dealerCardClips)));
                }
                again = false;
            }
            for(int q = 0; q < NUM_PLAYERS; q++) {
                if(player[q].getMoney() <= 0) player[q].setBankrupt(true);
            }
        }
        System.out.println("You've gone bankrupt!");
        System.out.println("GAMEOVER");
        System.exit(0);
    }
}

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
    private static JButton butHit, butStand, butDouble, butAgain, butSplit;
    private static boolean again = true;
    
    private static GraphicDealer dealer = new GraphicDealer();
    private static Shoe shoe = new Shoe(5);

    private static AllPlayers allP;
    public static AllPlayers getAllP() { return allP; }

    public static final boolean DEBUG_MODE = true;
    public static void writeLog(String s) {
        if(DEBUG_MODE) System.out.println(s);
    }
    
    public static void setupGUI() {
        
        JFrame frame = new JFrame();
        MainPanel panel = new MainPanel();

        Box northBox = Box.createVerticalBox();
        for(int i = 0; i < allP.numP(); i++) {
            northBox.add(allP.p(i).getNorthPanel());
        }
        panel.add(northBox, BorderLayout.NORTH);

        butHit = new JButton("HIT");
        butDouble = new JButton("DOUBLE");
        butStand = new JButton("STAND");
        butSplit = new JButton("SPLIT");
        butAgain = new JButton("Again?");
        butHit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                allP.p().drawCard(shoe.drawCard());
                allP.update();
                if(!allP.p().getPlayers().get(allP.p().getFocusedPlayer()).isActive()) allP.p().incFocusedPlayer();
            }
        });
        butDouble.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PartialPlayer pp = allP.p().getPlayers().get(allP.p().getFocusedPlayer());
                pp.setDoubled();
                allP.p().betMoney(allP.p().getBet());
                pp.setStanding(true);
                allP.p().drawCard(shoe.drawCard());
                allP.update();
                if(!allP.p().getPlayers().get(allP.p().getFocusedPlayer()).isActive()) allP.p().incFocusedPlayer();
            }
        });
        butSplit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fp = allP.p().getFocusedPlayer();
                Hand hand0 = allP.p().getPlayers().get(fp).getHand();
                //if it's splitable and if no cards have been added since it's been updated
                //to being splitable - making sure it's still splitable
                if (hand0.isSplitable() && hand0.getCards().size() == 2
                        && allP.p().getMoney() >= allP.p().getBet()) {
                    allP.p().getPlayers().add(fp + 1, new PartialPlayer());
                    ArrayList<Card> cards0 = hand0.getCards();
                    ArrayList<Card> cards1 = allP.p().getPlayers().get(fp + 1).getHand().getCards();
                    cards1.add(cards0.get(1));
                    cards0.remove(1);
                    allP.p().betMoney(allP.p().getBet());
                }
            }
        });
        butStand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                allP.p().getPlayers().get(allP.p().getFocusedPlayer()).setStanding(true);
                allP.update();
                if(!allP.p().getPlayers().get(allP.p().getFocusedPlayer()).isActive()) allP.p().incFocusedPlayer();
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

        butAgain.setBackground(MainPanel.color);
        butAgain.setBorder(BorderFactory.createLineBorder(new Color(51, 140, 37), 1));
        butAgain.setPreferredSize(new Dimension(100, 50));
        panel.add(butAgain, BorderLayout.EAST);

        
        try {
            cardSheet = ImageIO.read(new File("playingCards2.png"));
        } catch (IOException e) {
        }

        Box wb1 = Box.createVerticalBox();
        Box wb2 = Box.createVerticalBox();
        for(int i = 0; i < allP.numP(); i++) {
            SubPanel playerPane = new SubPanel(new FlowLayout());
            playerPane.setBackground(MainPanel.color);
            playerPane.add(allP.p(i).getLabelPlayer());
            playerPane.add(allP.p(i).getLabelPlayerCards());
            if(i < (allP.numP())/2) wb1.add(playerPane);
            else wb2.add(playerPane);
        }
        SubPanel dealerPane = new SubPanel(new FlowLayout());
        dealerPane.setBackground(MainPanel.color);
        dealerPane.add(dealer.getLabelDealer());
        dealerPane.add(dealer.getLabelDealerCards());
        wb1.add(dealerPane);
        Box westBox = Box.createHorizontalBox();
        westBox.add(wb1);
        westBox.add(wb2);
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
    public static void resetGUI() {
        butAgain.setBackground(MainPanel.color);
        butAgain.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        butAgain.setText("");
        for(int i = 0; i < allP.numP(); i++) {
            allP.p(i).getLabelPlayerCards().setIcon(new ImageIcon(
                    new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB)));
            allP.p(i).getLabelPlayer().setText("");
            dealer.getLabelDealer().setText("");
        }
        dealer.getLabelDealerCards().setIcon(new ImageIcon(
            new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB)));
    }
    public static BufferedImage joinCards(ArrayList<ArrayList<BufferedImage>> imgs) {
        if(imgs.get(0).isEmpty()) return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
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
    
    public static void main(String args[]) {
        allP = new AllPlayers(4);
        setupGUI();

        while (!allP.allPlayersBankrupt()) {
            for(int i = 0; i < allP.numP(); i++) writeLog("Player "+i+" Money: "+allP.p(i).getMoney());
            allP.updateTextMoney();
            if (again) {
                resetGUI();
                dealer.nextRound();
                allP.nextRound();
                allP.setup(shoe);
                dealer.getHand().drawCard(shoe.drawCard());
                dealer.getHand().drawCard(shoe.drawCard());
                
                String strPlayer[] = new String[allP.numP()];

                
                while (!allP.allPlayersNotActive()) {
                    allP.update();
                    dealer.getLabelDealer().setText("Dealer:");
                    Hand dealerHand = dealer.getHand();
                    //dealer bust
                    if (dealerHand.getVal() > 21 || dealerHand.isCharlie()) break;
                    //update player and dealer cards in gui
                    allP.updateCards();
                    if(DEBUG_MODE) for(int q = 0; q < allP.numP(); q++){
                        writeLog(allP.p(q).getStrPlayer());
                        writeLog("dealer: " + dealer);
                    }
                    dealer.updateDealerCardClips(true);
                    //this blocks untill valid insurance bets are recieved
                    if(dealerHand.getCards().get(1).getID() == 1) allP.updateInsBet();
                    //dealer blackjack
                    if (dealerHand.isBlackjack()) {
                        if(DEBUG_MODE) writeLog("dealer has a blackjack!");
                        break;
                    }
                    while(true) {
                        dealer.updateActivity();
                        if(!allP.isPlayerTurn() && allP.notAllPlayersBust() && dealer.isActive()){
                            dealer.getHand().drawCard(shoe.drawCard());
                            dealer.updateActivity();
                        } else break;
                    }
                }

                butAgain.setBackground(new Color(136, 224, 123));
                butAgain.setBorder(BorderFactory.createLineBorder(new Color(51, 140, 37), 1));
                butAgain.setText("AGAIN?");
                Hand dHand = dealer.getHand();
                if (dHand.isBlackjack()) {
                    for(int w = 0; w < allP.numP(); w++) {
                        allP.p(w).earn(3.0 * allP.p(w).getInsBet());
                    }
                }
                if (dHand.isBust()) {
                    for(int w = 0; w < allP.numP(); w++) {
                        for (int i = 0; i < allP.p(w).getPlayers().size(); i++) {
                            double mult = allP.p(w).getPlayers().get(i).isDoubled() ? 2.0 : 1.0;
                            if (allP.p(w).getPlayers().get(i).getHand().isBust()) {
                                allP.p(w).earn(mult * allP.p(w).getBet());
                            } else {
                                allP.p(w).earn(mult * 2.0 * allP.p(w).getBet());
                            }
                        }
                    }
                } else {
                    for(int w = 0; w < allP.numP(); w++) {
                        for (int i = 0; i < allP.p(w).getPlayers().size(); i++) {
                            Hand pHand = allP.p(w).getPlayers().get(i).getHand();
                            int n = -2; // -1:loss   0:tie   1:win
                            if (!pHand.isBust()) {
                                if (pHand.isCharlie()) {
                                    n = 1;
                                } else if (pHand.isBlackjack() && allP.p(w).getPlayers().size() == 1) {
                                    if (dHand.isBlackjack()) {
                                        n = 0;
                                    } else {
                                        allP.p(w).earn(0.5 * allP.p(w).getBet());
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

                            double mult = allP.p(w).getPlayers().get(i).isDoubled() ? 2.0 : 1.0;
                            if (n != -1) {
                                if (n == 1) {
                                    mult *= 2;
                                }
                                allP.p(w).earn(mult * allP.p(w).getBet());
                            }
                        }
                    }
                }

                allP.updateTextMoney();
                allP.updateCards();
                dealer.updateDealerCardClips(!allP.notAllPlayersBust());
                for(int w = 0; w < allP.numP(); w++) {
                    writeLog("Player "+w+" Money: "+allP.p(w).getMoney());
                    writeLog(strPlayer[w]);
                }
                writeLog("dealer: " + dealer.getHand());
                again = false;
            }
            allP.updateBankrupt();
        }
        writeLog("You've gone bankrupt!");
        writeLog("GAMEOVER");
        System.exit(0);
    }
}
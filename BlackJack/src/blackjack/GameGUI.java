/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author ethan
 */
public class GameGUI {
    private static JTextField textMoney, textBet, textIns, textDealer, textPlayer;
    private static JLabel labelMoney, labelBet, labelIns, labelDealer, labelPlayer;
    private static JButton butHit, butStand, butDouble, butAgain;
    private static String strBet = "invalid", strIns = "invalid";
    private static int butStatus = -1;
    private static boolean again = true;
    private static int PLAYER_TURN = 14981;
    private static int DEALER_TURN = 98321;
    private static int turn = PLAYER_TURN;
    public static void setupGUI() {
        /*try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){}*/
        JFrame frame = new JFrame();
        MainPanel panel = new MainPanel();
        
        textMoney = new JTextField(10);
        textMoney.setFocusable(false);
        textBet = new JTextField(10);
        textIns = new JTextField(10);
        textIns.setText("0");
        textMoney.setBackground(MainPanel.color);
        textMoney.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        textBet.setBackground(MainPanel.color);
        textBet.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        textBet.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {}
            @Override
            public void focusLost(FocusEvent fe) {
                strBet = textBet.getText();
            }
        });
        textBet.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                strBet = textBet.getText();
            }
        });
        textIns.setBackground(MainPanel.color);
        textIns.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        textIns.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {}
            @Override
            public void focusLost(FocusEvent fe) {
                strIns = textIns.getText();
            }
        });
        textIns.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                strIns = textIns.getText();
            }
        });
        labelMoney = new JLabel("MONEY: ");
        labelBet = new JLabel("BET: ");
        labelIns = new JLabel("INSURANCE BET: ");
        SubPanel north = new SubPanel(new FlowLayout());
        north.add(labelMoney);
        north.add(textMoney);
        north.add(labelBet);
        north.add(textBet);
        north.add(labelIns);
        north.add(textIns);
        panel.add(north, BorderLayout.NORTH);
        
        textPlayer = new JTextField(50);
        textPlayer.setFocusable(false);
        textDealer = new JTextField(50);
        textDealer.setFocusable(false);
        textPlayer.setBackground(MainPanel.color);;
        textPlayer.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        textDealer.setBackground(MainPanel.color);;
        textDealer.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        labelPlayer = new JLabel("Player: ");
        labelDealer = new JLabel("Dealer: ");
        //SubPanel center = new SubPanel();
        //center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        Box box = Box.createVerticalBox();
        SubPanel c1 = new SubPanel(new FlowLayout());
        c1.add(labelPlayer);
        c1.add(textPlayer);
        c1.setBackground(MainPanel.color);
        SubPanel c2 = new SubPanel(new FlowLayout());
        c2.add(labelDealer);
        c2.add(textDealer);
        c2.setBackground(MainPanel.color);
        c2.setPreferredSize(new Dimension(200, 20));
        box.add(Box.createVerticalStrut(220));
        box.add(c1);
        box.add(c2);
        box.add(Box.createVerticalStrut(220));
        panel.add(box, BorderLayout.CENTER);
        
        
        butHit = new JButton("HIT");
        butDouble = new JButton("DOUBLE");
        butStand = new JButton("STAND");
        butAgain = new JButton("Again?");
        butHit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.setActive(true);
                Card c = shoe.drawCard();
                //System.out.println("player drawing a " + c);
                player.getHand().drawCard(c);
                turn = DEALER_TURN;
            }          
        });
        butDouble.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.setActive(true);
                Card c = shoe.drawCard();
                //System.out.println("player drawing a " + c);
                player.getHand().drawCard(c);
                player.doubleDown();
                player.setStanding(true);
                turn = DEALER_TURN;
            }          
        });
        butStand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.setStanding(true);
                player.setActive(true);
                turn = DEALER_TURN;
            }          
        });
        butAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                again = true;
                player.setActive(true);
                dealer.setActive(true);
                turn = PLAYER_TURN;
            }    
        });
        SubPanel south = new SubPanel(new FlowLayout());
        south.add(butHit);
        south.add(butDouble);
        south.add(butStand);
        panel.add(south, BorderLayout.SOUTH);
        
        SubPanel east = new SubPanel(new FlowLayout());
        east.add(butAgain);
        panel.add(east, BorderLayout.EAST);
        
        SubPanel west = new SubPanel(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
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
    

    private static Player player;
    private static Dealer dealer = new Dealer();
    private static Shoe shoe = new Shoe();
    public static void main(String args[]) {
        setupGUI();
        
        double playerMoney = 10000;
        player = new Player(playerMoney);
        
        while(player.getMoney() > 0.0) {
            if(again) {
                player.reset();
                dealer.reset();
                textMoney.setText(String.valueOf(player.getMoney()));
                double bet = 0.0;
                while(true) {
                    try{
                        bet = Double.parseDouble(strBet);
                        if(bet > player.getMoney() || bet < 0.0) {
                            continue;
                        }
                        break;
                    } catch(Exception e) {}
                }
                player.betMoney(bet);

                player.getHand().drawCard(shoe.drawCard());
                player.getHand().drawCard(shoe.drawCard());
                dealer.getHand().drawCard(shoe.drawCard());
                dealer.getHand().drawCard(shoe.drawCard());

                double insBet = 0.0;
                while(player.isActive() || dealer.isActive()) {
                    if(player.isStanding()) player.setActive(false);
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

                    //System.out.println("player: " + player.getHand().getVal());
                    //System.out.println("dealer: " + dealer.getHand().getVal());

                    System.out.println("player: "  + player);
                    System.out.println("dealer: " + dealer);
                    textPlayer.setText(player.toString());
                    textDealer.setText(dealer.toString());

                    if(insBet > -0.000001 && insBet < 0.000001 && dealerHand.getCards().get(1).getID() == 1) {
                        double max = bet/2.0 < player.getMoney() ? bet/2.0 : player.getMoney();
                        while(true) {
                            try { 
                                insBet = Double.parseDouble(strIns);
                                if(insBet > max || insBet < 0.0) {
                                    insBet = 0.0;
                                    continue;
                                }
                                break;
                            }
                            catch(Exception e) {
                                insBet = 0;
                            }
                        }
                        player.betMoney(insBet);
                    }

                    //dealer blackjack
                    if(dealerHand.isBlackjack()) {
                        System.out.println("dealer has a blackjack!");
                        break;
                    }

                    System.out.println("1: HIT\t2: DOUBLE\t3: STAND");
                    
                    if(turn == DEALER_TURN || player.isStanding()) {
                        if(dealer.getHand().getVal() <= 16) {
                            Card c = shoe.drawCard();
                            //System.out.println("dealer drawing a " + c);
                            dealer.getHand().drawCard(c);
                            dealer.setActive(true);
                        } else {
                            dealer.setActive(false);
                        }
                        turn = PLAYER_TURN;
                    }
                }
                System.out.println("player: "  + player.getHand());
                System.out.println("dealer: " + dealer.getHand());
                textPlayer.setText(player.getHand().toString());
                textDealer.setText(dealer.getHand().toString());

                Hand pHand = player.getHand();
                Hand dHand = dealer.getHand();

                if(dHand.isBlackjack()) player.earn(2*insBet);
                if(dHand.isBust()) {
                    if(pHand.isBust()) {
                        player.earn(bet);
                        System.out.println("tie");
                    } else {
                        player.earn(2.0*bet);
                        System.out.println("player wins");
                    }
                }
                if(pHand.isBust() && !dHand.isBust()) {
                    System.out.println("dealer wins");
                }
                if(!pHand.isBust() && !dHand.isBust()) {
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
                again = false;
            }
        }
        System.out.println("You've gone bankrupt!");
        System.out.println("GAMEOVER");
    }
}
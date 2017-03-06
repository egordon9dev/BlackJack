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
    private static ArrayList<ArrayList<BufferedImage>> playerCardClips;
    private static ArrayList<ArrayList<BufferedImage>> dealerCardClips;
    private static JLabel labelPlayerCards, labelDealerCards;
    private static JTextField textMoney, textBet, textIns, textDealer;
    private static JTextArea textPlayer;
    private static JLabel labelMoney, labelBet, labelIns, labelSplit, labelDealer, labelPlayer;
    private static JButton butHit, butStand, butDouble, butAgain, butSplit;
    private static String strBet = "invalid", strIns = "invalid";
    private static int butStatus = -1;
    private static boolean again = true;
    private static int PLAYER_TURN = 14981;
    private static int DEALER_TURN = 98321;
    private static int turn = PLAYER_TURN;
    private static Player player;
    private static Dealer dealer = new Dealer();
    private static Shoe shoe = new Shoe();
    
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
        
        textPlayer = new JTextArea(4, 50);
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
        Box centerBox = Box.createVerticalBox();
        SubPanel c1 = new SubPanel(new FlowLayout());
        c1.add(labelPlayer);
        c1.add(textPlayer);
        c1.setBackground(MainPanel.color);
        SubPanel c2 = new SubPanel(new FlowLayout());
        c2.add(labelDealer);
        c2.add(textDealer);
        c2.setBackground(MainPanel.color);
        c2.setPreferredSize(new Dimension(200, 20));
        centerBox.add(Box.createVerticalStrut(220));
        centerBox.add(c1);
        centerBox.add(c2);
        centerBox.add(Box.createVerticalStrut(220));
        //panel.add(box, BorderLayout.CENTER);
        
        
        butHit = new JButton("HIT");
        butDouble = new JButton("DOUBLE");
        butStand = new JButton("STAND");
        butSplit = new JButton("SPLIT");
        butAgain = new JButton("Again?");
        butHit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(turn == PLAYER_TURN) {
                    PartialPlayer p = player.getPlayers().get(player.getFocusedPlayer());
                    if(p.isActive()) {
                        Card c = shoe.drawCard();
                        p.getHand().drawCard(c);
                    }
                    player.incFocusedPlayer();
                    if(player.getFocusedPlayer() == 0) turn = DEALER_TURN;
                }
            }          
        });
        butDouble.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(turn == PLAYER_TURN) {
                    PartialPlayer p = player.getPlayers().get(player.getFocusedPlayer());
                    if(p.isActive()) {
                        Card c = shoe.drawCard();
                        p.getHand().drawCard(c);
                        p.setDoubled();
                        player.betMoney(player.getBet());
                        p.setStanding(true);
                    }
                    player.incFocusedPlayer();
                    if(player.getFocusedPlayer() == 0) turn = DEALER_TURN;
                }
            }          
        });
        butSplit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(turn == PLAYER_TURN) {
                    ArrayList<PartialPlayer> players = player.getPlayers();
                    int fp = player.getFocusedPlayer();
                    if(players.get(fp).isActive()) {
                        Hand hand0 = players.get(fp).getHand();
                        //if it's splitable and if no cards have been added since it's been updated
                        //to being splitable - making sure it's still splitable
                        if(hand0.isSplitable() && hand0.getCards().size() == 2 &&
                            player.getMoney() >= player.getBet()) {
                            players.add(fp+1, new PartialPlayer());
                            ArrayList<Card> cards0 = hand0.getCards();
                            ArrayList<Card> cards1 = players.get(fp+1).getHand().getCards();
                            cards1.add(cards0.get(1) );
                            cards0.remove(1);
                            player.betMoney(player.getBet());
                        }
                    } else {
                        player.incFocusedPlayer();
                        if(player.getFocusedPlayer() == 0) turn = DEALER_TURN;
                    }
                }
            }
        });
        butStand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(turn == PLAYER_TURN) {
                    PartialPlayer p = player.getPlayers().get(player.getFocusedPlayer());
                    if(p.isActive()) {
                        p.setStanding(true);
                    }
                    player.incFocusedPlayer();
                    if(player.getFocusedPlayer() == 0) turn = DEALER_TURN;
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

        playerCardClips = new ArrayList<ArrayList<BufferedImage>>();
        dealerCardClips = new ArrayList<ArrayList<BufferedImage>>();
        try {
            cardSheet = ImageIO.read(new File("playingCards2.png"));
        } catch(IOException e) {}
        
        labelPlayerCards = new JLabel();
        labelDealerCards = new JLabel();
        Box westBox = Box.createHorizontalBox();
        westBox.add(labelPlayer);
        westBox.add(labelPlayerCards);
        westBox.add(Box.createHorizontalStrut(200));
        westBox.add(labelDealer);
        westBox.add(labelDealerCards);
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
        //trim list
        for(int i = imgs.size()-1; i >= 0; i--) {
            if(imgs.get(i).size() <= 0) imgs.remove(i);
        }
        //find dimensions
        int w = 0, h = imgs.size();
        for(ArrayList<BufferedImage> i : imgs) {
            if(i.size() > w) w = i.size();
        }
        int totalW = imgs.get(0).get(0).getWidth() * w;
        int totalH = imgs.get(0).get(0).getHeight() * h;
        //create new image
        BufferedImage newImage = new BufferedImage(totalW, totalH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setColor(oldColor);
        for(int y = 0; y < imgs.size(); y++) {
            for(int x = 0; x < imgs.get(y).size(); x++) {
                BufferedImage image;
                image = imgs.get(y).get(x);
                g2.drawImage(image, null, imgs.get(0).get(0).getWidth()*x, imgs.get(0).get(0).getHeight()*y);
            }
        }
        g2.dispose();
        return newImage;
    }
    
    public static void main(String args[]) {
        setupGUI();
        /*
        BufferedImage clip = cardSheet.getSubimage(44, 0, 44, 63);
        cardClips.add(new ArrayList<BufferedImage>());
        cardClips.add(new ArrayList<BufferedImage>());
        cardClips.add(new ArrayList<BufferedImage>());
        for(int i = 0; i < cardClips.size(); i++) {
            for(int j = 0; j < i; j++) cardClips.get(i).add(clip);
        }
        labelCard.setIcon(new ImageIcon(joinCards(cardClips)));
        */
        double playerMoney = 10000;
        player = new Player(playerMoney);
        ArrayList<PartialPlayer> players;
        
        while(player.getMoney() > 0.0) {
            textMoney.setText(String.valueOf(player.getMoney()));
            if(again) {
                player.reset();
                players = player.getPlayers();
                dealer.reset();

                strIns = "invalid";
                strBet = "invalid";
                textBet.setText("invalid");
                textIns.setText("invalid");
                turn = PLAYER_TURN;
                //get bet input
                while(true) {
                    try{
                        while(strBet.equals("invalid")) {}
                        double bet = Double.parseDouble(strBet);
                        if(bet > player.getMoney() || bet < 0.0) {
                            continue;
                        }
                        player.betMoney(bet);
                        player.setBet(bet);
                        break;
                    } catch(Exception e) {}
                }

                PartialPlayer p0 = players.get(0);

                //setup hands
                p0.getHand().drawCard(shoe.drawCard());
                p0.getHand().drawCard(shoe.drawCard());
                dealer.getHand().drawCard(shoe.drawCard());
                dealer.getHand().drawCard(shoe.drawCard());
                
                while(player.isActive() || dealer.isActive()) {
                    
                    for(int i = 0; i < players.size(); i++) {
                        PartialPlayer p = players.get(i);  
                        if(p.isStanding()) p.setActive(false);
                        Hand hand = p.getHand();
                        if(hand.getVal() > 21) {
                            System.out.println("player "+i+" bust");
                            p.setActive(false);
                        }
                        if(hand.isCharlie()) {
                            System.out.println("player "+i+" charlie");
                            p.setActive(false);
                        }
                        if(hand.isBlackjack()) {
                            System.out.println("player "+i+" blackjack");
                            p.setActive(false);
                        }
                    }
                    Hand dealerHand = dealer.getHand();
                    //dealer bust
                    if(dealerHand.getVal() > 21) {
                        System.out.println("dealer bust");
                        break;
                    }
                    //dealer charlie
                    if(dealerHand.isCharlie()) {
                        System.out.println("dealer has a 5 Card Charlie!");
                        break;
                    }

                    //System.out.println("player: " + player.getHand().getVal());
                    //System.out.println("dealer: " + dealer.getHand().getVal());
                    String strPlayer = "";
                    for(int i = 0; i < players.size(); i++) {
                        strPlayer += players.get(i).toString() + "\n";
                    }
                    System.out.println(strPlayer);
                    System.out.println("dealer: " + dealer);
                    textPlayer.setText(strPlayer);
                    textDealer.setText(dealer.toString());
                    playerCardClips = player.createCardClips();
                    dealerCardClips = new ArrayList<ArrayList<BufferedImage>>();
                    dealerCardClips.add(dealer.createCardClips());
                    labelPlayerCards.setIcon(new ImageIcon(joinCards(playerCardClips)));
                    labelDealerCards.setIcon(new ImageIcon(joinCards(dealerCardClips)));
                    
                    //insurance bet input
                    if(player.getInsBet() > -0.000001 && player.getInsBet() < 0.000001
                            && dealerHand.getCards().get(1).getID() == 1) {
                        double max = player.getBet()/2.0 < player.getMoney() ? player.getBet()/2.0 : player.getMoney();
                        while(true) {
                            try {
                                while(strIns.equals("invalid")) {}
                                Double insBet = Double.parseDouble(strIns);
                                if(insBet > max || insBet < 0.0) {
                                    continue;
                                }
                                player.setInsBet(insBet);
                                player.betMoney(insBet);
                                break;
                            }
                            catch(Exception e) {}
                        }
                    }
                    //dealer blackjack
                    if(dealerHand.isBlackjack()) {
                        System.out.println("dealer has a blackjack!");
                        break;
                    }

                    if(turn == DEALER_TURN || !player.isActive()) {
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
                    
                    for(int i = 0; i < players.size(); i++) {
                        PartialPlayer p = players.get(i);  
                        if(p.isStanding()) p.setActive(false);
                        Hand hand = p.getHand();
                        if(hand.getVal() > 21) {
                            System.out.println("player "+i+" bust");
                            p.setActive(false);
                        }
                        if(hand.isCharlie()) {
                            System.out.println("player "+i+" charlie");
                            p.setActive(false);
                        }
                        if(hand.isBlackjack()) {
                            System.out.println("player "+i+" blackjack");
                            p.setActive(false);
                        }
                    }
                }
                String strPlayer = "";
                for(int i = 0; i < players.size(); i++) {
                    strPlayer += players.get(i).toString() + "\n";
                }
                System.out.println(strPlayer);
                System.out.println("dealer: " + dealer.getHand());
                textPlayer.setText(strPlayer);
                textDealer.setText(dealer.getHand().toString());

                Hand dHand = dealer.getHand();
                if(dHand.isBlackjack()) player.earn(3.0*player.getInsBet());
                if(dHand.isBust()) {
                    for(int i = 0; i < players.size(); i++) {
                        double mult = players.get(i).isDoubled() ? 2.0 : 1.0;
                        if(players.get(i).getHand().isBust()) {
                            player.earn(mult * player.getBet());
                        } else {
                            player.earn(mult * 2.0*player.getBet());
                        }
                    }
                } else {
                    for(int i = 0; i < players.size(); i++) {
                        Hand pHand = players.get(i).getHand();
                        int n = -2; // -1:loss   0:tie   1:win
                        if(!pHand.isBust()) {
                            if(pHand.isCharlie()) n = 1;
                            else if(pHand.isBlackjack()) {
                                if(dHand.isBlackjack()) n = 0;
                                else n = 1;
                            }
                            else if(pHand.getVal() > dHand.getVal()) n = 1;
                            else if(pHand.getVal() == dHand.getVal()) n = 0;
                            else n = -1;
                        }
                        double mult = players.get(i).isDoubled() ? 2.0 : 1.0;
                        if(n != -1) {
                            if(n == 1) mult *= 2;
                            player.earn(mult * player.getBet());
                        }
                    }
                }
                again = false;
            }
        }
        System.out.println("You've gone bankrupt!");
        System.out.println("GAMEOVER");
    }
}
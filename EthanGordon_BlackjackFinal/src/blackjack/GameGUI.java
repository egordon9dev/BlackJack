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
 * @author Ethan Gordon
 */

public class GameGUI {

    public static BufferedImage cardSheet;
    private static JButton butHit, butStand, butDouble, butAgain, butSplit;
    private static boolean again = true;
    private static JTextArea textResult;
    private static JFrame mainFrame;
    private static CardLayout layout;
    private static JPanel mainPanel;
    private static MainPanel startPanel;
    private static MainPanel gamePanel;
    private static boolean started = false;
    
    private static GraphicDealer dealer = new GraphicDealer();
    private static Shoe shoe = new Shoe(5);

    private static AllPlayers allP;
    /**
     * @return all players
     */
    public static AllPlayers getAllP() { return allP; }

    public static final boolean DEBUG_MODE = false;
    private static final String START_PANEL = "start panel";
    private static final String GAME_PANEL = "game panel";
    
    private static JButton buttonPlay;
    private static JLabel labelNumPlayers;
    private static JTextField textNumPlayers;
    /**
     * @param s
     * prints text if in debug mode
     */
    public static void writeLog(String s) {
        if(DEBUG_MODE) System.out.println(s);
    }
    private static void setupMainGUI() {
        labelNumPlayers = new JLabel("Number of players: ");
        textNumPlayers = new JTextField(5);
        buttonPlay = new JButton("PLAY"); 
        buttonPlay.addActionListener(new ActionListener() {
            /**
             * runs when you click this button
             * @param ae 
             */
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    int n = Integer.parseInt(textNumPlayers.getText());
                    if(n < 1 || n > 7) return;
                    allP = new AllPlayers(n);
                    mainFrame.setSize(800, 650);
                    layout.show(mainPanel, GAME_PANEL);
                    started = true;
                } catch(Exception e) {}
            }
        });
        startPanel = new MainPanel();
        Box startBox = Box.createVerticalBox();
        startBox.add(labelNumPlayers);
        startBox.add(textNumPlayers);
        startBox.add(buttonPlay);
        startPanel.add(startBox, BorderLayout.NORTH);
        
        
        mainFrame = new JFrame();
        mainPanel = new JPanel();
        gamePanel = new MainPanel();
        layout = new CardLayout();
        mainPanel.setLayout(layout);
        mainPanel.add(startPanel, START_PANEL);
        mainPanel.add(gamePanel, GAME_PANEL);
        layout.show(mainPanel, START_PANEL);
        mainFrame.add(mainPanel);
        
        mainFrame.setSize(200, 150);
        mainFrame.setResizable(false);
        mainFrame.setTitle("Blackjack by Ethan Gordon");
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.requestFocus();
        
        EventQueue.invokeLater(new Runnable() {
            /**
             * repeatedly updates screen
             */
            @Override
            public void run() {
                mainFrame.setVisible(true);
            }
        });
        while(!started) {} // wait until we start the game
        
        Box northBox = Box.createVerticalBox();
        for(int i = 0; i < allP.numP(); i++) {
            northBox.add(allP.p(i).getNorthPanel());
        }
        gamePanel.add(northBox, BorderLayout.NORTH);

        butHit = new JButton("HIT");
        butDouble = new JButton("DOUBLE");
        butStand = new JButton("STAND");
        butSplit = new JButton("SPLIT");
        butAgain = new JButton("Again?");
        butHit.addActionListener(new ActionListener() {
            /**
             * makes player hit when button is pressed
             * @param e 
             */
            public void actionPerformed(ActionEvent e) {
                if(allP.isMoneySet()) {
                    allP.p().drawCard(shoe.drawCard());
                    allP.update();
                    if(!allP.p().getPlayers().get(allP.p().getFocusedPlayer()).isActive()) allP.p().incFocusedPlayer();
                }
            }
        });
        butDouble.addActionListener(new ActionListener() {
            /**
             * makes player double down when button is pressed
             * @param e 
             */
            public void actionPerformed(ActionEvent e) {
                if(allP.isMoneySet() && allP.p().getPlayers().size() == 1 &&
                 allP.p().getPlayers().get(allP.p().getFocusedPlayer()).getHand().getCards().size() == 2 &&
                 allP.p().getMoney() >= allP.p().getBet()) {
                    PartialPlayer pp = allP.p().getPlayers().get(allP.p().getFocusedPlayer());
                    pp.setDoubled();
                    allP.p().betMoney(allP.p().getBet());
                    pp.setStanding(true);
                    allP.p().drawCard(shoe.drawCard());
                    allP.update();
                    if(!allP.p().getPlayers().get(allP.p().getFocusedPlayer()).isActive()) allP.p().incFocusedPlayer();
                }
            }
        });
        butSplit.addActionListener(new ActionListener() {
            /**
             * makes player split when button is pressed
             * @param e 
             */
            public void actionPerformed(ActionEvent e) {
                int fp = allP.p().getFocusedPlayer();
                Hand hand0 = allP.p().getPlayers().get(fp).getHand();
                //if it's splitable and if no cards have been added since it's been updated
                //to being splitable - making sure it's still splitable
                if (allP.isMoneySet() && 
                        hand0.isSplitable() && hand0.getCards().size() == 2
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
            /**
             * makes player stand when button is pressed
             * @param e 
             */
            public void actionPerformed(ActionEvent e) {
                if(allP.isMoneySet()) {
                    allP.p().getPlayers().get(allP.p().getFocusedPlayer()).setStanding(true);
                    allP.update();
                    if(!allP.p().getPlayers().get(allP.p().getFocusedPlayer()).isActive()) allP.p().incFocusedPlayer();
                }
            }
        });
        butAgain.addActionListener(new ActionListener() {
            /**
             * runs main loop again to start a new round
             * @param e 
             */
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
        gamePanel.add(south, BorderLayout.SOUTH);

        SubPanel eastPanel = new SubPanel(new BorderLayout());
        butAgain.setBackground(MainPanel.color);
        butAgain.setBorder(BorderFactory.createLineBorder(new Color(51, 140, 37), 1));
        butAgain.setPreferredSize(new Dimension(80, 160));
        textResult = new JTextArea(10, 5);
        textResult.setFocusable(false);
        textResult.setText("");
        textResult.setBackground(MainPanel.color);
        textResult.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        eastPanel.add(textResult, BorderLayout.NORTH);
        eastPanel.add(butAgain, BorderLayout.SOUTH);
        eastPanel.setBackground(MainPanel.color);
        gamePanel.add(eastPanel, BorderLayout.EAST);

        
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
        gamePanel.add(westBox, BorderLayout.WEST);
    }
    /**
     * resets gui
     */
    private static void resetGUI() {
        butAgain.setBackground(MainPanel.color);
        butAgain.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        butAgain.setText("");
        textResult.setText("");
        for(int i = 0; i < allP.numP(); i++) {
            allP.p(i).getLabelPlayerCards().setIcon(new ImageIcon(
                    new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB)));
            allP.p(i).getLabelPlayer().setText("");
            dealer.getLabelDealer().setText("");
        }
        dealer.getLabelDealerCards().setIcon(new ImageIcon(
            new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB)));
    }
    /**
     * joins several images into one image in a way that represents which card
     * goes with which hand
     * @param imgs seperate images
     * @return combined image
     */
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
    /**
     * main code with main loop
     * @param args 
     */
    public static void main(String args[]) {
        setupMainGUI();
        while(!allP.isMoneySet()) {}
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

                allP.updateCards();
                dealer.updateDealerCardClips(true);
                dealer.getLabelDealer().setText("Dealer:");
                while (!allP.allPlayersNotActive()) {
                    Hand dealerHand = dealer.getHand();
                    //this blocks untill valid insurance bets are recieved
                    if(dealerHand.getCards().get(1).getID() == 1) allP.updateInsBet();
                    allP.update();
                    //dealer bust
                    if (dealerHand.getVal() > 21 || dealerHand.isCharlie()) break;
                    //update player and dealer cards in gui
                    allP.updateCards();
                    if(DEBUG_MODE) for(int q = 0; q < allP.numP(); q++){
                        writeLog(allP.p(q).getStrPlayer());
                        writeLog("dealer: " + dealer);
                    }
                    dealer.updateDealerCardClips(true);
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
                ArrayList<Integer> result[] = new ArrayList[allP.numP()];
                for(int w = 0; w < allP.numP(); w++) {
                    result[w] = new ArrayList<Integer>();
                    for(int q = 0; q < allP.p(w).getPlayers().size(); q++) {
                        result[w].add(-2);
                    }
                }
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
                                result[w].set(i, 0);
                            } else {
                                allP.p(w).earn(mult * 2.0 * allP.p(w).getBet());
                                result[w].set(i, 1);
                            }
                        }
                    }
                } else {
                    for(int w = 0; w < allP.numP(); w++) {
                        for (int i = 0; i < allP.p(w).getPlayers().size(); i++) {
                            Hand pHand = allP.p(w).getPlayers().get(i).getHand();
                            if (!pHand.isBust()) {
                                if (pHand.isCharlie()) {
                                    result[w].set(i, 1);
                                } else if (pHand.isBlackjack() && allP.p(w).getPlayers().size() == 1) {
                                    if (dHand.isBlackjack()) {
                                        result[w].set(i, 0);
                                    } else {
                                        allP.p(w).earn(0.5 * allP.p(w).getBet());
                                        result[w].set(i, 1);
                                    }
                                } else if (pHand.getVal() > dHand.getVal()) {
                                    result[w].set(i, 1);
                                } else if (pHand.getVal() == dHand.getVal()) {
                                    result[w].set(i, 0);
                                } else {
                                    result[w].set(i, -1);
                                }
                            } else {
                                result[w].set(i, -1);
                            }

                            double mult = allP.p(w).getPlayers().get(i).isDoubled() ? 2.0 : 1.0;
                            if (!result[w].get(i).equals(-1)) {
                                if (result[w].get(i).equals(1)) {
                                    mult *= 2;
                                }
                                allP.p(w).earn(mult * allP.p(w).getBet());
                            }
                        }
                    }
                }
                String sr = "";
                for(int w = 0; w < result.length; w++) {
                    for(int q = 0; q < result[w].size(); q++) {
                        sr += "Player "+ w+"."+q +": ";
                        switch(result[w].get(q)) {
                            case -1:
                                sr+="loss\n";
                                break;
                            case 0:
                                sr+="push\n";
                                break;
                            case 1:
                                sr+="win\n";
                                break;
                        }
                        sr += "\n";
                    }
                }
                textResult.setText(sr);
                
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
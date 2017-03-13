/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import static blackjack.GameGUI.joinCards;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ethan
 */
public class GraphicPlayer extends Player{
    
    private final JPanel northPanel;
    private JPanel westPanel;
    private ArrayList<ArrayList<BufferedImage>> playerCardClips;
    private JLabel labelPlayerCards;
    private final JLabel labelMoney, labelBet, labelIns, labelPlayer;
    private JTextField textMoney, textBet, textIns;
    private String strBet, strIns;
    private String strPlayer;
    
    public JPanel getNorthPanel() { return northPanel; }
    public JPanel getWestPanel() { return westPanel; }
    public JLabel getLabelPlayerCards() { return labelPlayerCards; }
    public JLabel getLabelPlayer() { return labelPlayer; }
    public String getStrBet() { return strBet; }
    public String getStrIns() { return strIns; }
    public String getStrPlayer() { return strPlayer; }
    
    public void setStrPlayer(String s) { strPlayer = s; }
    
    public void nextRound() {
        strBet = "";
        strIns = "";
        textBet.setText("");
        textIns.setText("");
    }
    
    public void updateBet() {
        while (true) {
            try {
                double bet = Double.parseDouble(strBet);
                if (bet > money || bet < 0.0) {
                    continue;
                }
                betMoney(bet);
                setBet(bet);
                break;
            } catch (Exception e) {}
        }
    }
    
    public void updateTextMoney() {
        textMoney.setText(String.valueOf(money));
    }
    
    public void updateInsBet() {
        double max = bet/2.0 < money ? bet/2.0 : money;       
        while (true) {
            try {
                Double d = 0.0;
                if(!isBankrupt()) {
                    d = Double.parseDouble(strIns);
                    if (d > max || d < 0.0) {
                        continue;
                    }
                }
                setInsBet(d);
                betMoney(d);
                break;
            } catch (Exception e) {
            }
        }
    }
            
    public void updatePlayerCardClips() {
        playerCardClips = createCardClips();
        labelPlayerCards.setIcon(new ImageIcon(joinCards(playerCardClips)));
    }
    
    public GraphicPlayer(double money) {
        super(money);
        
        strBet = "invalid";
        strIns = "invalid";
        textMoney = new JTextField(10);
        textBet = new JTextField(10);
        textIns = new JTextField(10);
        labelMoney = new JLabel("MONEY: ");
        labelBet = new JLabel("BET: ");
        labelIns = new JLabel("INSURANCE BET: ");
        textMoney.setFocusable(false);
        textIns.setText("0");
        textMoney.setBackground(MainPanel.color);
        textMoney.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        textBet.setBackground(MainPanel.color);
        textBet.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        northPanel = new SubPanel(new FlowLayout());
        northPanel.add(labelMoney);
        northPanel.add(textMoney);
        northPanel.add(labelBet);
        northPanel.add(textBet);
        northPanel.add(labelIns);
        northPanel.add(textIns);
        
        textBet.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
            }

            @Override
            public void focusLost(FocusEvent fe) {
                strBet = textBet.getText();
            }
        });
        textBet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                strBet = textBet.getText();
            }
        });
        textIns.setBackground(MainPanel.color);
        textIns.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        textIns.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
            }

            @Override
            public void focusLost(FocusEvent fe) {
                strIns = textIns.getText();
            }
        });
        textIns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                strIns = textIns.getText();
            }
        });

        playerCardClips = new ArrayList<ArrayList<BufferedImage>>();
        labelPlayerCards = new JLabel();
        labelPlayer = new JLabel("Player: ");
    }
}

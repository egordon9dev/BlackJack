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
 * @author Ethan Gordon
 */
public class GraphicPlayer extends Player {

    private final JPanel northPanel;
    private JPanel westPanel;
    private ArrayList<ArrayList<BufferedImage>> playerCardClips;
    private JLabel labelPlayerCards;
    private final JLabel labelMoney, labelBet, labelIns, labelPlayer;
    private JTextField textMoney, textBet, textIns;
    private String strBet, strIns;
    private String strPlayer;
    private boolean moneySet = false;

    /**
     * @return flag representing whether or not the money/bankroll has been set
     */
    public boolean isMoneySet() {
        return moneySet;
    }

    /**
     * @return north panel
     */
    public JPanel getNorthPanel() {
        return northPanel;
    }

    /**
     * @return west panel
     */
    public JPanel getWestPanel() {
        return westPanel;
    }

    /**
     * @return label with card image clips
     */
    public JLabel getLabelPlayerCards() {
        return labelPlayerCards;
    }

    /**
     * @return player label
     */
    public JLabel getLabelPlayer() {
        return labelPlayer;
    }

    /**
     * @return string bet
     */
    public String getStrBet() {
        return strBet;
    }

    /**
     * @return string insurance bet
     */
    public String getStrIns() {
        return strIns;
    }

    /**
     * @return string representation of player
     */
    public String getStrPlayer() {
        return strPlayer;
    }

    /**
     * sets string player
     * @param s
     */
    public void setStrPlayer(String s) {
        strPlayer = s;
    }

    /**
     * sets up for the next round
     */
    public void nextRound() {
        strBet = "";
        strIns = "";
        textBet.setText("");
        textIns.setText("");
    }

    /**
     * updates bet
     */
    public void updateBet() {
        while (true) {
            try {
                double dBet = Double.parseDouble(strBet);
                if (dBet > money || dBet < 0.0) {
                    continue;
                }
                betMoney(dBet);
                setBet(dBet);
                break;
            } catch (Exception e) {
            }
        }
    }

    /**
     * updates money text field
     */
    public void updateTextMoney() {
        textMoney.setText(String.valueOf(money));
    }

    /**
     * updates insurance bet
     */
    public void updateInsBet() {
        double max = bet / 2.0 < money ? bet / 2.0 : money;
        while (true) {
            try {
                Double d = 0.0;
                if (!isBankrupt()) {
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

    /**
     * updates player card clips
     */
    public void updatePlayerCardClips() {
        playerCardClips = createCardClips();
        labelPlayerCards.setIcon(new ImageIcon(joinCards(playerCardClips)));
    }

    /**
     * constructs new GraphicPlayer
     */
    public GraphicPlayer() {
        super();

        strBet = "invalid";
        strIns = "invalid";
        textMoney = new JTextField(10);
        textMoney.setFocusable(true);
        textBet = new JTextField(10);
        textIns = new JTextField(10);
        labelMoney = new JLabel("MONEY: ");
        labelBet = new JLabel("BET: ");
        labelIns = new JLabel("INSURANCE BET: ");
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

        textMoney.addFocusListener(new FocusListener() {
            /**
             * does nothing
             *
             * @param fe
             */
            @Override
            public void focusGained(FocusEvent fe) {
            }

            /**
             * updates stuff when user clicks away
             *
             * @param fe
             */
            @Override
            public void focusLost(FocusEvent fe) {
                try {
                    money = Double.parseDouble(textMoney.getText());
                } catch (Exception e) {
                    return;
                }
                textMoney.setFocusable(false);
                moneySet = true;
            }
        });
        textMoney.addActionListener(new ActionListener() {
            /**
             * updates stuff when user hits [ENTER]
             *
             * @param ae
             */
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    money = Double.parseDouble(textMoney.getText());
                } catch (Exception e) {
                    return;
                }
                textMoney.setFocusable(false);
                moneySet = true;
            }
        });

        textBet.addFocusListener(new FocusListener() {
            /**
             * does nothing
             *
             * @param fe
             */
            @Override
            public void focusGained(FocusEvent fe) {
            }

            /**
             * updates stuff when user clicks away
             *
             * @param fe
             */
            @Override
            public void focusLost(FocusEvent fe) {
                strBet = textBet.getText();
            }
        });
        textBet.addActionListener(new ActionListener() {
            /**
             * updates stuff when user hits [ENTER]
             *
             * @param ae
             */
            @Override
            public void actionPerformed(ActionEvent ae) {
                strBet = textBet.getText();
            }
        });
        textIns.setBackground(MainPanel.color);
        textIns.setBorder(BorderFactory.createLineBorder(MainPanel.color));
        textIns.addFocusListener(new FocusListener() {
            /**
             * does nothing
             *
             * @param fe
             */
            @Override
            public void focusGained(FocusEvent fe) {
            }

            /**
             * updates stuff when user clicks away
             *
             * @param fe
             */
            @Override
            public void focusLost(FocusEvent fe) {
                strIns = textIns.getText();
            }
        });
        textIns.addActionListener(new ActionListener() {
            /**
             * updates stuff when user hits [ENTER]
             *
             * @param ae
             */
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

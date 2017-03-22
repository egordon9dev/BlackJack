package blackjack;

/**
 * @author Ethan Gordon
 */
public class AllPlayers {

    private int playerUp;
    private final int numPlayers;
    private int turn;
    private final int PLAYER_TURN = 14981;
    private final int DEALER_TURN = 98321;
    private static GraphicPlayer player[];

    /**
     * @return the size of players array
     */
    public int numP() {
        return numPlayers;
    }

    /**
     * @return the index of the current player up
     */
    public int UpP() {
        return playerUp;
    }

    /**
     * @return the element representing the current player up
     */
    public GraphicPlayer p() {
        return player[playerUp];
    }

    /**
     * @param n index of player
     * @return player element from a given index
     */
    public GraphicPlayer p(int n) {
        return player[n];
    }

    /**
     * @return boolean representation of whether or not it's the player's turn
     */
    public boolean isPlayerTurn() {
        return turn == PLAYER_TURN;
    }

    /**
     * updates money textfield
     */
    public void updateTextMoney() {
        for (int i = 0; i < numPlayers; i++) {
            player[i].updateTextMoney();
        }
    }

    /**
     * prepares all players for the next round
     */
    public void nextRound() {
        playerUp = 0;
        turn = PLAYER_TURN;
        for (GraphicPlayer p : player) {
            p.nextRound();
        }
    }

    /**
     * @param n start
     * @return whether or not all players are bankrupt from start
     */
    public boolean allPlayersBankrupt(int n) {
        if (n == 0) {
            return player[n].isBankrupt();
        }
        return player[n].isBankrupt() && allPlayersBankrupt(n - 1);
    }

    /**
     * @return whether or not all players are bankrupt
     */
    public boolean allPlayersBankrupt() {
        return allPlayersBankrupt(numPlayers - 1);
    }

    /**
     * @param n start
     * @return whether or not all players are inactive from start
     */
    public boolean allPlayersNotActive(int n) {
        if (n == 0) {
            return !player[n].isActive();
        }
        return !player[n].isActive() && allPlayersNotActive(n - 1);
    }

    /**
     * @return whether or not all players are inactive
     */
    public boolean allPlayersNotActive() {
        return allPlayersNotActive(numPlayers - 1);
    }

    /**
     * @param n start
     * @return true if not all players have busted from start
     */
    public boolean notAllPlayersBust(int n) {
        if (n == 0) {
            return !player[n].isBust();
        }
        return !player[n].isBust() || notAllPlayersBust(n - 1);
    }

    /**
     * @return true if not all players have busted
     */
    public boolean notAllPlayersBust() {
        return notAllPlayersBust(numPlayers - 1);
    }

    /**
     * updates bankrupt flag
     */
    public void updateBankrupt() {
        for (int q = 0; q < numPlayers; q++) {
            if (player[q].getMoney() <= 0) {
                player[q].setBankrupt(true);
            }
        }
    }

    /**
     * updates insurance bet from user input
     */
    public void updateInsBet() {
        for (int i = 0; i < numPlayers; i++) {
            if (player[i].getInsBet() > -0.000001 && player[i].getInsBet() < 0.000001) {
                player[i].updateInsBet();
            }
        }
    }

    /**
     * @return whether or not all players money has been set
     */
    public boolean isMoneySet() {
        for (GraphicPlayer gp : player) {
            if (!gp.isMoneySet()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param n number of players
     */
    public AllPlayers(int n) {
        turn = PLAYER_TURN;
        numPlayers = n;
        player = new GraphicPlayer[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            player[i] = new GraphicPlayer();
        }
        playerUp = 0;
    }

    /**
     * updates string representation of player (text) updates graphic
     * representation of player (sprites)
     */
    public void updateCards() {
        for (int i = 0; i < numPlayers; i++) {
            String s = "player " + i + ": \n";
            for (int j = 0; j < player[i].getPlayers().size(); j++) {
                s += "\t" + j + ": " + player[i].getPlayers().get(j).toString() + "\n";
            }
            player[i].setStrPlayer(s);
            player[i].updatePlayerCardClips();
        }
    }

    /**
     * prepares fields for a round
     *
     * @param shoe
     */
    public void setup(Shoe shoe) {
        for (int i = 0; i < numPlayers; i++) {
            player[i].reset();

            if (player[i].isBankrupt()) {
                for (PartialPlayer p : player[i].getPlayers()) {
                    p.setActive(false);
                }
                continue;
            }
            //get bet from strBet
            player[i].updateBet();

            //setup hands after bet is made
            player[i].drawCard(shoe.drawCard());
            player[i].drawCard(shoe.drawCard());
        }
    }

    /**
     * updates fields during a round
     */
    public void update() {
        for (int i = 0; i < numPlayers; i++) {
            player[i].updateActivity();
            if (!player[playerUp].getPlayers().get(player[playerUp].getFocusedPlayer()).isActive()) {
                player[playerUp].incFocusedPlayer();
            }
            if (i == playerUp) {
                String strActivity = "";
                for (PartialPlayer j : player[i].getPlayers()) {
                    strActivity += j.isActive();
                }
                String debugStuff = player[i].isActive() + "|" + strActivity + "|" + (turn == PLAYER_TURN ? "PPP" : "DDD");
                player[i].getLabelPlayer().setText("player " + i + "." + player[i].getFocusedPlayer()
                        + ": =========>>" + (GameGUI.DEBUG_MODE == true ? debugStuff : ""));
            } else {
                player[i].getLabelPlayer().setText("player " + i + ": ");
            }
        }
    }

    /**
     * increments current player up index and tries to find an active player
     * PRECONDITION: it must be the player's turn
     */
    public void incPlayerUp() {
        do {
            if (allPlayersNotActive()) {
                turn = DEALER_TURN;
                return;
            }
            playerUp++;
            if (playerUp >= numPlayers) {
                playerUp = 0;
                turn = DEALER_TURN;
            }
        } while (!player[playerUp].isActive() || player[playerUp].isBankrupt());
        if (!player[playerUp].getPlayers().get(0).isActive()) {
            player[playerUp].incFocusedPlayer();
        }
    }
}

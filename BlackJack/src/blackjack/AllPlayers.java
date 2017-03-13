/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author ethan
 */
public class AllPlayers {
    private int playerUp;
    private final int numPlayers;
    private int turn;
    private final int PLAYER_TURN = 14981;
    private final int DEALER_TURN = 98321;
    private static GraphicPlayer player[];

    public int numP() { return numPlayers; }
    public int UpP() { return playerUp; }
    public GraphicPlayer p() { return player[playerUp]; }
    public GraphicPlayer p(int n) { return player[n]; }
    public boolean isPlayerTurn() { return turn == PLAYER_TURN; }
    
    public void updateTextMoney() {
        for(int i = 0; i < numPlayers; i++) {
            player[i].updateTextMoney();
        }
    }
    
    public void nextRound() {
        playerUp = 0;
        turn = PLAYER_TURN;
        for(GraphicPlayer p : player) {
            p.nextRound();
        }
    }
    
    public boolean allPlayersBankrupt(int n) {
        if(n == 0) return player[n].isBankrupt();
        return player[n].isBankrupt() && allPlayersBankrupt(n-1);
    }
    public boolean allPlayersBankrupt() { return allPlayersBankrupt(numPlayers-1); }
    public boolean allPlayersNotActive(int n) {
        if(n == 0) return !player[n].isActive();
        return !player[n].isActive() && allPlayersNotActive(n-1);
    }
    public boolean allPlayersNotActive() { return allPlayersNotActive(numPlayers-1); }
    public boolean notAllPlayersBust(int n) {
        if(n == 0) return !player[n].isBust();
        return !player[n].isBust() || notAllPlayersBust(n-1);
    }
    public boolean notAllPlayersBust() { return notAllPlayersBust(numPlayers-1); }
    
    public void updateBankrupt() {
        for(int q = 0; q < numPlayers; q++) {
            if(player[q].getMoney() <= 0) player[q].setBankrupt(true);
        }
    }
    public void updateInsBet() {
        for(int i = 0; i < numPlayers; i++) {
            if (player[i].getInsBet() > -0.000001 && player[i].getInsBet() < 0.000001) {
                    player[i].updateInsBet();
            }
        }
    }
    public AllPlayers(int n) {
        turn = PLAYER_TURN;
        numPlayers = n;
        player = new GraphicPlayer[numPlayers];
        for(int i = 0; i < numPlayers; i++) {
            player[i] = new GraphicPlayer(10000);
        }
        playerUp = 0;
    }
    
    
    
    
    public void updateCards() {
        for(int i = 0; i < numPlayers; i++) {
            String s = "player "+i+": \n";
            for (int j = 0; j < player[i].getPlayers().size(); j++) {
                s += "\t" + j + ": " + player[i].getPlayers().get(j).toString() + "\n";
            }
            player[i].setStrPlayer(s);
            player[i].updatePlayerCardClips();
        }
    }
    //before each round
    public void setup(Shoe shoe) {
        for(int i = 0; i < numPlayers; i++) {
            player[i].reset();

            if(player[i].isBankrupt()) {
                for(PartialPlayer p : player[i].getPlayers()) {
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
    //during round
    public void update() {
        for(int i = 0; i < numPlayers; i++) {
            player[i].updateActivity();
            if(i == playerUp) {
                String strActivity = "";
                for(PartialPlayer j : player[i].getPlayers()) {
                    strActivity += j.isActive();
                }
                String debugStuff = player[i].isActive()+ "|" + strActivity + "|" + (turn == PLAYER_TURN ? "PPP" : "DDD");
                player[i].getLabelPlayer().setText("player "+i+"."+player[i].getFocusedPlayer()+
                ": =========>>" + (GameGUI.DEBUG_MODE == true ? debugStuff : ""));
                
            } else {
                player[i].getLabelPlayer().setText("player "+i+": ");
            }
        }
    }
    
     /**
     * PRECONDITION: it must be the player's turn
     */
    public void incPlayerUp() {
        do {
            if(allPlayersNotActive()) {
                turn = DEALER_TURN;
                return;
            }
            playerUp++;
            if(playerUp >= numPlayers) {
                playerUp = 0;
                turn = DEALER_TURN;
            }
        } while(!player[playerUp].isActive() || player[playerUp].isBankrupt());
        if(!player[playerUp].getPlayers().get(0).isActive()) player[playerUp].incFocusedPlayer();
    }
    
}

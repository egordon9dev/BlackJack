package blackjack;

/**
 * @author Ethan Gordon
 */
public class PartialPlayer {

    private boolean active;
    private boolean standing;
    private boolean doubled;
    private Hand hand;

    /**
     * @return doubled flag
     */
    public boolean isDoubled() {
        return doubled;
    }

    /**
     * sets doubled flag to true
     */
    public void setDoubled() {
        doubled = true;
    }

    /**
     * @return active flag
     */
    public boolean isActive() {
        return active;
    }

    /**
     * sets active flag
     *
     * @param b
     */
    public void setActive(boolean b) {
        active = b;
    }

    /**
     * @return standing flag
     */
    public boolean isStanding() {
        return standing;
    }

    /**
     * sets standing flag
     *
     * @param b
     */
    public void setStanding(boolean b) {
        standing = b;
    }

    /**
     * @return hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * sets hand
     *
     * @param hand
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    /**
     * constructs new PartialPlayer
     */
    public PartialPlayer() {
        hand = new Hand();
        standing = false;
        doubled = false;
        active = true;
    }

    /**
     * @return string representation of this partial player's hand
     */
    @Override
    public String toString() {
        return hand.toString();
    }
}
